package com.piotrzawada.Recipes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class RecipesService {
    private final RecipesRepo recipesRepo;

    public Optional<Recipe> getRecipe(Long id){
        return recipesRepo.findById(id);
    }

    public List<Recipe> recipeByName(String name) {
         return recipesRepo.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);

    }
    public List<Recipe> recipeByCategory(String category) {
        return recipesRepo.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    @Transactional
    public void saveRecipe(Recipe recipe) {
        recipesRepo.save(recipe);
    }

    @Transactional
    public void deleteRecipe(Long id) {
        recipesRepo.deleteById(id);
    }
}
