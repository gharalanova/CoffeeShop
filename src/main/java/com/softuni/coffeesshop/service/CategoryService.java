package com.softuni.coffeesshop.service;

import com.softuni.coffeesshop.model.entity.Category;
import com.softuni.coffeesshop.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
