package com.example.quejapp.model.repositories;

import com.example.quejapp.model.Queja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
}
