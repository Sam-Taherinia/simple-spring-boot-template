package com.tromaya.simple_spring_boot_template.repositories;

import com.tromaya.simple_spring_boot_template.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {}
