package com.safeway.teste.service;

import com.safeway.teste.domain.Empresa;
import com.safeway.teste.domain.TipoTransacao;
import com.safeway.teste.domain.Transacao;
import com.safeway.teste.dto.TransacaoDTO;
import com.safeway.teste.exception.SaldoInsuficienteException;
import com.safeway.teste.repository.EmpresaRepository;
import com.safeway.teste.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
   TransacaoRepository transacaoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    public Transacao realizarDeposito(final TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        BeanUtils.copyProperties(transacaoDTO,transacao);
        Empresa empresaValida = validarEmpresa(transacaoDTO.getIdentificador());
        empresaValida.setSaldoConta(empresaValida.getSaldoConta().add(transacao.getValor()));
        transacao.setEmpresa(empresaValida);
        empresaValida.getTransacoes().add(transacao);
        transacao = transacaoRepository.save(transacao);


        return transacao;
    }

    public Transacao realizarSaque(final TransacaoDTO transacaoDTO) throws SaldoInsuficienteException {

        Empresa empresaValida = validarEmpresa(transacaoDTO.getIdentificador());

        Transacao novaTransacao = new Transacao();
        novaTransacao.setEmpresa(empresaValida);
        BeanUtils.copyProperties(transacaoDTO, novaTransacao);


        if (empresaValida.getSaldoConta().compareTo(novaTransacao.getValor()) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }


        transacaoRepository.save(novaTransacao);
        empresaValida.setSaldoConta(empresaValida.getSaldoConta().subtract(novaTransacao.getValor()));
        empresaRepository.save(empresaValida);

        return novaTransacao;
    }
    public Empresa validarEmpresa(String cnpj) {
        Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(cnpj);
        if(empresaOptional.isEmpty()){
            throw new EntityNotFoundException();
        }
        return empresaOptional.get();
    }
}



