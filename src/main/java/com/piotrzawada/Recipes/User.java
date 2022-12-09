package com.piotrzawada.Recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)" +
            "+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
    String email;

    @Size(min = 8)
    @NotBlank
    String password;

    @JsonIgnore
    String role;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
