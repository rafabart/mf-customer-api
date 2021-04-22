package com.mfcustomerapi.mapper;

import com.mfcustomerapi.builders.CustomerBuilder;
import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.entities.request.CustomerCreateRequest;
import com.mfcustomerapi.entities.request.CustomerUpdateRequest;
import com.mfcustomerapi.entities.response.CustomerResponse;
import com.mfcustomerapi.mappers.CustomerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Teste dos metodos da classe mapper")
public class CustomerMapperTest {

    @Autowired
    private CustomerBuilder customerBuilder;

    @Autowired
    private CustomerMapper customerMapper;


    @Test
    void shouldMapCustomerCreateRequestToCustomer() {

        final CustomerCreateRequest request = customerBuilder.createObjCustomerCreateRequest();

        final Customer customer = customerMapper.to(request);

        assertThat(customer).isNotNull();
        assertThat(customer.getName()).isEqualTo(request.getName());
        assertThat(customer.getEmail()).isEqualTo(request.getEmail());
        assertThat(customer.getPassword()).isEqualTo(request.getPassword());

        request.getRoles().forEach(
                role -> assertThat(request.getRoles()).contains(role)
        );
    }


    @Test
    void shouldMapCustomerUpdateRequestToCustomer() {

        final CustomerUpdateRequest request = customerBuilder.createObjCustomerUpdateRequest();
        final Long id = System.currentTimeMillis();

        final Customer customer = customerMapper.to(id, request);

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getName()).isEqualTo(request.getName());
        assertThat(customer.getEmail()).isNull();
        assertThat(customer.getPassword()).isEqualTo(request.getPassword());

        request.getRoles().forEach(
                role -> assertThat(request.getRoles()).contains(role)
        );
    }


    @Test
    void shouldUpdateCustomer() {

        final Customer customer = customerBuilder.createObjCustomer();
        final Customer customerToUpdate = customerBuilder.createObjCustomer();

        customerMapper.toUpdate(customer, customerToUpdate);

        assertThat(customer).isEqualToComparingFieldByField(customerToUpdate);
    }


    @Test
    void shouldMapCustomerToCustomerResponse() {

        final Customer customer = customerBuilder.createObjCustomer();
        customer.setId(System.currentTimeMillis());

        final CustomerResponse response = customerMapper.toReponse(customer);

        assertThat(response).isEqualToComparingFieldByField(customer);
    }


    @Test
    void shouldMapListOfCustomerToListOfCustomerResponse() {

        final List<Customer> customerList = customerBuilder.createLstOfCustomer();

        final List<CustomerResponse> responseList = customerMapper.toResponseList(customerList);

        for (int i = 0; i < responseList.size(); i++) {
            assertThat(responseList.get(i)).isEqualToComparingFieldByField(customerList.get(i));
        }
    }
}
