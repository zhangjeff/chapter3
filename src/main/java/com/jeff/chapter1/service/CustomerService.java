package com.jeff.chapter1.service;

import com.jeff.chapter1.framework.annotation.Service;
import com.jeff.chapter1.helper.DataBaseHelper;
import com.jeff.chapter1.model.Customer;
import com.jeff.chapter1.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zhangying on 2018/3/12.
 */
@Service
public class CustomerService {
//    Logger logger = LoggerFactory.getLogger(CustomerService.class);

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

//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            logger.error("can not load jdbc driver", e)
//            System.out.println(e);
//            e.printStackTrace();
//        }
    }

    public List<Customer> getCustomerList(String keyword) {
        Connection conn = null;
        List<Customer> customerList = new ArrayList<Customer>();
        try {
            String sql = "select * from customer";
            conn = DataBaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseHelper.closeConnection();
        }
        return customerList;
    }


//    public List<Customer> getCustomerList() {
//        Connection conn = DataBaseHelper.getConnection();
//        List<Customer> customerList = new ArrayList<Customer>();
//        try {
//            String sql = "select * from customer";
//            conn = DataBaseHelper.getConnection();
//            return DataBaseHelper.queryEntityList(Customer.class,  sql);
//        } finally {
//            DataBaseHelper.closeConnection();
//        }
//    }

    public List<Customer> getCustomerList() {
//        Connection conn = DataBaseHelper.getConnection();
//        List<Customer> customerList = new ArrayList<Customer>();
//        try {
            String sql = "select * from customer";
//            conn = DataBaseHelper.getConnection();
            return DataBaseHelper.queryEntityList(Customer.class,  sql);
//        } finally {
//            DataBaseHelper.closeConnection();
//        }
    }

    public Customer getCustomer() {
        String sql = "select * from customer";
        return DataBaseHelper.queryEntity(Customer.class,  sql);
    }


    public Customer getCustomer(long id) {
        return null;
    }

    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DataBaseHelper.insertEntity(Customer.class,fieldMap);
    }

    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DataBaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    public boolean deleteCustomer(long id) {
        return DataBaseHelper.deleteEntity(Customer.class, id);
    }
}
