package com.marcelo.belezapura.controller.mappers;


import com.marcelo.belezapura.controller.dtos.UsuarioRequestDTO;
import com.marcelo.belezapura.controller.dtos.UsuarioResponseDTO;
import com.marcelo.belezapura.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toResponse(Usuario usuario);
}
