package com.safeway.teste.dto;

import com.safeway.teste.domain.Taxa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO{
    private String nome;
    private String email;
    private String telefone;
    @CNPJ
    private String cnpj;
    private Taxa taxa;
}
