package com.softuni.coffeesshop.service;

import com.softuni.coffeesshop.model.service.OrderServiceModel;
import com.softuni.coffeesshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrderPriceByPriceDesc();

    void readyOrder(Long id);
}
