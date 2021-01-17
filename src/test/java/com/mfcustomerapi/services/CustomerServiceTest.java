package com.mfcustomerapi.services;

import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.enums.Role;
import com.mfcustomerapi.exceptions.CustomerNotFoundException;
import com.mfcustomerapi.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @SpyBean
    private CustomerService customerService;

    final Customer customer = new Customer();


    @BeforeEach
    public void setUp() throws Exception {

        customer.setId(1L);
        customer.setName("Bart Simpsons");
        customer.setEmail("bart@gmail.com");
        customer.setPassword("123456");
        customer.setRoles(new HashSet<>(Arrays.asList(Role.USER.getCod(), Role.USER.getCod())));
    }

    @Test
    public void must_find_costumer_by_id() {

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

        final Customer customerSaved = customerService.findById(Mockito.anyLong());

        verify(customerRepository).findById(Mockito.anyLong());

        assertThat(customerSaved).isNotNull();
        assertThat(customerSaved.getId()).isEqualTo(1L);
        assertThat(customerSaved.getName()).isEqualTo(customer.getName());
        assertThat(customerSaved.getEmail()).isEqualTo(customer.getEmail());
        assertThat(customerSaved.getRoles()).isEqualTo(customer.getRoles());
        assertThat(customerSaved.getPassword()).isEqualTo(customer.getPassword());
    }

    @Test
    public void should_not_find_costumer_by_nonexistent_id() {

        final Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.findById(Mockito.anyLong())
        );

        assertTrue(exception.getMessage().contains("não encontrado"));
    }
}
