package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class usuarioCreateDto {

    @NotBlank (message = "Debe ingresar un nombre valido")
    private String nombre;

    @NotBlank (message = "Debe ingresar un apellido valido")
    private String apellido;

    @NotBlank (message = "Debe ingresar una contraseña valida")
    private String contra;

}
