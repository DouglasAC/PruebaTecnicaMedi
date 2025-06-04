package douglas.pruebatecnicabackend.dtos;

import douglas.pruebatecnicabackend.models.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private String numeroIdentificacion;

}
