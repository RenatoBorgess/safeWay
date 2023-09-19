package com.safeway.teste.callback.bank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDepositResponse {

    private String accountId;
    private String amount;
    private String status;

}
