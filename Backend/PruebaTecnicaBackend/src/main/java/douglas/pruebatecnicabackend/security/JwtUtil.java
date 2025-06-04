package douglas.pruebatecnicabackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.*;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String KEY_FILE = "jwt.key";
    private static final long EXPIRACION_MS = 86400000;

    private Key key;

    @PostConstruct
    public void init() {
        try {
            Path keyPath = Paths.get(KEY_FILE);

            if (Files.exists(keyPath)) {
                // Leer la clave desde el archivo
                String base64Key = Files.readString(keyPath);
                byte[] keyBytes = Base64.getDecoder().decode(base64Key);
                this.key = Keys.hmacShaKeyFor(keyBytes);
                System.out.println("[JWT] Clave cargada desde archivo.");
            } else {
                // Generar clave nueva y guardar
                this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
                String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
                Files.writeString(keyPath, base64Key, StandardOpenOption.CREATE);
                System.out.println("[JWT] Clave generada y guardada en archivo.");
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar o crear la clave JWT", e);
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION_MS))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
