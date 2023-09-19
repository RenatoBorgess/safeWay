package com.safeway.teste.callback.deposit;

import lombok.Data;
import lombok.ToString;

@Data
public class DepositRequest {

    private String accountId;
    private String amount;

}
