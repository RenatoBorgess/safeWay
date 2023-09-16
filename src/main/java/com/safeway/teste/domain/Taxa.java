package com.safeway.teste.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Taxa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Float valor;
    @OneToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

}
