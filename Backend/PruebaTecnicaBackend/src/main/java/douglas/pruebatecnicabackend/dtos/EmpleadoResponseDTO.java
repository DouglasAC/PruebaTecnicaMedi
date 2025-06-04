package douglas.pruebatecnicabackend.dtos;

import douglas.pruebatecnicabackend.models.Genero;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoResponseDTO {
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private String numeroIdentificacion;
}
