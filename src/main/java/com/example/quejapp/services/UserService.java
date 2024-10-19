package com.example.quejapp.services;

import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.model.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final QuejaRepository repositorioDeQuejas;
    private final UsuarioRepository repositorioDeUsuarios;

    public UserService(
            QuejaRepository repositorioQuejas,
            UsuarioRepository repositorioDeUsuarios
    ) {
        this.repositorioDeQuejas = repositorioQuejas;
        this.repositorioDeUsuarios = repositorioDeUsuarios;
    }

    public List<ReportDTO> listarQuejas(String nickname){
        Usuario usuario = repositorioDeUsuarios.findByNickname(nickname);
        return repositorioDeQuejas.findUserComplaintsOrdered(usuario.getId());
    }


    public Queja createComplaintForUser(Queja queja, String nickname) {
        Usuario usuario = repositorioDeUsuarios.findByNickname(nickname);
        queja.setEstado(1);
        queja.setUsuarioId(usuario.getId());
        return repositorioDeQuejas.save(queja);
    }

    public ReportDTO buscarQueja(Long id){
        List<ReportDTO> optionalQueja = repositorioDeQuejas.findUserComplaintReport(id);
        return optionalQueja.getFirst();
    }
}
