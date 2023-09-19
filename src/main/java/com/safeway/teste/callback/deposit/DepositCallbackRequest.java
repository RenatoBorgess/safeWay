package com.safeway.teste.callback.deposit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepositCallbackRequest {

    private String accountId;
    private String amount;
    private String status;

}
