package com.onejava.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.onejava.entity.User}
 */
@Value
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 50)
    String userName;
    @NotNull
    @Size(max = 100)
    String email;
}