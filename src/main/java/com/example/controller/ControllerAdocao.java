package com.example.controller;


import com.example.dtos.DadosCadastroAdocao;
import com.example.entity.Adocao;
import com.example.repository.AdocaoRepository;
import com.example.service.ServiceAdocao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/adocao")
public class ControllerAdocao {

    @Autowired
    private ServiceAdocao serviceAdocao;

    @Autowired
    private AdocaoRepository repository;

    @Operation(summary = "Lista de Adocao", description = "Lista completa de Adocao", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Adocao com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosCadastroAdocao"))
            })
    })
    @GetMapping
    public ResponseEntity all(DadosCadastroAdocao dados){
        var listAll = repository.findAll();
        return new ResponseEntity<>(listAll, HttpStatus.OK);
    }

    @Operation(summary = "Cadastro de Adocao", description = "Cadastro de Adocoes", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro Realizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosCadastroAdocao"))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            })
    })
    @PostMapping
    @Transactional
    public ResponseEntity adocao(@RequestBody @Valid DadosCadastroAdocao dados){
        var cadastroAdocao = serviceAdocao.adotar(dados);
        return new ResponseEntity<>(cadastroAdocao, HttpStatus.CREATED);
    }

    @Operation(summary = "Consulta por data", description = "Consulta por data", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "consulta realizada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "dataAdocao"))
            })
    })
    @GetMapping("/find")
    public ResponseEntity findByDataAdocao(@RequestParam LocalDateTime dataAdocao){
        var find = repository.findBydataAdocao(dataAdocao);
        return new ResponseEntity<>(find, HttpStatus.OK);
    }
}
