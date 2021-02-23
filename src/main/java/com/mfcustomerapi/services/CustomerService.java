package com.mfcustomerapi.services;

import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.exceptions.CustomerNotFoundException;
import com.mfcustomerapi.exceptions.EmailIntegrityViolationException;
import com.mfcustomerapi.exceptions.EmailNotFoundException;
import com.mfcustomerapi.mappers.CustomerMapper;
import com.mfcustomerapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    @Cacheable(value = "customer", key = "#id", unless = "#result == null")
    public Customer findById(final Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }


    @Cacheable(value = "customerByEmail", key = "#email", unless = "#result == null")
    public Customer findByEmail(final String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
    }


    @Caching(evict = {
            @CacheEvict(value = "customer", key = "#id"),
            @CacheEvict(value = "customerByEmail", allEntries = true)
    })
    public void deleteById(final Long id) {
        findById(id);
        customerRepository.deleteById(id);
    }


    @Caching(put = {
            @CachePut(value = "customer", key = "#result.id"),
            @CachePut(value = "customerByEmail", key = "#result.email")
    })
    public Customer create(final Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new EmailIntegrityViolationException(customer.getEmail());
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return customerRepository.save(customer);
    }


    @Caching(put = {
            @CachePut(value = "customer", key = "#result.id"),
            @CachePut(value = "customerByEmail", key = "#result.email")
    })
    public Customer update(Customer customer) {

        final Customer customerSaved = findById(customer.getId());

        if (Objects.nonNull(customer.getPassword())) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }

        customerMapper.toUpdate(customerSaved, customer);
        return customerRepository.save(customerSaved);
    }
}
