package com.piotrzawada.Recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
    @Id
    String email;
    @Size(min = 4)
    String password;

    @JsonIgnore
    String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
