package com.example.quejapp.services;

import com.example.quejapp.DTOs.AttendDTO;
import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.model.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendantService {
    private final QuejaRepository repositorioDeQuejas;
    private final UsuarioRepository repositorioDeUsuarios;

    public AttendantService(
            QuejaRepository repositorioQuejas,
            UsuarioRepository repositorioDeUsuarios
    ) {
        this.repositorioDeQuejas = repositorioQuejas;
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    public List<ReportDTO> listarQuejas(){
        return repositorioDeQuejas.findComplaintReportOrdered();
    }

    public ReportDTO buscarQuejaPorId(Long id){
        Optional<ReportDTO> reporte = repositorioDeQuejas.findComplaintReportById(id);
        return reporte.orElse(null);
    }

    public void actualizarQueja(Long id, AttendDTO cambios, String nickname){
        Optional<Queja> reporteOpcion = repositorioDeQuejas.findById(id);

        if(reporteOpcion.isEmpty()){
            return;
        }
        Usuario usuario = repositorioDeUsuarios.findByNickname(nickname);

        Queja queja = reporteOpcion.get();
        queja.setEncargado(usuario.getId());
        queja.setEstado(cambios.getEstado());
        queja.setRespuesta(cambios.getRespuesta());
        queja.setFechaRespuesta(new Date());
        repositorioDeQuejas.save(queja);
    }
}
