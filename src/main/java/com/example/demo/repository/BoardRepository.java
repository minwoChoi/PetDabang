package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board; // Adjust package name accordingly

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    // You can add custom query methods here if needed

    // Example:
    // List<Board> findByTitle(String title);
}
