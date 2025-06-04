package douglas.pruebatecnicabackend.repositories;

import douglas.pruebatecnicabackend.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
