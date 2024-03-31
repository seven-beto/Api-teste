package com.example.controller;

import com.example.dtos.DadosAtualizarPetsDto;
import com.example.dtos.DadosCadastroPetsDto;
import com.example.entity.Pets;
import com.example.repository.PetsRepository;
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

@RestController
@RequestMapping("/pets")
public class ControllerPets {

    @Autowired
    private PetsRepository repository;

    @Operation(summary = "Lista Total Pets", description = "Lista Completa de Pets", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Pets com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosCadastroPetsDto"))
            }),
            @ApiResponse(responseCode = "404", description = "Lista nao encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
        })
    })
    @GetMapping
    @Transactional
    public ResponseEntity all(DadosCadastroPetsDto dados){
        var listAll = repository.findAll();
        return new ResponseEntity<>(listAll, HttpStatus.OK);
    }

    @Operation(summary = "Cadastro de pets", description = "Cadastro de Pets", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario cadastrado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosCadastroPetsDto"))
            }),
            @ApiResponse(responseCode = "500", description = "Erro servidor Interno", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            })
    })
    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DadosCadastroPetsDto dados){
        var cadastro = repository.save(new Pets(dados));
        return new ResponseEntity<>(cadastro, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar Cadastro", description = "Atualizacao de cadastro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados atualizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosAtualizarPetsDto"))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            })
    })
    @PutMapping
    @Transactional
    public void update(@RequestBody DadosAtualizarPetsDto dados){
        var updateAll = repository.getReferenceById(dados.id());
        updateAll.atualizando(dados);
    }

    @Operation(summary = "Exclusao de cadastro", description = "Exclusao de cadastro", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluido", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosAtualizarPetsDto"))
            }),
            @ApiResponse(responseCode = "500", description = "Error no servidor interno", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
