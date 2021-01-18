package com.mfcustomerapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Role {

    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");


    private Integer cod;
    private String name;


    public static Role toEnum(final Integer cod) {

        if (Objects.isNull(cod)) {
            return null;
        }

        for (Role expenseType : Role.values()) {

            if (cod.equals(expenseType.getCod())) {
                return expenseType;
            }
        }

        throw new IllegalArgumentException("Tipo de regra inválido: " + cod);
    }


    public static Role toEnum(final String name) {

        if (Objects.isNull(name)) {
            return null;
        }

        for (Role role : Role.values()) {

            if (name.equals(role.getName()) || name.equals(role.toString())) {
                return role;
            }
        }

        throw new IllegalArgumentException("Tipo de regra inválido: " + name);
    }
}
