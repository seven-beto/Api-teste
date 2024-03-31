package com.example.repository;

import com.example.entity.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    public List<Adocao>findBydataAdocao(LocalDateTime dataAdocao);

}
