package com.example.quejapp.services;

import com.example.quejapp.DTOs.AttendDTO;
import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import com.example.quejapp.model.repositories.QuejaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendantService {
    private final QuejaRepository repositorioDeQuejas;

    public AttendantService(QuejaRepository repositorioQuejas) {
        this.repositorioDeQuejas = repositorioQuejas;
    }

    public List<ReportDTO> listarQuejas(){
        return repositorioDeQuejas.findComplaintReportOrdered();
    }

    public ReportDTO buscarQuejaPorId(Long id){
        Optional<ReportDTO> reporte = repositorioDeQuejas.findComplaintReportById(id);
        return reporte.orElse(null);
    }

    public Queja actualizarQueja(Long id, AttendDTO cambios){
        Optional<Queja> reporteOpcion = repositorioDeQuejas.findById(id);
        if(reporteOpcion.isEmpty()){
            return null;
        }
        Queja queja = reporteOpcion.get();
        queja.setEstado(cambios.getEstado());
        queja.setRespuesta(cambios.getRespuesta());
        queja.setFechaRespuesta(new Date());
        return repositorioDeQuejas.save(queja);
    }
}
