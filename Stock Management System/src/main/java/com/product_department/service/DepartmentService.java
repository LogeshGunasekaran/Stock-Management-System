package com.product_department.service;

import com.product_department.dto.DepartmentDto;
import com.product_department.entity.Department;
import com.product_department.entity.Product;
import com.product_department.exceptions.CouldNotAddException;
import com.product_department.exceptions.NoContentAvailableException;
import com.product_department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class DepartmentService {

    /* class data */
    private DepartmentRepository departmentRepository;
    private String productId = null;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    /* adding new product with department */
    public Department addProducts(DepartmentDto departmentDto )  {

         Department department = new Department();
         List<Product> productListToAdd = new ArrayList<>();

         if(departmentDto.getProducts()!= null)
             {
                 departmentDto.getProducts().stream()
                         .forEach(product -> productListToAdd.add(new Product(0, product.getProductName(),idGenerator(), product.getPrice(),
                                                                     product.getQuantity(),null, null, department)));
             }

        department.setDepartmentName(departmentDto.getDepartmentName());

        if(!productListToAdd.isEmpty())
            department.setProducts(productListToAdd);

        return departmentRepository.save(department);
    }

    /* get products by department */
    public Department getProductsByDepartment(String deptName) throws NoContentAvailableException {

        return departmentRepository.findAll().stream()
                    .filter(department1 -> deptName.equalsIgnoreCase(department1.getDepartmentName()))
                    .findAny().orElseThrow(()->
                                new NoContentAvailableException("Department :"+deptName , "Entered department is not found ...Enter valid department"));
    }


    /* add new products to the existing department */
    public List<Product> addNewProduct(Department newProduct) throws NoContentAvailableException {

        /*
        * fetch existing department detail by department name
        */
        Department  existingDept = getProductsByDepartment(newProduct.getDepartmentName());

        /*
        * add existing product regarding the department into list for distinct checking
        * and removing duplicates
        */
        List<String> oldProducts = new ArrayList<>();
        existingDept.getProducts().forEach(p-> oldProducts.add(p.getProductName()));

        List<Product> newProductList = new ArrayList<>(newProduct.getProducts());

        newProduct.getProducts().forEach((p)-> {if(oldProducts.contains(p.getProductName()))
                                {newProductList.remove(p);}} );

        List<Product> distinctList = new ArrayList<>();

        /*
         * creating new products and unique id
         */
        newProductList.forEach(p->
                 distinctList.add(new Product(0 , p.getProductName(),idGenerator(),p.getPrice(),p.getQuantity(),null,null,existingDept)));

        return ProductService.addNewProduct(distinctList)
                                               .orElseThrow(() -> new CouldNotAddException
                                                        ("Could not able to add a new products Something went wrong..!! "));



    }

    public String deleteDepartment(String deptName) throws NoContentAvailableException {

        Department productsByDepartment = getProductsByDepartment(deptName);
        departmentRepository.deleteById(productsByDepartment.getId());

        return "Deleted Successfully";
    }

    /*
    * product id generator
    */
    private String idGenerator()
    {
        List<Department> existingDepartment = departmentRepository.findAll();
        String lastProductId = ProductService.lastProductId();
        Pattern pattern = Pattern.compile("\\d+");

        if(productId == null)
        {
           if(existingDepartment.size()>0 && lastProductId != null)
              productId = lastProductId;
        }

       if (productId == null)
       {
           return productId = "PRODUCT_1";
       }
       else
       {
        Matcher matcher = pattern.matcher(productId);
        matcher.find();
        int newId = Integer.parseInt(matcher.group())+1;
        productId = new String("PRODUCT_"+newId);
        return productId;
       }
    }

}

