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

    public ArrayList<Recipe> recipeByName(String name) {
        ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

        for (Recipe recipe : recipesRepo.findAll()) {
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                recipeArrayList.add(recipe);
            }
        }
        Collections.reverse(recipeArrayList);
        return recipeArrayList;
    }

    public ArrayList<Recipe> recipeByCategory(String category) {
        ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();

        for (Recipe recipe : recipesRepo.findAll()) {
            if (recipe.getCategory().toLowerCase().equals(category.toLowerCase())) {
                recipeArrayList.add(recipe);
            }
        }
        Collections.reverse(recipeArrayList);
        return recipeArrayList;
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
