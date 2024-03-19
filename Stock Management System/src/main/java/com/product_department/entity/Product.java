package com.product_department.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_Name" , length = 20)
    private String productName;
    @Column(name = "product_id" ,unique = true , length = 20)
    private String productId;
    @Column(name = "price" , length = 10)

    private BigDecimal price;
    @Column(name = "quantity" , length = 20)
    private int quantity;
    @CreationTimestamp
    @Column(name = "createdTime")
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(name = "modifiedTime")
    private LocalDateTime modifiedTime;

    @ManyToOne(cascade = {CascadeType.DETACH ,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
     @JsonBackReference
    private Department department;


}
