package com.mfcustomerapi.entities.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequest {

    @NotNull
    private String name;

    private String password;

    @NotNull
    @Builder.Default
    private Set<String> roles = new HashSet<>();
}
