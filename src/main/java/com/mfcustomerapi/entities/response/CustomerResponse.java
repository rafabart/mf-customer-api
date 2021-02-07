package com.mfcustomerapi.entities.response;

import com.mfcustomerapi.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;

    private String email;

    private Set<Role> rolesNamed = new HashSet<>();
}
