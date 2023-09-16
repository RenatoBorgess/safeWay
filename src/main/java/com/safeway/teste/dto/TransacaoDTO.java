package com.safeway.teste.dto;

import com.safeway.teste.domain.TipoTransacao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoDTO {
    private TipoTransacao tipo;
    private BigDecimal valor;
    private String identificador;


}

