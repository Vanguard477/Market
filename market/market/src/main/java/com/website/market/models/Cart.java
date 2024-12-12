package com.website.market.models;

import com.website.market.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;


@Getter
@Setter
@Entity
@Table (name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    private User user;
}
