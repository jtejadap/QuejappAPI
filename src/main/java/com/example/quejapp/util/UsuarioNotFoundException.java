package com.example.quejapp.util;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("No se puede encontrar usuario " + id);
    }
}
