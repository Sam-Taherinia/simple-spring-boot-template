package com.tromaya.simple_spring_boot_template.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "books")
@Entity
@AllArgsConstructor
@NoArgsConstructor // Default constructor is required for JPA
@Setter
@Getter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // when the id is null, JPA generates UUID for us
    @Column(name = "id",nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "isbn",nullable = false, unique = true)
    private String isbn;

    @Column(name = "description",nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY) // @ManyToOne does NOT use mappedBy. mappedBy is only used on the non-owning side of a relationship
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "published_year",nullable = true)
    private int publishedYear;

    @Column(name = "language",nullable = true)
    private String language;

    @Column(name = "price",nullable = true)
    private double price;

    @Column(name = "page_count",nullable = true)
    private int pages;

    @Column(name = "weight",nullable = true)
    private double weight;

    @Column(name = "rating",nullable = true)
    private double rating;

    @Column(name = "stock",nullable = true)
    private int leftInStock;

}
