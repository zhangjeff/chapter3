package com.jeff.chapter1.controller;

import com.jeff.chapter1.framework.HelperLoader;
import com.jeff.chapter1.framework.annotation.Action;
import com.jeff.chapter1.framework.annotation.Controller;
import com.jeff.chapter1.model.Customer;
import com.jeff.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
