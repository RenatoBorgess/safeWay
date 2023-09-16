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
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
