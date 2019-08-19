package com.example.keshav.Service;

import com.example.keshav.Models.Customer;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(String.class);
    @KafkaListener(topics = "test1" , containerFactory = "kafkaListenerContainerFactory")
    public Customer consume(Customer customer) throws IOException {
        logger.info("message coming" + customer);
    return customer;
}

}
