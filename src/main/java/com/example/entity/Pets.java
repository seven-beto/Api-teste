package com.example.entity;


import com.example.dtos.DadosAtualizarPetsDto;
import com.example.dtos.DadosCadastroPetsDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "pets")
@Entity(name = "Pets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raca;

    private String idade;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public Pets(DadosCadastroPetsDto dados) {
        this.raca = dados.raca();
        this.idade = dados.idade();
    }

    public void atualizando(DadosAtualizarPetsDto dados) {
        if(dados.raca() != null){
            this.raca = dados.raca();
        }
        if(dados.idade() != null){
            this.idade = dados.idade();
        }
    }
}
