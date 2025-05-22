package org.example.Controllers; // Veya daha spesifik bir alt paket, örn. org.example.controller

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hello() {
        return "Herkese Merhaba Jenkins!!!! ";
    }
}