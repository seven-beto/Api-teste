package com.example.service;


import com.example.dtos.DadosCadastroAdocao;
import com.example.entity.Adocao;
import com.example.entity.Adotante;
import com.example.entity.Pets;
import com.example.repository.AdocaoRepository;
import com.example.repository.AdotanteRepository;
import com.example.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceAdocao {

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private AdotanteRepository adotanteRepository;

    public Adocao adotar(DadosCadastroAdocao dados){
        Pets pets = petsRepository.findById(dados.idPets()).get();
        Adotante adotante = adotanteRepository.findById(dados.idAdotante()).get();
        Adocao adocao = new Adocao();
        adocao.setIdPets(pets);
        adocao.setIdAdotante(adotante);
        adocao.setDataAdocao(dados.dataAdocao());

        return adocaoRepository.save(adocao);
    }

    public List<Adocao>findByDataAdocao(LocalDateTime dataAdocao){
        return adocaoRepository.findBydataAdocao(dataAdocao);
    }

}
