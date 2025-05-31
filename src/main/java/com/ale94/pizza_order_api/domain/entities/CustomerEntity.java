package com.ale94.pizza_order_api.domain.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, length = 40)
    private String address;
    @Column(nullable = false, length = 20)
    private String phone;
    @Column(length = 30)
    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER, 
        orphanRemoval = true, 
        mappedBy = "customer"
    )
    private List<OrderEntity> orders;

}
