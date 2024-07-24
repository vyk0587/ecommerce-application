package com.onejava;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onejava.entity.Address}
 */
@Value
public class AddressDto implements Serializable {
    UserDto user;
    @Size(max = 255)
    String street;
    @Size(max = 100)
    String city;
    @Size(max = 100)
    String state;
    @Size(max = 20)
    String postalCode;
    @Size(max = 100)
    String country;

    /**
     * DTO for {@link com.onejava.entity.User}
     */
    @Value
    public static class UserDto implements Serializable {
        Integer id;
        @NotNull
        @Size(max = 50)
        String userName;
    }
}