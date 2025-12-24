package com.tromaya.simple_spring_boot_template.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "authors")
@Entity
@AllArgsConstructor
@NoArgsConstructor // Default constructor is required for JPA
@Setter
@Getter
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // when the id is null, JPA generates UUID for us
    @Column(name = "id",nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "middle_name",nullable = true)
    private String middleName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "biography",nullable = false)
    private String biography;

    @Column(name = "nationality",nullable = false)
    private String nationality;

    @Column(name = "birth_year",nullable = false)
    private int birthYear;

    @Column(name = "death_date",nullable = true)
    private int deathYear;

    @Column(name = "website",nullable = true, unique = true)
    private String website;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true) // mappedBy is only used on the non-owning side of a relationship
    private List<Book> writtenBooks;

}
