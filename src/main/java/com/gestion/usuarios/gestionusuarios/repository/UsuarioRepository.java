package com.gestion.usuarios.gestionusuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.usuarios.gestionusuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
