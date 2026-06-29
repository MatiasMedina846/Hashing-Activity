package com.example.demo.controller;

import com.example.demo.dto.request.usuarioCreateDto;
import com.example.demo.dto.response.usuarioResponseDto;
import com.example.demo.service.interfaces.usuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor

public class usuarioController {

    private final usuarioService us;

    @PostMapping
    public ResponseEntity<usuarioResponseDto> crear(@RequestBody usuarioCreateDto dto) {
        return ResponseEntity.ok(us.crearUsuario(dto));
    }

}
