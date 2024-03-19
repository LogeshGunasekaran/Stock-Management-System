package com.product_department.service;

import com.product_department.dto.ProductDto;
import com.product_department.entity.Product;
import com.product_department.exceptions.NoContentAvailableException;
import com.product_department.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    /* class data */
    private static ProductRepository repository;
    private String productId =null;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }




    /*
    * add new products to the existing department
    */
    public static Optional<List<Product>> addNewProduct(List<Product> newProducts)  {

        Optional<List<Product>> optionalProducts = Optional.of(repository.saveAll(newProducts));

        return optionalProducts;
    }

    public Product updateExistingProduct(ProductDto product)
    {
        Product existingProduct = repository.findByProductName(product.getProductName());
        String pId = existingProduct.getProductId();

        if(existingProduct != null) {
            existingProduct.setProductId(existingProduct.getProductId());
            existingProduct.setProductName(product.getProductName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setProductId(pId);
            return repository.save(existingProduct);
        }
        else
        {
            throw new NoContentAvailableException("Product :"+product.getProductName()
                                                  ,"Given product is not existing . Enter the valid product to Update"  );
        }
    }



    @Transactional
    public String deleteByProductName( String pname)
    {
        if(repository.deleteByProductName(pname)>0)
            return "Deleted Successfully";

        else return "Deletion failed";
    }


    public static String lastProductId()
    {
        List<Product> productList = repository.findAll();
        if(productList.isEmpty())
            return null;
        else
            return productList.getLast().getProductId();

    }

    /*
    * product id generator
    */
    private String idGenerator()
    {
        Pattern pattern = Pattern.compile("\\d+");
        if(productId == null) {
            productId = lastProductId();
        }

        if (productId == null)
        {
            productId = "PRODUCT_1";
            return productId;
        }
        else
        {
            Matcher matcher = pattern.matcher(productId);
            matcher.find();
            int id = Integer.parseInt(matcher.group())+1;
            productId = new String("PRODUCT_"+id);
            return productId;
        }
    }
}
