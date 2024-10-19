package com.example.quejapp.model.repositories;

import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
    @Query(value = "SELECT new com.example.quejapp.DTOs.ReportDTO(complaint.id,complaint.fecha,u.nombre, u.apellido,complaint.descripcion, complaint.tipoQueja, complaint.ubicacion, complaint.fechaRespuesta, complaint.estado, complaint.respuesta)"
            + " from Queja complaint, Usuario u "
            + " where complaint.usuarioId= u.id "
            + " and complaint.id=?1 ")
    Optional<ReportDTO> findComplaintReportById(Long id);

    @Query(value = "SELECT new com.example.quejapp.DTOs.ReportDTO(" +
            "complaint.id,complaint.fecha,u.nombre, u.apellido, substring(complaint.descripcion,1 ,100) , complaint.tipoQueja," +
            "complaint.ubicacion, complaint.fechaRespuesta, complaint.estado, complaint.respuesta)"
            + " from Queja complaint, Usuario u "
            + " where complaint.usuarioId= u.id "
            + " order by complaint.fecha desc , complaint.estado")
    List<ReportDTO> findComplaintReportOrdered();

    @Query(value = "SELECT new com.example.quejapp.DTOs.ReportDTO(" +
            "complaint.id,complaint.fecha,u.nombre, u.apellido, substring(complaint.descripcion,1 ,100) , complaint.tipoQueja," +
            "complaint.ubicacion, complaint.fechaRespuesta, complaint.estado, complaint.respuesta)"
            + " from Queja complaint, Usuario u "
            + " where complaint.usuarioId= u.id "
            + " and complaint.usuarioId=?1 "
            + " order by complaint.fecha desc , complaint.estado")
    List<ReportDTO> findUserComplaintsOrdered(Long id);

    @Query(value = "SELECT new com.example.quejapp.DTOs.ReportDTO(complaint.id,complaint.fecha,u.nombre , u.apellido,complaint.descripcion, complaint.tipoQueja, complaint.ubicacion, complaint.fechaRespuesta, complaint.estado, complaint.respuesta)"
            + " from Queja complaint "
            + " left join Usuario u on complaint.encargado = u.id"
            + " where complaint.id=?1")
    List<ReportDTO> findUserComplaintReport(Long id);
}
