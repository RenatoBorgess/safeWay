package com.safeway.teste.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Taxa {
    @Id
    private String nome;
    private Float valor;
    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
