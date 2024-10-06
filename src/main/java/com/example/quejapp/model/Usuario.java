package com.example.quejapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
public class Usuario implements UserDetails{
    private @Id
    @GeneratedValue Long id;

    @NotNull(message="Nombres con un campo obligatorio.")
    @Size(min=2, max=255, message = "Nombres debe tener un minimo de información para ser valida.")
    private String nombre;

    @NotNull(message="Apellidos con un campo obligatorio")
    @Size(min=2, max=255, message = "Apellidos debe tener un minimo de información para ser valida.")
    private String apellido;

    @NotNull(message="Usuario es un campo obligatorio.")
    @Size(min=2, max=255, message = "Usuario debe tener un minimo de información para ser valida.")
    private String nickname;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotNull(message="Debe seleccionar genero")
    private Integer genero;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @NotNull(message="contraseña es un campo obligatorio.")
    @Size(min=2, max=255, message = "contraseña debe tener un minimo de información para ser valida.")
    private String password;

    public Usuario(){}

    public Usuario(String nombre, String apellido, String nickname, String email, Date fechaNacimiento, Integer genero, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.rol = Rol.valueOf(rol);
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        return rol.name();
    }

    public void setRol(String rol) {
        this.rol = Rol.valueOf(rol);
    }

    // Implementacion de UserDetails (Spring boot Security)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_"+rol.name()));
        return roles;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Implementacion de Serializable

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario1 = (Usuario) o;
        return Objects.equals(id, usuario1.id) && Objects.equals(nombre, usuario1.nombre)
                && Objects.equals(apellido, usuario1.apellido) && Objects.equals(nickname, usuario1.nickname)
                && Objects.equals(email, usuario1.email) && Objects.equals(fechaNacimiento, usuario1.fechaNacimiento)
                && Objects.equals(genero, usuario1.genero) && Objects.equals(password, usuario1.password)
                && Objects.equals(rol, usuario1.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, nickname, email, fechaNacimiento, genero, password, rol);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", usuario='" + nickname + '\'' +
                ", rol='" + rol + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", genero=" + genero +
                '}';
    }
}
