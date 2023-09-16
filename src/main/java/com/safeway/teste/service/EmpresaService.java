package com.safeway.teste.service;

import com.safeway.teste.domain.Empresa;
import com.safeway.teste.domain.Taxa;
import com.safeway.teste.dto.EmpresaDTO;
import com.safeway.teste.dto.EmpresaResponseDTO;
import com.safeway.teste.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
    }

    public EmpresaResponseDTO criarEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNome(empresaDTO.getNome());
        empresa.setEmail(empresaDTO.getEmail());
        empresa.setTelefone(empresaDTO.getTelefone());
        empresa.setCnpj(empresaDTO.getCnpj());

        Taxa taxa = new Taxa();
        taxa.setNome(empresaDTO.getTaxa().getNome());
        taxa.setValor(empresaDTO.getTaxa().getValor());

        empresa.setTaxa(taxa);
        taxa.setEmpresa(empresa);

        Empresa empresaSalva = empresaRepository.save(empresa);

        return EmpresaResponseDTO
                .builder()
                .nome(empresaSalva.getNome())
                .cnpj(empresaSalva.getCnpj())
                .build();
    }

    public List<EmpresaResponseDTO> listarEmpresas() {

            List<Empresa> empresas = empresaRepository.findAll();

            return empresas
                    .stream()
                    .map(empresa -> EmpresaResponseDTO
                            .builder()
                            .nome(empresa.getNome())
                            .cnpj(empresa.getCnpj()).build())
                    .collect(Collectors
                            .toList());
        }
    }

