package com.example.keshav.Repositories;

import com.example.keshav.Models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer , Integer> {
}
