package com.msnishan.basicgraphql.basicgraphql.service;

import com.msnishan.basicgraphql.basicgraphql.record.Address;
import com.msnishan.basicgraphql.basicgraphql.record.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    private Map<Long, Customer> customers = new HashMap<>();

    public Customer getById(Long id) {
        return customers.get(id);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    @PostConstruct
    public void loadCustomers() {
        customers.put(1L, new Customer(1L, "1", new Address(
                "line1",
                "line2",
                "123",
                "AB",
                "CO")));
        customers.put(2L, new Customer(2L, "2", new Address(
                "line1",
                "line2",
                "123",
                "BC",
                "CO")));
        customers.put(3L, new Customer(3L, "3", new Address(
                "line1",
                "line2",
                "123",
                "AB",
                "CN")));
        customers.put(4L, new Customer(4L, "4", new Address(
                "line1",
                "line2",
                "123",
                "AB",
                "CO")));
        customers.put(5L, new Customer(5L, "5", new Address(
                "line1",
                "line2",
                "123",
                "AB",
                "C5")));
    }
}
