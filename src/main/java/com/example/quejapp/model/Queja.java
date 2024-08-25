package com.example.quejapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.Objects;

@Entity
public class Queja {
    private @Id
    @GeneratedValue Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @NotNull(message="La descripción es un campo obligatorio, por favor comentenos su incidente.")
    @Size(min=2, max=255,  message="la descripcion debe tener un minimo de información para ser valida.")
    private String descripcion;

    @NotNull(message="Debe seleccionar el tipo de incidente")
    private Integer tipoQueja;

    @NotNull(message="La ubicación es un campo obligatorio, por favor comentenos donde ocurrio el incidente.")
    @Size(min=2, max=255, message = "la ubicación debe tener un minimo de información para ser valida.")
    private String ubicacion;
    private Long usuarioId;

    public Queja() {
        this.fecha = new Date();
    }
    public Queja(Date fecha, String descripcion, Integer tipoQueja, String ubicacion,Long usuarioId) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.tipoQueja = tipoQueja;
        this.ubicacion = ubicacion;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTipoQueja() {
        return tipoQueja;
    }

    public void setTipoQueja(Integer tipoQueja) {
        this.tipoQueja = tipoQueja;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queja queja = (Queja) o;
        return Objects.equals(id, queja.id) && Objects.equals(fecha, queja.fecha) && Objects.equals(descripcion, queja.descripcion)
                && Objects.equals(tipoQueja, queja.tipoQueja) && Objects.equals(ubicacion, queja.ubicacion)
                && Objects.equals(usuarioId, queja.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, descripcion, tipoQueja, ubicacion, usuarioId);
    }

    @Override
    public String toString() {
        return "Queja{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", TipoQueja=" + tipoQueja +
                ", ubicacion='" + ubicacion + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
