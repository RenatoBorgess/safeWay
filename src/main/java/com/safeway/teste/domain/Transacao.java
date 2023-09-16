package com.safeway.teste.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Data
@NoArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    private BigDecimal valor;
    @OneToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;
}
