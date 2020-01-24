package com.example.keshav.ServiceTest;

import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import com.example.keshav.Service.AppService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    AppService appService;

    private final Logger logger = LoggerFactory.getLogger(String.class);

    @Before
    public void beforeTest() {
        logger.info("<-----------------Test starting----------->>>");
    }

    @After
    public void afterTest() {
        logger.info("<----------------Test ending------------->");
    }

    @Test
    public void testAddCustomer() {

        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("Shivani");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("Sarjapur");

        logger.info("<<<-------Test 1 ---------->>>");
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        Customer newCustomer = appService.addCustomer(mockCustomer);

        verify(customerRepository , times(1)).save(any(Customer.class));
    }

    @Test
    public void testAddCustomerWithoutCustomerAdd() {
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("Shivani");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("");

        logger.info("<<<-------Test 2 ---------->>>");

        Customer newCustomer = appService.addCustomer(mockCustomer);
        verifyZeroInteractions(customerRepository);

        //verify(customerRepository , times(0)).save(any(Customer.class));
    }

    @Test
    public void testChangeAddress() {

        //////Given
        List<Customer> mockCustomerList = new ArrayList<Customer>();
        Customer mockCustomer = new Customer();
        Customer mockCustomer1 = new Customer();
        Customer mockCustomer2 = new Customer();

        mockCustomer.setCustomerId(12);
        mockCustomer.setCustomerName("Shivani");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("trinity");
        mockCustomerList.add(mockCustomer);

        mockCustomer1.setCustomerId(13);
        mockCustomer1.setCustomerName("Shivani1");
        mockCustomer1.setCustomerPhNo("12345678");
        mockCustomer1.setCustomerAdd("trinity");
        mockCustomerList.add(mockCustomer1);

        mockCustomer2.setCustomerId(14);
        mockCustomer2.setCustomerName("Shivani2");
        mockCustomer2.setCustomerPhNo("12345678");
        mockCustomer2.setCustomerAdd("trinity");
        mockCustomerList.add(mockCustomer2);

        logger.info("<<<-------Test 3 ---------->>>");

        given(customerRepository.findByCustomerAdd("trinity")).willReturn(mockCustomerList);
        /////////////Given ends

        /////When starts
        List<Customer> newCustomerList = appService.changeAddress();

        ////When ends

        //Then starts
        assertEquals(newCustomerList.get(0).getCustomerAdd(),"trinity Acres and Woods");
        assertEquals(newCustomerList.get(1).getCustomerAdd(),"trinity Acres and Woods");
        assertEquals(newCustomerList.get(2).getCustomerAdd(),"trinity Acres and Woods");
        //Then ends
    }

    @Test
    public void testChangeAddWithoutTrinity(){

        List<Customer> mockCustomerList = new ArrayList<Customer>();

        when(customerRepository.findByCustomerAdd("trinity")).thenReturn(mockCustomerList);

        logger.info("<<<-------Test 4 ---------->>>");


        List<Customer> newCustomerList = appService.changeAddress();
        Assert.assertNull(newCustomerList);
    }

}

