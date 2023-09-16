package com.safeway.teste.repository;

import com.safeway.teste.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    public Optional<Empresa> findByCnpj(String cnpj);
}
