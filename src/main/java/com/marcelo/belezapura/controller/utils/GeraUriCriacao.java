package com.marcelo.belezapura.controller.utils;

import com.marcelo.belezapura.model.Usuario;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class GeraUriCriacao {

    public static URI gerarUri(UUID id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("id")
                .buildAndExpand(id)
                .toUri();
    }
}
