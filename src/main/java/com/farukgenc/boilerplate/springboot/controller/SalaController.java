package com.farukgenc.boilerplate.springboot.controller;

import com.farukgenc.boilerplate.springboot.model.Sala;
import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.security.service.SalaService;
import com.farukgenc.boilerplate.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private final SalaService salaService;
    @Autowired
    private final UserService userService;

    public SalaController(SalaService salaService, UserService userService) {
        this.salaService = salaService;
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<Sala>> getSalas() {
        return ResponseEntity.ok(salaService.findAll());
    }


    @PostMapping
    public ResponseEntity<Sala> registrarSala(@RequestBody Sala sala, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        sala.setUser(user);
        Sala nuevaSala = salaService.save(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSala);
    }
    /*@PostMapping
    public ResponseEntity<Sala> registrarSala(@RequestBody Sala sala) {
        if (sala == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(salaService.save(sala));
    }*/


}
