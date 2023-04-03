    package com.gestion.usuarios.gestionusuarios.controller;


import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.usuarios.gestionusuarios.excepcions.NotFound;
import com.gestion.usuarios.gestionusuarios.model.Usuario;
import com.gestion.usuarios.gestionusuarios.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/") /* Notacion para dar un prefijo a la URL */
public class UsuarioController {
    
    @Autowired UsuarioRepository uRepository;

    @GetMapping(value = "/usuarios")
    public List<Usuario> getUsuarios(){
        return uRepository.findAll();
    }

    @GetMapping(value = "/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id){
       Usuario usuario = uRepository.findById(id)
                         .orElseThrow( () -> new NotFound("No existe el usuario con el id: " + id));
        
        return ResponseEntity.ok( usuario);
    } 

    @PostMapping(value = "/nuevo-usuario")
    public Usuario nuevoUsuario(@RequestBody Usuario usuario){
        
        return uRepository.save(usuario);
    }

    @PutMapping(value = "/editar-usuario/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id ,@RequestBody Usuario usuarioActualizar){

        Usuario usuario = uRepository.findById(id)
        .orElseThrow( () -> new NotFound("No existe el usuario con el id: " + id));

        usuario.setNombre(usuarioActualizar.getNombre());
        usuario.setApellido(usuarioActualizar.getApellido());
        usuario.setEmail(usuarioActualizar.getEmail());
        usuario.setTelefono(usuarioActualizar.getTelefono());
        usuario.setPassword(usuarioActualizar.getPassword());
        

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping(value = "/usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id){
        uRepository.deleteById(id);
    }


}
