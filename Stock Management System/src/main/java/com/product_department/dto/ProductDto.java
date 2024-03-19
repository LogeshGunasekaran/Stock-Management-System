package com.product_department.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter@Setter
public class ProductDto {

    @NotNull(message = "Fill the field")
    @Pattern(regexp = "^\\p{Alnum}$" , message = "enter a valid product name" )
    private String productName;

    private String productId;
    @NotNull(message = "Enter the price")
    private BigDecimal price;
    @NotNull(message = "Enter the Quantity")
    private int quantity;


}
