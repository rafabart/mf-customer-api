package com.mfcustomerapi.services;

import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.exceptions.CustomerNotFoundException;
import com.mfcustomerapi.exceptions.EmailIntegrityViolationException;
import com.mfcustomerapi.exceptions.EmailNotFoundException;
import com.mfcustomerapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Customer findById(final Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }


    public Customer findByEmail(final String email) {
        return customerRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
    }


    public void deleteById(final Long id) {
        findById(id);
        customerRepository.deleteById(id);
    }


    public Long create(final Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new EmailIntegrityViolationException();
        }
        return customerRepository.save(customer).getId();
    }


    public void update(final Customer customer) {
        findById(customer.getId());
        customerRepository.save(customer);
    }
}
