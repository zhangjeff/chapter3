package com.jeff.spring.controller;

import com.jeff.spring.framework.annotation.Action;
import com.jeff.spring.framework.annotation.Controller;
import com.jeff.spring.framework.annotation.Inject;
import com.jeff.spring.framework.bean.View;
import com.jeff.spring.model.Customer;
import com.jeff.spring.service.CustomerService;

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
