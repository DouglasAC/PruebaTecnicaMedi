package douglas.pruebatecnicabackend.services;

import douglas.pruebatecnicabackend.dtos.EmpleadoDTO;
import douglas.pruebatecnicabackend.dtos.EmpleadoResponseDTO;
import douglas.pruebatecnicabackend.exception.EmpleadoNoEncontradoException;
import douglas.pruebatecnicabackend.exception.NumeroIdentificacionYaExisteException;
import douglas.pruebatecnicabackend.models.Empleado;
import douglas.pruebatecnicabackend.models.Genero;
import douglas.pruebatecnicabackend.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public EmpleadoResponseDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        //Verificar que no exista un empleado con el mismo número de identificación
        if(empleadoRepository.findAll()
                .stream()
                .anyMatch(empleado -> empleado.getNumeroIdentificacion().equals(empleadoDTO.getNumeroIdentificacion()))){
            throw new NumeroIdentificacionYaExisteException("Ya existe un empleado con el número de identificación: " + empleadoDTO.getNumeroIdentificacion());
        }
        //Guardar empleado
        Empleado empleado = mapearADominio(empleadoDTO);
        empleadoRepository.save(empleado);
        return mapearAResponse(empleado);
    }

    public List<EmpleadoResponseDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    public EmpleadoResponseDTO obtenerEmpleadoPorId(int id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("No se encontró al Empleado con ID: " + id));
        return mapearAResponse(empleado);
    }

    public EmpleadoResponseDTO actualizarEmpleado(int id, EmpleadoDTO empleadoDTO) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("No se encontró al Empleado con ID: " + id));

        //Si se cambia el número de identificación verificar que no exista un empleado con el mismo número de identificación
        if(!empleado.getNumeroIdentificacion().equals(empleadoDTO.getNumeroIdentificacion())){
            if(empleadoRepository.findAll()
                    .stream()
                    .anyMatch(emp -> emp.getNumeroIdentificacion().equals(empleadoDTO.getNumeroIdentificacion()))){
                throw new NumeroIdentificacionYaExisteException("Ya existe un empleado con el número de identificación: " + empleadoDTO.getNumeroIdentificacion());
            }
        }

        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setGenero(empleadoDTO.getGenero());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
        empleado.setNumeroIdentificacion(empleadoDTO.getNumeroIdentificacion());

        empleadoRepository.save(empleado);
        return mapearAResponse(empleado);
    }

    public void eliminarEmpleado(int id) {
        if(!empleadoRepository.existsById(id)) {
            throw new EmpleadoNoEncontradoException("No se encontró al Empleado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    private Empleado mapearADominio(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();

        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setGenero(dto.getGenero());
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setNumeroIdentificacion(dto.getNumeroIdentificacion());

        return empleado;
    }

    private EmpleadoResponseDTO mapearAResponse(Empleado empleado) {
        EmpleadoResponseDTO response = new EmpleadoResponseDTO();

        response.setId(empleado.getId());
        response.setNombre(empleado.getNombre());
        response.setApellido(empleado.getApellido());
        response.setGenero(empleado.getGenero());
        response.setFechaNacimiento(empleado.getFechaNacimiento());
        response.setNumeroIdentificacion(empleado.getNumeroIdentificacion());

        return response;
    }
}
