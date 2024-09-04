package com.softuni.coffeesshop.repository;

import com.softuni.coffeesshop.model.entity.Order;
import com.softuni.coffeesshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderByPriceDesc();

}
