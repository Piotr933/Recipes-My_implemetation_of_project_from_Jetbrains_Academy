package com.piotrzawada.Recipes;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    private final RecipesRepo recipesRepo;
    public void registerUser(User user) {
        userRepo.save(user);
    }

    public boolean userExist(String name) {
        return userRepo.existsById(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }
        return new UserDetailsImpl(user);
    }
    public User getUserTest(String name) {
        return userRepo.findByEmail(name);
    }

    public void assignRecipe(String email , Long id){
        User user = userRepo.findByEmail(email);
    }
}

