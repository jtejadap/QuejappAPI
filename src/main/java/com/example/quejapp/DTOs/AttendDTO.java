package com.example.quejapp.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AttendDTO {
    @NotNull(message="Debe seleccionar el tipo de incidente")
    private Integer estado;

    @NotBlank(message = "La respuesta es un campo obligatorio, por favor comente sobre el estado de la queja.")
    @Size(min=2, max=255,  message="la respuesta debe tener un minimo de información para ser valida.")
    private String respuesta;

    public AttendDTO(Integer estado, String respuesta) {
        this.estado = estado;
        this.respuesta = respuesta;
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
}
