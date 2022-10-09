package com.piotrzawada.Recipes;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User getByEmail(String email);
}
