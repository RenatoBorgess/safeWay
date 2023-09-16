package com.safeway.teste.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpresaResponseDTO {
    private String nome;
    private String cnpj;


}
