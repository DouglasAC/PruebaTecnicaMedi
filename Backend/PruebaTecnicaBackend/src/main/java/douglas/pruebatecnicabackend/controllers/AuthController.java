package douglas.pruebatecnicabackend.controllers;

import douglas.pruebatecnicabackend.dtos.LoginRequest;
import douglas.pruebatecnicabackend.dtos.LoginResponse;
import douglas.pruebatecnicabackend.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
