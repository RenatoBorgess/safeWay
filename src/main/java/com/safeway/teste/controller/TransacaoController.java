package com.safeway.teste.controller;

import com.safeway.teste.domain.Transacao;
import com.safeway.teste.dto.TransacaoDTO;
import com.safeway.teste.exception.SaldoInsuficienteException;
import com.safeway.teste.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transacoes")
public class TransacaoController {


    private TransacaoService transacaoService;
    public TransacaoController(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<Transacao> realizarDeposito(@RequestBody TransacaoDTO transacaoDTO) {
        Transacao transacao = transacaoService.realizarDeposito(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }

    @PostMapping("/saque")
    public ResponseEntity<Transacao> realizarSaque(@RequestBody TransacaoDTO transacaoDTO) throws SaldoInsuficienteException {
        Transacao transacao = transacaoService.realizarSaque(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
    }
}

