package com.product_department.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class DepartmentDto {

    @NotNull(message = "Field should be filled")
    private String departmentName;


    private List<ProductDto> products;



}
