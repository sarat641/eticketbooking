package com.example.eticketbooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * @author Sarat
 */
public interface UserRepository extends JpaRepository<User, String> {
}
