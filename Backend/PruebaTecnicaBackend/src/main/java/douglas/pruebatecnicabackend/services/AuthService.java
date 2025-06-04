package douglas.pruebatecnicabackend.services;

import douglas.pruebatecnicabackend.dtos.LoginRequest;
import douglas.pruebatecnicabackend.dtos.LoginResponse;
import douglas.pruebatecnicabackend.exception.UsuarioNoEncontradoException;
import douglas.pruebatecnicabackend.models.Usuario;
import douglas.pruebatecnicabackend.repositories.UsuarioRepository;
import douglas.pruebatecnicabackend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado, username: " + request.getUsername()));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(usuario.getUsername());
        return new LoginResponse(token);
    }

}
