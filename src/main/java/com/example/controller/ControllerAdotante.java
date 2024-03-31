package com.example.controller;

import com.example.dtos.DadosAtualizarAdotanteDto;
import com.example.dtos.DadosCadastroDto;
import com.example.entity.Adotante;
import com.example.repository.AdotanteRepository;
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
@RequestMapping("/Adontante")
public class ControllerAdotante {

    @Autowired
    private AdotanteRepository repository;

    @Operation(summary = "Listar Adotante",description = "Listar Adotante", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listou os adotante com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosAtualizarAdotanteDto"))
            }),
            @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            })
    })
    @GetMapping
    @Transactional
    public ResponseEntity alllist(DadosCadastroDto dados){
        var all =  repository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @Operation(summary = "cadastro de adotante", description = "Cadastro Adotante", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosCadastroDto"))
            }),
            @ApiResponse(responseCode = "500", description = "Erro no servidor interno", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            })
    })
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroDto dados){
        var cadastro = repository.save(new Adotante(dados));
        return new ResponseEntity<>(cadastro, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar Dados Adotante", description = "Atualizar Dados Adotante", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizacao realizada com sucesso",content = {
                    @Content(mediaType = "application/json", schema = @Schema(ref = "DadosAtualizarAdotanteDto"))
            }),
            @ApiResponse(responseCode = "500", description = "Usuario nao encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            } )
    })
    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DadosAtualizarAdotanteDto dados){
        var updateAl = repository.getReferenceById(dados.id());
        updateAl.atualizando(dados);
    }

    @Operation(summary = "Deletar dados", description = "Deletar usuario", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusao do usuario realizado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro no servidor Interno")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
