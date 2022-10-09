package com.piotrzawada.Recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 40)
    String name;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 40)
    String category;

    @UpdateTimestamp
    LocalDateTime date;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    String description;

    @NotEmpty
    @NotNull
    @Size(min = 1)
    @ElementCollection
    @OrderColumn
    String[] ingredients;

    @Size(min = 1)
    @NotNull
    @NotEmpty
    @ElementCollection
    @OrderColumn
    String[] directions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @NotEmpty
    @NotNull

    public Recipe(Long id, String name, String category, LocalDateTime date, String description,
                  @NotEmpty @NotNull @Size(min = 1) String[] ingredients, @Size(min = 1) @NotNull @NotEmpty
                  String[] directions) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
    }
}