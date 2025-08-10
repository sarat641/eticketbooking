package com.example.eticketbooking.service;

import com.example.eticketbooking.api.UserService;
import com.example.eticketbooking.dao.User;
import com.example.eticketbooking.dao.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
