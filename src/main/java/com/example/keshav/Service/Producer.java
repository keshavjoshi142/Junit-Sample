package com.example.keshav.Service;
import com.example.keshav.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "test1";


    @Autowired
    private KafkaTemplate<String , Customer> kafkaTemplate;

    public void sendMessage(Customer customer) {
        System.out.println("message published");
        this.kafkaTemplate.send(TOPIC , customer);
    }
}
