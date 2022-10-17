package com.piotrzawada.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class RecipesController {

    RecipesService recipesService;

    @Autowired
    UserService userService;


    public RecipesController(@Autowired RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCurrentRecipe(@PathVariable long id) {
        Optional<Recipe> recipe = recipesService.getRecipe(id);

        if (recipe.isEmpty()) {
            return new ResponseEntity<>("(Not found)", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<?> searchRecipe(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String category) {

        if (name == null && category == null || name != null && category != null){    //must add some in case when parameters are not valid
            return new ResponseEntity<>("(Bad Request)", HttpStatus.BAD_REQUEST);
        }

        if (category == null) {
            return new ResponseEntity<>(recipesService.recipeByName(name), HttpStatus.OK);
        }

        return new ResponseEntity<>(recipesService.recipeByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> postRecipe(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody Recipe postRecipe) {
        String userName = userDetails.getUsername();
        User user = userService.getByEmail(userName);
        postRecipe.setUser(user);
        recipesService.saveRecipe(postRecipe);
        return new ResponseEntity<>(Map.of("id", postRecipe.id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@AuthenticationPrincipal UserDetails userDetails ,@PathVariable long id) {
        String userName = userDetails.getUsername();
        Optional<Recipe> recipe = recipesService.getRecipe(id);

        if (recipe.isPresent()) {
            if (recipe.get().getUser().email.equals(userName)) {
                recipesService.deleteRecipe(id);
                return new ResponseEntity<>("(No Content)", HttpStatus.NO_CONTENT);
            } else { return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
        }
        return new ResponseEntity<>("(Not found)", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putRecipe(@AuthenticationPrincipal UserDetails userDetails,
                                       @Valid @RequestBody Recipe putRecipe, @PathVariable long id) {
        String userName = userDetails.getUsername();
        User user = userService.getByEmail(userName);
        Optional<Recipe> recipe = recipesService.getRecipe(id);

        if (recipe.isPresent()) {
            if (recipe.get().getUser().email.equals(userName)) {
                putRecipe.setId(id);
                putRecipe.setUser(user);
                recipesService.saveRecipe(putRecipe);
                return new ResponseEntity<>("(No Content)", HttpStatus.NO_CONTENT);
            } else { return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
        }
        return new ResponseEntity<>("(Not found)", HttpStatus.NOT_FOUND);
    }
}



