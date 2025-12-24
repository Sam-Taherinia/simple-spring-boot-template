package com.tromaya.simple_spring_boot_template.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "users") // name must be users and not user!
@Entity
@AllArgsConstructor
@NoArgsConstructor // Default constructor is required for JPA
@Setter
@Getter
@Builder
public class User implements UserDetails { // UserDetails is a way to configure authentication using Spring Security

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

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    private String role;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "borrower", cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // mappedBy is only used on the non-owning side of a relationship
    private List<Book> borrowedBooks;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
