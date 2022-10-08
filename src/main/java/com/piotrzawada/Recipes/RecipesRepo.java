package com.piotrzawada.Recipes;

import org.springframework.data.repository.CrudRepository;

public interface RecipesRepo extends CrudRepository<Recipe, Long> {
}
