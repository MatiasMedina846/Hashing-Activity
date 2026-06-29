package com.example.demo.service.impl;

import com.example.demo.dto.request.usuarioCreateDto;
import com.example.demo.dto.response.usuarioResponseDto;
import com.example.demo.models.usuario;
import com.example.demo.service.interfaces.usuarioService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class usuarioServiceImpl implements usuarioService {

    private final AtomicLong generadorIds = new AtomicLong( 1);

    @Override
    public usuarioResponseDto crearUsuario(usuarioCreateDto dto) {

        usuario us = usuario.builder()
                .id(generadorIds.getAndIncrement())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .contra(dto.getContra())
                .build();

        return usuarioResponseDto.builder()
                .id(us.getId())
                .nombreCompleto(us.getNombre()+ " " + us.getApellido())
                .contra(us.getContra())
                .build();

    }
}
