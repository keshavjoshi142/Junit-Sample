package com.example.keshav.Service;


import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AppService {

    @Autowired
    CustomerRepository customerRepository;

    Customer newCustomer = null;

    public Customer addCustomer(Customer customer) {

        if(customer.getCustomerAdd().equals("") || customer.getCustomerName().equals("") || customer.getCustomerPhNo().equals("")) {
            return newCustomer;
        }
        newCustomer = customerRepository.save(customer);

        return newCustomer;
    }

    public Iterable<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public List<Customer> changeAddress() {

        List<Customer> customersIntrinity = (List<Customer>) customerRepository.findByCustomerAdd("trinity");
        List<Customer> changedAddressCustomer = new ArrayList<>();
        if(customersIntrinity.size() == 0) {
            return null;
        }
        for(Customer customerIntrinity : customersIntrinity) {
            Customer newCustomerInTrinity = customerIntrinity;
            customerRepository.delete(customerIntrinity);
            newCustomerInTrinity.setCustomerAdd("trinity Acres and Woods");
            customerRepository.save(newCustomerInTrinity);
            changedAddressCustomer.add(newCustomerInTrinity);
        }

        return changedAddressCustomer;
    }


}
