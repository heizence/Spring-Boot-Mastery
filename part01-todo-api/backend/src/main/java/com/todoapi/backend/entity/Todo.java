package com.todoapi.backend.entity;

// jakarta.persistence are JPA annotations used to map the class to a database table.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "todos") // Explicitly specifies the database table name.
@Getter
@Setter
@NoArgsConstructor // Adds a no-argument constructor, which is required by JPA.
public class Todo {
    // Specifies how the primary key is generated.
    @Id // Marks the id field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the way the ID is generated. DB auto-increment.
    private Long id;

    @Column(nullable = false) // Marks the corresponding DB column as NOT NULL(Colum constraint).
    private String title;

    @Column // You can omit attributes if you just want to mark it as a column.
    private String description;

    @Column(nullable = false)
    private boolean completed = false; // It's good practice to provide a default value.
}
