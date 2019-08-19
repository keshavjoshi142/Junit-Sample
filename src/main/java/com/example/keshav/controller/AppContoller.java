package com.example.keshav.controller;

import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import com.example.keshav.Service.Consumer;
import com.example.keshav.Service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController

public class AppContoller {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Producer producer;
    private Consumer consumer;

    @GetMapping("message")
    public @ResponseBody Iterable<Customer> getMessage()
    {

        //producer = new Producer();


        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("keshav");
        customer.setCustomerAdd("Avana JIO");
        customer.setCustomerPhNo("8003328807");
        producer.sendMessage(customer);
        customerRepository.save(customer);

        customer.setCustomerId(2);
        customer.setCustomerName("keshav1");
        customer.setCustomerAdd("Avana JIO");
        customer.setCustomerPhNo("8003328807");
        customerRepository.save(customer);


        System.out.println(customerRepository.findAll());
        return customerRepository.findAll();
    }

    @RequestMapping(value = "put/customer" , consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Customer customer) {

        if(Objects.isNull(customer)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        customerRepository.save(customer);

        producer.sendMessage(customer);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
