package com.example.eticketbooking.api;

import com.example.eticketbooking.dao.User;

import java.util.List;

/**
 * Interface for user-related operations.
 * @author Sarat
 */
public interface UserService {
    List<User> getAllUsers();
}
