package com.product_department.repository;

import com.product_department.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product , Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Product p set p.productName=:pname ,p.productId= :pId , p.price = :price , p.quantity = :qty WHERE p.productName = :pname")
    int updateSelectedProduct(String pname, String pId , Integer price , Integer qty);

    @Transactional
    int deleteByProductName(String productName);

    Product findByProductName(String productName);

}
