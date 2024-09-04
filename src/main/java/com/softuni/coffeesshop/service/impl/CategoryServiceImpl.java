package com.softuni.coffeesshop.service.impl;

import com.softuni.coffeesshop.model.entity.Category;
import com.softuni.coffeesshop.model.entity.CategoryNameEnum;
import com.softuni.coffeesshop.repository.CategoryRepo;
import com.softuni.coffeesshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void initCategories() {
        if (categoryRepo.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    Category category = new Category();
                    category.setName(categoryNameEnum);
                    switch (categoryNameEnum) {

                        case COFFEE -> {category.setNeededTime(2);
                        }
                        case CAKE -> {category.setNeededTime(10);
                        }
                        case DRINK -> {category.setNeededTime(1);
                        }
                        case OTHER -> {category.setNeededTime(5);
                        }
                    }

                    categoryRepo.save(category);
                });
    }

    @Override
    public Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum) {

        return categoryRepo.findByName(categoryNameEnum)
                .orElse(null);
    }
}
