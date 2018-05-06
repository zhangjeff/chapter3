package com.jeff.spring.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Youpeng.Zhang on 2018/3/13.
 */
public final class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?>loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try{
            cls = Class.forName(className, isInitialized, getClassLoader());
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return  cls;
    }

    public  static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try{
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
//          Name.replace
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", "");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet,String  packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
               String subPackagePath = fileName;
               if (StringUtil.isNotEmpty(packagePath)) {
                   if (packagePath.endsWith("/")) {
                       subPackagePath = packagePath + subPackagePath;
                   } else {
                       subPackagePath = packagePath + "/" + subPackagePath;
                   }
               }

               String subPackageName  = fileName;
               if (StringUtil.isNotEmpty(packageName)) {
                   subPackageName  = packageName + "." + subPackageName;
               }
               addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>>classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }


    public static void mainmain(String[] args) {
        System.out.println("---------------1111111111111111-------------------");
        Set<Class<?>> classSet = ClassUtil.getClassSet("");
        System.out.println("---------------2222222222222222-------------------");
    }
}
