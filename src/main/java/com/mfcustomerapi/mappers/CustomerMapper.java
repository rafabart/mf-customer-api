package com.mfcustomerapi.mappers;

import com.mfcustomerapi.entities.Customer;
import com.mfcustomerapi.entities.request.CustomerCreateRequest;
import com.mfcustomerapi.entities.request.CustomerUpdateRequest;
import com.mfcustomerapi.entities.response.CustomerResponse;
import com.mfcustomerapi.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    @Mapping(target = "roles", qualifiedByName = "rolesMapper")
    Customer to(CustomerCreateRequest customerCreateRequest);

    @Mapping(target = "roles", qualifiedByName = "rolesMapper")
    Customer to(Long id, CustomerUpdateRequest customerUpdateRequest);

    void toUpdate(@MappingTarget Customer customerSaved, Customer customer);

    CustomerResponse toReponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customerList);


    @Named("rolesMapper")
    default Set<Integer> rolesMapper(final Set<String> roles) {
        return roles
                .stream()
                .map(Role::toEnum)
                .map(Role::getCod)
                .collect(toSet());
    }
}
