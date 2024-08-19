package com.example.quejapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;


@Entity
public class Usuario {
    private @Id
    @GeneratedValue Long id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private Date fechaNacimiento;
    private Integer genero;
    private String rol;
    private String password;

    public Usuario(){}

    public Usuario(String nombre, String apellido, String usuario, String email, Date fechaNacimiento, Integer genero, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.rol = rol;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getGenero() {
        return genero;
    }

    public void setGenero(Integer genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(id, usuario1.id) && Objects.equals(nombre, usuario1.nombre)
                && Objects.equals(apellido, usuario1.apellido) && Objects.equals(usuario, usuario1.usuario)
                && Objects.equals(email, usuario1.email) && Objects.equals(fechaNacimiento, usuario1.fechaNacimiento)
                && Objects.equals(genero, usuario1.genero) && Objects.equals(password, usuario1.password)
                && Objects.equals(rol, usuario1.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, usuario, email, fechaNacimiento, genero, password, rol);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", usuario='" + usuario + '\'' +
                ", rol='" + rol + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", genero=" + genero +
                '}';
    }
}
