package com.softuni.coffeesshop.service;

import com.softuni.coffeesshop.model.entity.User;
import com.softuni.coffeesshop.model.service.UserServiceModel;
import com.softuni.coffeesshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

    List<UserViewModel> findAllUserAndCountOfOrdersOrderByCountDesc();
}
