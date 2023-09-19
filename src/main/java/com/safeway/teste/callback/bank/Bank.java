package com.safeway.teste.callback.bank;

import com.safeway.teste.callback.deposit.DepositCallbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@RequestMapping("api/v1/bank")
public class Bank {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/deposit")
    public ResponseEntity<BankDepositResponse> deposit(@RequestBody BankDepositRequest bankDepositRequest) {

        BankDepositResponse response = BankDepositResponse.builder()
                .accountId(bankDepositRequest.getAccountId())
                .amount(bankDepositRequest.getAmount())
                .status("TAMO OLHANO")
                .build();


        CompletableFuture<Future<Void>> future  = CompletableFuture.supplyAsync(() -> doDeposit(bankDepositRequest));


        System.out.println(LocalDateTime.now() + "****** BANCO - TA ANALIZANO A PARADA");
        return ResponseEntity.ok(response);
    }

    private Future<Void> doDeposit(BankDepositRequest bankDepositRequest) {

        DepositCallbackRequest depositCallbackRequest = DepositCallbackRequest.builder()
                .accountId(bankDepositRequest.getAccountId())
                .amount(bankDepositRequest.getAmount())
                .status("DEU PAU").build();

        try {
            Thread.sleep(3000L);

            BigDecimal amount = new BigDecimal(bankDepositRequest.getAmount());
            System.out.println(LocalDateTime.now() + "****** BANCO - PROCESSANDO DEPOSITO");

            if(isDepositValid(amount)){
                depositCallbackRequest.setStatus("É NÒIS");
            }
            Thread.sleep(7000L);

            System.out.println(LocalDateTime.now() + "****** BANCO - MANDA O CALLBACK PRO SERVICO CLIENTE");
            HttpEntity<DepositCallbackRequest> request = new HttpEntity<>(depositCallbackRequest);
            ResponseEntity<String> response = restTemplate.exchange(bankDepositRequest.getCallbackUrl(), HttpMethod.POST, request, String.class);

            Thread.sleep(1000L);

            System.out.println(LocalDateTime.now() + "****** BANCO - SERVICO CLIENTE DISSE: '"+ response.getBody()+"'");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CompletableFuture.completedFuture(null);
    }

    private static boolean isDepositValid(BigDecimal amount) {
        return amount.compareTo(BigDecimal.TEN) != -1;
    }

}

