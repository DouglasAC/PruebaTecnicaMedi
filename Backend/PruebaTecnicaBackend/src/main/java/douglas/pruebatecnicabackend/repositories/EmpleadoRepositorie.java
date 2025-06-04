package douglas.pruebatecnicabackend.repositories;

import douglas.pruebatecnicabackend.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorie extends JpaRepository<Empleado, Integer> {
}
