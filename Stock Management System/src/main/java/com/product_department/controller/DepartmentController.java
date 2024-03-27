package com.product_department.controller;

import com.product_department.dto.DepartmentDto;
import com.product_department.entity.Department;
import com.product_department.entity.Product;
import com.product_department.exceptions.NoContentAvailableException;
import com.product_department.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    private DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /* add products and department */
    @PostMapping("/add-department-products")
    public ResponseEntity<Department> addproducts(@RequestBody@Valid DepartmentDto department ) {

        return new ResponseEntity<>(departmentService.addProducts(department) , HttpStatus.CREATED);
    }

    /* get products br dept */
    @GetMapping("/get-productsby-dept")
    public ResponseEntity<Department> getProducts(@RequestParam(name = "department") String department) throws NoContentAvailableException {
        return new ResponseEntity<>(departmentService.getProductsByDepartment(department),HttpStatus.OK);
    }

    /* add new product to the department */
    @PostMapping("/add-newproduct")
    public ResponseEntity<List<Product>>  addNewProduct(@RequestBody@Valid Department newProduct) throws NoContentAvailableException {
        return new ResponseEntity<>(departmentService.addNewProduct(newProduct) , HttpStatus.CREATED);
    }

    /*delete department by name */
    @DeleteMapping("/delete-department")
    public  ResponseEntity<String> deleteByName(@RequestParam String deptName) throws NoContentAvailableException {
        return  new ResponseEntity<>(deptName+" "+departmentService.deleteDepartment(deptName) , HttpStatus.OK);
    }
}
