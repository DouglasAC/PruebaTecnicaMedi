package douglas.pruebatecnicabackend.repositories;

import douglas.pruebatecnicabackend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorie extends JpaRepository<Usuario, Integer> {
}
