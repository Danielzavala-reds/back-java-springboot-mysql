package com.gestion.usuarios.gestionusuarios.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.usuarios.gestionusuarios.model.Usuario;
import com.gestion.usuarios.gestionusuarios.repository.UsuarioRepository;

@RestController
public class UsuarioController {
    
    @Autowired UsuarioRepository uRepository;

    @GetMapping(value = "/api/usuarios")
    public List<Usuario> getUsuarios(){
        return uRepository.findAll();
    }

    @GetMapping(value = "/api/usuarios/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
        return uRepository.findById(id);
    } 

    @PostMapping(value = "/api/nuevo-usuario")
    public String nuevoUsuario(@RequestBody Usuario usuario){
        uRepository.save(usuario);
        
        return "";
    }

    @PutMapping(value = "/api/editar-usuario/{id}")
    public String editarUsuario(@PathVariable Long id ,@RequestBody Usuario usuario){
        Usuario actualizar = uRepository.findById(id).get();
        actualizar.setNombre(usuario.getNombre());
        actualizar.setApellido(usuario.getApellido());
        actualizar.setEmail(usuario.getEmail());
        actualizar.setTelefono(usuario.getTelefono());
        actualizar.setPassword(usuario.getPassword());

        uRepository.save(actualizar);

        return "";
    }

    @DeleteMapping(value = "/api/usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id){
        uRepository.deleteById(id);
    }


}
