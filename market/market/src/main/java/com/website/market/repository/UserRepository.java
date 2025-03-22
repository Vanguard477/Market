package com.website.market.repository;

import com.website.market.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"cart.cartItems"})
    Optional<User> findUserWithCartById(String id);

    @EntityGraph(attributePaths = {"cart.cartItems"})
    Optional<User> findUserWithCartByUsername(String username);


}
