package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Table(name = "adocao")
@Entity(name = "Adocao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "idAdotante")
    @ManyToOne
    private Adotante idAdotante;

    @JoinColumn(name = "idPets")
    @ManyToOne
    private Pets idPets;

    @Column(unique = true)
    private LocalDateTime dataAdocao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adotante getIdAdotante() {
        return idAdotante;
    }

    public void setIdAdotante(Adotante idAdotante) {
        this.idAdotante = idAdotante;
    }

    public Pets getIdPets() {
        return idPets;
    }

    public void setIdPets(Pets idPets) {
        this.idPets = idPets;
    }

    public LocalDateTime getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(LocalDateTime dataAdocao) {
        this.dataAdocao = dataAdocao;
    }
}
