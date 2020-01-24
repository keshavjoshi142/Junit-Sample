package com.example.keshav.controller;

import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import com.example.keshav.Service.AppService;
import com.example.keshav.Service.Consumer;
import com.example.keshav.Service.Producer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class AppContoller implements ApplicationContextAware {

    @Autowired
    AppService appService;

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    private ApplicationContext context;

    @GetMapping("all")
    public @ResponseBody Iterable<Customer> getMessage()
    {
        return appService.findAllCustomer();
    }

    @RequestMapping(value = "put/customer" , consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {

        Customer newCustomer = appService.addCustomer(customer);

        System.out.println(newCustomer);

        if(Objects.isNull(newCustomer)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/shutdownContext")
    public void shutdownContext() {
        ((ConfigurableApplicationContext) context).close();
    }

    @GetMapping("changeAddress")
    public ResponseEntity<String> changeTrinityAdd() {
        List<Customer> customerList = appService.changeAddress();

        if(Objects.isNull(customerList)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
