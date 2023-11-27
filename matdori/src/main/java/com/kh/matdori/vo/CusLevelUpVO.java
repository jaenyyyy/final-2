package com.kh.matdori.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CusLevelUpVO {
	 private String paymentCustomer;
    private String customerId;
    private String paymentStatus;
    private Date paymentTime;
    private int paymentSuccess;
    private String customerLevel;
    
    
    
    public void resetPaymentSuccess() {
        paymentSuccess = 0;
    }
}
