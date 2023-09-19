package com.safeway.teste.callback.deposit;

import com.safeway.teste.callback.bank.BankDepositRequest;
import com.safeway.teste.callback.bank.BankDepositResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/deposit")
public class Deposit {

    public static final String DEPOSIT_CALLBACK_URL = "http://localhost:8080/api/v1/deposit/callback";
    public static final String BANK_DEPOSIT_URL = "http://localhost:8081/api/v1/bank/deposit";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<String> deposit(@RequestBody DepositRequest depositRequest) {

       BankDepositRequest bankDepositRequest =  BankDepositRequest.builder()
               .accountId(depositRequest.getAccountId())
               .amount(depositRequest.getAmount())
               .callbackUrl(DEPOSIT_CALLBACK_URL)
               .build();


         HttpEntity<BankDepositRequest> request = new HttpEntity<>(bankDepositRequest);
        System.out.println(LocalDateTime.now() + "****** SERVICO CLIENTE -  CHAMA API DO BANCO");
        ResponseEntity<BankDepositResponse> response = restTemplate.exchange(BANK_DEPOSIT_URL, HttpMethod.POST, request, BankDepositResponse.class);
        System.out.println(LocalDateTime.now() + "****** SERVICO CLIENTE -  BANCO FALOU QUE: '"+ response.getBody().getStatus()+"'");

        return ResponseEntity.ok(response.toString());
    }

    @PostMapping("/callback")
    public ResponseEntity<String> depositCallback(@RequestBody DepositCallbackRequest depositCallbackRequest) {


        System.out.println(LocalDateTime.now() + "****** SERVICO CLIENTE:  BANCO FALOU QUE " +  depositCallbackRequest.getStatus());
        return ResponseEntity.ok("RECEBI O CALLBACK BANCO, EH NOIS!");
    }

}

