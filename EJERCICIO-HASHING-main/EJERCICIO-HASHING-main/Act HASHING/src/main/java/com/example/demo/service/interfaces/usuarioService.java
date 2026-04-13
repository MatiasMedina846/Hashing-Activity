package com.example.demo.service.interfaces;

import com.example.demo.dto.request.usuarioCreateDto;
import com.example.demo.dto.response.usuarioResponseDto;

public interface usuarioService {

    usuarioResponseDto crearUsuario(usuarioCreateDto dto);
}
