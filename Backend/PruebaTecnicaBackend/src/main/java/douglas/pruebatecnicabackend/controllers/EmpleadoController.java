package douglas.pruebatecnicabackend.controllers;

import douglas.pruebatecnicabackend.dtos.EmpleadoDTO;
import douglas.pruebatecnicabackend.dtos.EmpleadoResponseDTO;
import douglas.pruebatecnicabackend.services.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los empleados")
    public ResponseEntity<List<EmpleadoResponseDTO>> listar() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un empleado por su ID")
    public ResponseEntity<EmpleadoResponseDTO> obtener(@PathVariable int id) {
        return ResponseEntity.ok(empleadoService.obtenerEmpleadoPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo empleado")
    public ResponseEntity<EmpleadoResponseDTO> crear(@RequestBody @Valid EmpleadoDTO empleadoDto) {
        return ResponseEntity.ok(empleadoService.crearEmpleado(empleadoDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un empleado por su ID")
    public ResponseEntity<EmpleadoResponseDTO> actualizar(@PathVariable int id, @RequestBody EmpleadoDTO empleadoDto) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(id, empleadoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

}
