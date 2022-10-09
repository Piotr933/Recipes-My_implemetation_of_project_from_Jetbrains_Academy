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

    public void registerUser(User user) {
        userRepo.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }
        return new UserDetailsImpl(user);
    }
    public boolean userExist(String email) {
        for (User user : userRepo.findAll()) {
            if (user.email.equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User getByEmail(String email) {
        return userRepo.getByEmail(email);
    }
}

