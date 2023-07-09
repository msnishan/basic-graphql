package com.msnishan.basicgraphql.basicgraphql.rest;

import com.msnishan.basicgraphql.basicgraphql.record.Customer;
import com.msnishan.basicgraphql.basicgraphql.record.Purchase;
import com.msnishan.basicgraphql.basicgraphql.service.CustomerService;
import com.msnishan.basicgraphql.basicgraphql.service.PurchaseService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class GraphqlController {


    final private CustomerService customerService;

    final private PurchaseService purchaseService;

    public GraphqlController(CustomerService customerService, PurchaseService purchaseService) {
        this.customerService = customerService;
        this.purchaseService = purchaseService;
    }

    @QueryMapping
    public Collection<Customer> customers() {
        return customerService.getAllCustomers();
    }

    @QueryMapping
    public Customer customerById(@Argument Long id) {
        return customerService.getById(id);
    }

    @SchemaMapping(typeName = "Customer")
    public Collection<Purchase> purchases(Customer customer) {
        return purchaseService.getPurchasesByCustomer(customer.id());
    }
}
