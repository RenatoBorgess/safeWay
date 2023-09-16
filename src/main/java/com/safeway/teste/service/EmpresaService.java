package com.safeway.teste.service;

import com.safeway.teste.domain.Empresa;
import com.safeway.teste.dto.EmpresaDTO;
import com.safeway.teste.dto.EmpresaResponseDTO;
import com.safeway.teste.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public EmpresaDTO criarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(empresaDTO,empresa);
        Empresa empresaSalva = empresaRepository.save(empresa);
        EmpresaDTO empresaSalvaDTO = new EmpresaDTO();
        BeanUtils.copyProperties(empresaSalva,empresaSalvaDTO);

        return empresaSalvaDTO;
    }

    public List<EmpresaResponseDTO> listarEmpresas() {

            List<Empresa> empresas = empresaRepository.findAll();

            return empresas
                    .stream()
                    .map(empresa -> new EmpresaResponseDTO())
                    .collect(Collectors
                            .toList());
        }
    }

