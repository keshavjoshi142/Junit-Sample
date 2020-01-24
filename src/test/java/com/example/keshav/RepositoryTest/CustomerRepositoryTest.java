package com.example.keshav.RepositoryTest;

import com.example.keshav.Models.Customer;
import com.example.keshav.Repositories.CustomerRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityManager entityManager;



    @Test
    public void  testSaveCustomer() {
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("Shivani");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("Sarjapur");

        Customer newCustomer = customerRepository.save(mockCustomer);

        Assert.assertNotNull(customerRepository.findOne(7));

    }

    @Test
    public void testFindAll() {

        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(7);
        mockCustomer.setCustomerName("Shivani");
        mockCustomer.setCustomerPhNo("12345678");
        mockCustomer.setCustomerAdd("Sarjapur");

        Customer mockCustomer1 = new Customer();
        mockCustomer1.setCustomerId(8);
        mockCustomer1.setCustomerName("Shivani1");
        mockCustomer1.setCustomerPhNo("12345678");
        mockCustomer1.setCustomerAdd("Sarjapur");

        Customer mockCustomer2 = new Customer();
        mockCustomer2.setCustomerId(9);
        mockCustomer2.setCustomerName("Shivani2");
        mockCustomer2.setCustomerPhNo("12345678");
        mockCustomer2.setCustomerAdd("Sarjapur");

        entityManager.persist(mockCustomer);
        entityManager.persist(mockCustomer1);
        entityManager.persist(mockCustomer2);

        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        assertEquals(14 , customers.size());
    }

    @Test
    public void testFindAllDBUnit() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        assertEquals(4 , customers.size());
    }

}
