package com.product_department.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    @NotNull(message = "Field cannot be null")
    private String name;
    @NotNull(message = "Cannot be Null")
    @Min(value = 4 , message = "password should minium size of 4 characters")
        private String password;

    private final String role = "user";
    private final String authority = "user";
}
