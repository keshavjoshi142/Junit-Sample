package com.example.keshav.controllerTest;


import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import com.example.keshav.Service.AppService;
import com.example.keshav.Service.Consumer;
import com.example.keshav.Service.Producer;
import com.example.keshav.controller.AppContoller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(AppContoller.class)
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppService appService;

    @MockBean
    private Producer producer;

    @MockBean
    private Consumer consumer;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testPutConsumerAPI() throws Exception {

        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("Shivani");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("Sarjapur");

        when(appService.addCustomer(any(Customer.class))).thenReturn(mockCustomer);

        Customer aCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("keshav");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("Sarjapur");

        String serialisedCustomer = objectMapper.writeValueAsString(aCustomer);

        mockMvc.perform(post("/put/customer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(serialisedCustomer))
                .andExpect(status().isCreated())
                .andReturn();
    }




}
