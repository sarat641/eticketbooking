package com.example.eticketbooking.resource;

import com.example.eticketbooking.dao.User;
import com.example.eticketbooking.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling e-ticket booking related requests.
 * This class contains endpoints for the e-ticket booking application.
 * @author Sarat
 */
@RestController
@AllArgsConstructor
public class EticketBookingController {

    private final UserServiceImpl userService;

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello E Ticket Booking";
    }
    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
