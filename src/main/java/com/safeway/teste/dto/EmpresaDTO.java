package com.safeway.teste.dto;

import com.safeway.teste.domain.Taxa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO{
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    private Taxa taxa;
}
