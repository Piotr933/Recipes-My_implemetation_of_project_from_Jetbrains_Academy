package com.piotrzawada.Recipes;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipesRepo extends CrudRepository<Recipe, Long> {
    List<Recipe> findAllByNameContainingIgnoreCaseOrderByDateDesc(String name);

    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
}
