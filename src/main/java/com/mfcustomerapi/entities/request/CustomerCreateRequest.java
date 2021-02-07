package com.mfcustomerapi.entities.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Set<String> roles = new HashSet<>();
}
