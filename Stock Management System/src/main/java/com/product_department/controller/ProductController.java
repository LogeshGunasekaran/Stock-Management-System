package com.product_department.controller;

import com.product_department.dto.ProductDto;
import com.product_department.entity.Product;
import com.product_department.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PutMapping("/update-product")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductDto product)
    {
        return new ResponseEntity<>(service.updateExistingProduct(product) , HttpStatus.OK);
    }

    @DeleteMapping("/deleteby-name")
    public ResponseEntity<String> deleteByProductName(@RequestParam("pdt-name") String pname)
    {
        return new ResponseEntity<>(service.deleteByProductName(pname) , HttpStatus.OK);
    }

}
