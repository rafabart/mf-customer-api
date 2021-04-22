package com.mfcustomerapi.services;

import com.mfcustomerapi.builders.CustomerBuilder;
import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.repositories.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Teste dos metodos da classe CustomerService")
public class CustomerServiceTest {

    @Autowired
    private CustomerBuilder customerBuilder;

    @SpyBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @SpyBean
    private CacheManager cacheManager;


    @Test
    void shouldCreateNewCustomer() {

        final Customer customer = customerBuilder.createObjCustomer();
        customer.setId(UUID.randomUUID().getMostSignificantBits());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        final Customer customerSaved = customerService.create(customer);

        verify(customerRepository, times(1)).save(any(Customer.class));

        assertThat(customerSaved).isEqualToComparingFieldByField(customer);
    }
}
