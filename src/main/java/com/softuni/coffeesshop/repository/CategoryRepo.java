package com.softuni.coffeesshop.repository;

import com.softuni.coffeesshop.model.entity.Category;
import com.softuni.coffeesshop.model.entity.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//dostypvame entitito koeto svyrzva i vida na klucha
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
        Optional<Category> findByName(CategoryNameEnum name);
}
