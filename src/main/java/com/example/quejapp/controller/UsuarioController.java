package com.example.quejapp.controller;

import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.repositories.UsuarioRepository;
import com.example.quejapp.util.UsuarioModelAssembler;
import com.example.quejapp.util.UsuarioNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UsuarioController {
    private final UsuarioRepository repositorio;
    private final UsuarioModelAssembler ensamblador;

    public UsuarioController(UsuarioRepository repository, UsuarioModelAssembler ensamblador) {
        this.repositorio = repository;
        this.ensamblador = ensamblador;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<Usuario>> listarUsuarios() {
        List<EntityModel<Usuario>> usuarios = repositorio.findAll().stream()
                .map(ensamblador::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios, linkTo(methodOn(UsuarioController.class).listarUsuarios()).withSelfRel());
    }

    @PostMapping("/users")
    public ResponseEntity<?> agregarNuevoUsuario(@RequestBody Usuario nuevoUsuario) {
        EntityModel<Usuario> modeloDeEntidad = ensamblador.toModel(repositorio.save(nuevoUsuario));
        return ResponseEntity
                .created(modeloDeEntidad.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(modeloDeEntidad);
    }

    @GetMapping("/users/{id}")
    public EntityModel<Usuario> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = repositorio.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
        return ensamblador.toModel(usuario);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> modificarUsuario(@RequestBody Usuario nuevoUsuario, @PathVariable Long id) {
        Usuario usuarioActualizado = repositorio.findById(id)
                .map(usuario -> {
                    usuario.setNombre(nuevoUsuario.getNombre());
                    usuario.setApellido(nuevoUsuario.getApellido());
                    usuario.setUsuario(nuevoUsuario.getUsuario());
                    usuario.setPassword(nuevoUsuario.getPassword());
                    usuario.setEmail(nuevoUsuario.getEmail());
                    return repositorio.save(usuario);
                })
                .orElseGet(() -> {
                    return repositorio.save(nuevoUsuario);
                });

        EntityModel<Usuario> modeloDeEntidad = ensamblador.toModel(usuarioActualizado);

        return  ResponseEntity
                .created(modeloDeEntidad.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(modeloDeEntidad);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        repositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
