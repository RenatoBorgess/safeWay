package com.safeway.teste.controller;

import java.util.*;
import com.safeway.teste.domain.Empresa;
import com.safeway.teste.dto.EmpresaDTO;
import com.safeway.teste.dto.EmpresaResponseDTO;
import com.safeway.teste.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criarEmpresa(@RequestBody @Valid EmpresaDTO empresaDTO) {
        EmpresaResponseDTO empresaCriada = empresaService.criarEmpresa(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCriada);
    }
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listarEmpresas(){
        List<EmpresaResponseDTO> empresas = empresaService.listarEmpresas();
        return ResponseEntity.ok(empresas);
    }
}

