package com.jeff;

import java.lang.reflect.Field;

/**
 * @author Youpeng.Zhang on 2018/3/26.
 */
public class CustomerTest {

    Customer customer;

    Integer aaaa;

    public static void main(String[] args) {
        try{
            Class cls = Class.forName("com.jeff.Customer");
            Object newObj = cls.newInstance();
            Class ctt = Class.forName("com.jeff.CustomerTest");

            Object cttObj =ctt.newInstance();
            Field[] fields = ctt.getDeclaredFields();
            for (Field field : fields) {
                if ("com.jeff.Customer".equals(field.getType().getTypeName())) {
                    field.set(cttObj,newObj);
                }

            }
            CustomerTest customerTest = (CustomerTest)cttObj;
            System.out.println(customerTest.customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
