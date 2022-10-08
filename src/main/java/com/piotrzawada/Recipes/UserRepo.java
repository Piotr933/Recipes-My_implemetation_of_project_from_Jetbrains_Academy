package com.piotrzawada.Recipes;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, String> {
    User findByEmail(String email);
}
