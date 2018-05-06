package com.jeff.spring.helper;

import com.jeff.spring.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhangying on 2018/3/12.
 */
public class DataBaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);
    private static final QueryRunner  QUERY_RUNNER = new QueryRunner();
    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
    private static final BasicDataSource DATA_SOURCE;


    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);

//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("can not load jdbc driver", e);
//            e.printStackTrace();
//        }
    }

//    public static Connection getConnection(){
//        Connection conn = null;
//        try{
//            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            LOGGER.error("get connection failure", e);
//        }
//        return conn;
//    }


//    public static Connection getConnection(){
//        Connection conn = CONNECTION_HOLDER.get();
//        if (conn == null) {
//            try{
//                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                LOGGER.error("get connection failure", e);
//            } finally {
//                CONNECTION_HOLDER.set(conn);
//            }
//        }
//        return conn;
//    }

    public static Connection getConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null) {
            try{
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGER.error("get connection failure", e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

//    public static void closeConnection(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//
//            }
//        }
//    }

    public static void closeConnection(){
        Connection conn = CONNECTION_HOLDER.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }


    public static <T> List<T> queryEntityList(Class<T>entityClass, String sql, Object...params) {
        List<T> entityList = null;
        Connection conn = getConnection();
        try{
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return entityList;
    }

    public static<T> T queryEntity(Class<T>entityClass, String sql, Object...params) {
        T entity = null;
        try{
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return entity;
    }

    public static List<Map<String, Object>> executeQuery(String sql, Object...params) {
        List<Map<String, Object>> result = null;
        try{
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static int executeUpdate(String sql, Object...params) {
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return rows;
    }

    public static<T> boolean insertEntity(Class<T>entityClass, Map<String,Object>fieldMap ) {
        if (fieldMap != null && fieldMap.size() > 0) {
            return  false;
        }

        String sql = "insert into " + getTableName(entityClass);
        StringBuffer columns = new StringBuffer("(");
        StringBuffer values = new StringBuffer("(");

        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }

        columns.replace(columns.lastIndexOf(", "), columns.length(), ") ");
        values.replace(columns.lastIndexOf(", "), columns.length(), ") ");
        sql += columns + " VALUES " + values;
        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }

    public static<T> boolean updateEntity(Class<T>entityClass, long id, Map<String,Object>fieldMap ){
        if (fieldMap != null && fieldMap.size() > 0) {
            return  false;
        }

        String sql = "update " + getTableName(entityClass) + " SET ";
        StringBuffer columns = new StringBuffer();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }

        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql, params) == 1;
    }

    public static<T> boolean deleteEntity(Class<T>entityClass, long id){
        String sql = "delete from " + getTableName(entityClass) + " where id=? ";
        return executeUpdate(sql, id) == 1;
    }

    private static String getTableName(Class<?> entityclass) {
        return entityclass.getSimpleName();
    }

    public static  void executeSqlFile(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String  sql;
            while ((sql = reader.readLine()) != null) {
                DataBaseHelper.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
