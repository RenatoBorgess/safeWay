package com.safeway.teste.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Data
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cnpj;
    private BigDecimal saldoConta;

    @OneToOne(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Taxa taxa;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacoes = new ArrayList<>();

    public BigDecimal obterSaldoTotal(){
        BigDecimal saldoTotal = BigDecimal.ZERO;

        for (Transacao transacao : transacoes) {
            if ("Deposito".equals(transacao.getTipo())) {
                saldoTotal = saldoTotal.add(transacao.getValor());
            } else if ("Saque".equals(transacao.getTipo())) {
                saldoTotal = saldoTotal.subtract(transacao.getValor());
            }
        }
            saldoTotal = saldoTotal.subtract(BigDecimal.valueOf(taxa.getValor()));


        return saldoTotal;
    }

    public BigDecimal getSaldoConta() {
        return obterSaldoTotal();
    }
}

