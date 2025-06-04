package douglas.pruebatecnicabackend.controller;

import douglas.pruebatecnicabackend.security.JwtUtil;
import douglas.pruebatecnicabackend.services.AuthService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuthControllerTestConfig {

    @Bean
    public AuthService authService() {
        return Mockito.mock(AuthService.class);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return Mockito.mock(JwtUtil.class);
    }
}
