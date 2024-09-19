package com.farukgenc.boilerplate.springboot.controller;


import com.farukgenc.boilerplate.springboot.model.Reserva;
import com.farukgenc.boilerplate.springboot.model.Sala;
import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.repository.ReservaRepository;
import com.farukgenc.boilerplate.springboot.security.service.ReservaService;
import com.farukgenc.boilerplate.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservar")
public class ReservaController {

    @Autowired
    private final ReservaService reservaService;

    @Autowired
    private final UserService userService;

    public ReservaController(ReservaService reservaService, UserService userService) {
        this.reservaService = reservaService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Reserva> getReserva(Reserva reserva) {
        return ResponseEntity.ok(reservaService.save(reserva));
    }


    @PostMapping
    public ResponseEntity<String> reservarSala(@RequestBody Reserva reserva, Authentication authentication) {
        // Verifica la autenticación
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Verifica si el usuario tiene el rol "EMPLOYABLE"
        boolean isEmployable = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_EMPLOYABLE"));

        if (!isEmployable) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        // Lógica para reservar la sala
        // Por ejemplo, usar salaService para reservar la sala
        // ...

        return ResponseEntity.status(HttpStatus.CREATED).body("Sala reservada exitosamente.");
    }
}

