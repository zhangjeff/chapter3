package com.jeff.chapter1.controller;

import com.jeff.chapter1.framework.HelperLoader;
import com.jeff.chapter1.framework.annotation.Action;
import com.jeff.chapter1.framework.annotation.Controller;
import com.jeff.chapter1.framework.annotation.Inject;
import com.jeff.chapter1.framework.bean.View;
import com.jeff.chapter1.model.Customer;
import com.jeff.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Youpeng.Zhang on 2018/3/13.
 */
@Controller
public class CustomerController  {

    @Inject
    private CustomerService customerService;
//
//    @Override
//    public void init() throws ServletException {
//        customerService = new CustomerService();
//        HelperLoader.init();
//    }

    @Action("get:/customer")
    protected View getCustomer() {
        List<Customer> customerList = customerService.getCustomerList();
//        req.setAttribute("customerList", customerList);
//        req.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(req, resp);
        View view = new View("customer.jsp");
//        List<Customer> customerList = new ArrayList<Customer>();
        Customer customer = new Customer();
        customer.setId(112);
        customer.setName("jeff");
        customer.setTelephone("1235454");
        customer.setContact("contact");
        customer.setEmail("1234@143.com");
        customer.setRemark("remark");
        customerList.add(customer);
        view.addModel("customerList", customerList);
        return view;
    }
}
