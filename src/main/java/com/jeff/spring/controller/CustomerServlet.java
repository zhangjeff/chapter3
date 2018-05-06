package com.jeff.spring.controller;

import com.jeff.spring.framework.HelperLoader;
import com.jeff.spring.model.Customer;
import com.jeff.spring.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Youpeng.Zhang on 2018/3/13.
 */

public class CustomerServlet extends HttpServlet {


    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        HelperLoader.init();
    }

    @Override
//    @Action("get:/customer")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.getCustomerList();
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(req, resp);

    }
}
