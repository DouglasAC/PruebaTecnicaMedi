package douglas.pruebatecnicabackend.controller;

import douglas.pruebatecnicabackend.security.JwtUtil;
import douglas.pruebatecnicabackend.services.EmpleadoService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class EmpleadoCotrollerTestConfig {

    @Bean
    public EmpleadoService empleadoService() {
        return Mockito.mock(EmpleadoService.class);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return Mockito.mock(JwtUtil.class);
    }

}
