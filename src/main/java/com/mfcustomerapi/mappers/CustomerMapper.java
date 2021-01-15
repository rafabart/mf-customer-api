package com.mfcustomerapi.mappers;

import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.entities.request.CustomerRequest;
import com.mfcustomerapi.entities.response.CustomerResponse;
import com.mfcustomerapi.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "roles", qualifiedByName = "rolesMapper")
    Customer to(CustomerRequest customerRequest);

    @Mapping(target = "roles", qualifiedByName = "rolesMapper")
    Customer to(Long id, CustomerRequest customerRequest);

    CustomerResponse toReponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customerList);


    @Named("rolesMapper")
    default Set<Integer> rolesMapper(final Set<String> roles) {
        return roles
                .stream()
                .map(Role::toEnum)
                .map(Role::getCod)
                .collect(Collectors.toSet());
    }
}
