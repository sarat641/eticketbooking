package com.example.eticketbooking.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller for handling e-ticket booking related requests.
 * This class contains endpoints for the e-ticket booking application.
 * @author Sarat
 */
@RestController
public class EticketBookingController {

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello E Ticket Booking";
    }
}
