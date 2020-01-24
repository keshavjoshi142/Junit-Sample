package com.example.keshav.ServiceTest;

import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import com.example.keshav.Service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppServiceIntegrationTest {

    @Autowired
    private AppService appService;

    @Test
    public void testAddCustomer() {

        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("Satrajit");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("Sarjapur");

        Customer newCustomer = appService.addCustomer(mockCustomer);

        assertNotNull(newCustomer);
        assertEquals(newCustomer.getCustomerId() , 7);
        assertEquals(newCustomer.getCustomerName() , "Satrajit");
        assertEquals(newCustomer.getCustomerPhNo() , "12345678");
        assertEquals(newCustomer.getCustomerAdd() , "Sarjapur");
    }

}
