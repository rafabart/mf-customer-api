package com.mfcustomerapi.builders;

import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.entities.request.CustomerCreateRequest;
import com.mfcustomerapi.entities.request.CustomerUpdateRequest;
import com.mfcustomerapi.enums.Role;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class CustomerBuilder {


    final Random random = new Random();


    public Customer createObjCustomer() {
        return Customer.builder()
                .name(nameRandom())
                .password(nameRandom())
                .email(emailRandom())
                .roles(roleRandomInteger())
                .build();
    }


    public CustomerCreateRequest createObjCustomerCreateRequest() {
        return CustomerCreateRequest.builder()
                .name(nameRandom())
                .password(nameRandom())
                .email(emailRandom())
                .roles(roleRandomString())
                .build();
    }


    public CustomerUpdateRequest createObjCustomerUpdateRequest() {
        return CustomerUpdateRequest.builder()
                .name(nameRandom())
                .password(nameRandom())
                .roles(roleRandomString())
                .build();
    }


    public List<Customer> createLstOfCustomer() {
        return Arrays.asList(
                createObjCustomer(),
                createObjCustomer(),
                createObjCustomer(),
                createObjCustomer()
        );
    }


    private String nameRandom() {
        return RandomStringUtils.random(10, true, false);
    }

    private String emailRandom() {
        return RandomStringUtils.random(4)
                .concat("@")
                .concat(RandomStringUtils.random(6, true, false));
    }

    private Set<String> roleRandomString() {
        final Integer role = random.nextInt(2 - 1) + 1;
        return new HashSet(Arrays.asList(Role.toEnum(role).getName()));
    }

    private Set<Integer> roleRandomInteger() {
        final Integer role = random.nextInt(2 - 1) + 1;
        return new HashSet(Arrays.asList(Role.toEnum(role).getCod()));
    }
}
