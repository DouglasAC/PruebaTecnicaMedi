package douglas.pruebatecnicabackend.controller;


import douglas.pruebatecnicabackend.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(EmpleadoController.class)
@Import(EmpleadoCotrollerTestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmpleadoCotrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmpleadoService empleadoService;
/*
    @Test
    void crear_empleado() throws Exception {
        EmpleadoDTO requestDTO = new EmpleadoDTO();
        requestDTO.setNombre("Juan Pérez");
        requestDTO.setFechaNacimiento(LocalDate.parse("2025-06-04"));
        requestDTO.setGenero(Genero.MASCULINO);
        requestDTO.setNumeroIdentificacion("123456");

        // Simular la respuesta esperada
        EmpleadoDTO responseDTO = new EmpleadoDTO();
        responseDTO.setNombre("Juan Pérez");
        responseDTO.setFechaNacimiento(LocalDate.parse("2025-06-04"));
        responseDTO.setGenero(Genero.MASCULINO);
        responseDTO.setNumeroIdentificacion("123456");


        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "nombre": "Douglas123",
                         "apellido": "Aguilar123",
                         "fechaNacimiento": "1997-05-03",
                         "genero": "MASCULINO",
                         "numeroIdentificacion": "ABC12345123"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Douglas123"))
                .andExpect(jsonPath("$.numeroIdentificacion").value("ABC12345123"));
    }*/

}
