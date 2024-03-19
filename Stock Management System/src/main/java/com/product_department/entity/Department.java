package com.product_department.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department"
        ,uniqueConstraints = {@UniqueConstraint(columnNames = {"department"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "department"  , length = 20)
    private String departmentName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id" , referencedColumnName = "id" )
    private List<Product> products;


}
