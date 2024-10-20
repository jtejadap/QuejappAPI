package com.example.quejapp.services;

import com.example.quejapp.DTOs.ReportDTO;
import com.example.quejapp.model.Queja;
import com.example.quejapp.model.Rol;
import com.example.quejapp.model.Usuario;
import com.example.quejapp.model.repositories.QuejaRepository;
import com.example.quejapp.model.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    private final QuejaRepository repositorioDeQuejas;
    private final UsuarioRepository repositorioDeUsuarios;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            QuejaRepository repositorioQuejas,
            UsuarioRepository repositorioDeUsuarios,
            PasswordEncoder passwordEncoder
    ) {
        this.repositorioDeQuejas = repositorioQuejas;
        this.repositorioDeUsuarios = repositorioDeUsuarios;
        this.passwordEncoder = passwordEncoder;
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

    public Usuario CreateUser(Usuario formulario){
        formulario.setRol(Rol.USER.name());
        formulario.setPassword(passwordEncoder.encode(formulario.getPassword()));
        return repositorioDeUsuarios.save(formulario);
    }

    public String buscarRolDeUsuarioPorNickname(String nickname){
        Usuario usuario = repositorioDeUsuarios.findByNickname(nickname);
        return usuario.getRol();
    }

    public String returnHomeLinkByRole(Principal session) {
        if(session == null){
            return "/";
        }
        String rol = buscarRolDeUsuarioPorNickname(session.getName());

        if(rol.equalsIgnoreCase("ADMINISTRATOR")){
            return "/attendant/dashboard";
        }

        return "/user/dashboard";
    }
}
