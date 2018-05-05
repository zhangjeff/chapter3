package com.jeff;

import com.jeff.chapter1.helper.DataBaseHelper;
import com.jeff.chapter1.model.Customer;
import com.jeff.chapter1.service.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.notification.RunListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/**
 * Created by zhangying on 2018/3/12.
 */
public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Test
    public void init(){
        System.out.println("-----------------init-------------------");
        String file = "sql/customer_init.sql";
        DataBaseHelper.executeSqlFile(file);
    }


    @Test
    public void getCustomerTest() throws Exception{
        long id = 1;
        System.out.println("--------------getCustomerTest------------------");
        List<Customer> customers = customerService.getCustomerList(null);
        System.out.println(customers.size());
    }

    @Test
    public void getCustomerTest2() throws Exception{
        long id = 1;
        System.out.println("--------------getCustomerTest------------------");
        List<Customer> customers = customerService.getCustomerList();
        System.out.println(customers.size());
    }

    @Test
    public void getCustomerTest3() throws Exception{
        long id = 1;
        System.out.println("--------------getCustomerTest------------------");
        Customer customer = customerService.getCustomer();
        System.out.println(customer);
    }
}
