package com.example.quejapp.DTOs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReportDTO {
    private Long id;
    private Date fecha;
    private String nombres;
    private String apellidos;
    private String descripcion;
    private Integer tipoQueja;
    private String ubicacion;
    private Date fechaRespuesta;

    @NotNull(message="Debe seleccionar el tipo de incidente")
    private Integer estado;

    @NotNull(message="La respuesta es un campo obligatorio, por favor comente sobre el estado de la queja.")
    @Size(min=2, max=255,  message="la respuesta debe tener un minimo de información para ser valida.")
    private String respuesta;

    public ReportDTO(
            Long id, Date fecha, String nombres, String apellidos, String descripcion,
            Integer tipoQueja, String ubicacion, Date fechaRespuesta,
            Integer estado, String respuesta
    ) {
        this.id = id;
        this.fecha = fecha;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.descripcion = descripcion;
        this.tipoQueja = tipoQueja;
        this.ubicacion = ubicacion;
        this.fechaRespuesta = fechaRespuesta;
        this.estado = estado;
        this.respuesta = respuesta;
    }

    public ReportDTO() {

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String obtenerNombreEstado() {
        if (estado == null){
            estado = 0;
        }
        return switch (estado) {
            case 1 -> "Nuevo";
            case 2 -> "Revisión";
            case 3 -> "Solucionado";
            default -> "Sin asignar";
        };
    }

    public String obtenerClaseEstado() {
        if (estado == null){
            estado = 0;
        }
        return switch (estado) {
            case 1 -> "text-bg-primary";
            case 2 -> "text-bg-warning";
            case 3 -> "text-bg-success";
            default -> "text-bg-secondary";
        };
    }


    public String obtenerNombreTipoQueja() {
        return switch (tipoQueja) {
            case 1 -> "Servicio al Cliente";
            case 2 -> "Falla en el Servicio";
            case 3 -> "Retrasos en el Servicio";
            default -> "Otros";
        };
    }
}
