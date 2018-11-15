package com.ogloba.ew.wsrg.api.message;

import org.hibernate.validator.constraints.NotBlank;

public class SalesRequest {

	public String transactionNumber;
	
	@NotBlank
	public String cashierId;
	
	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getCashierId() {
		return cashierId;
	}

	public void setCashierId(String cashierId) {
		this.cashierId = cashierId;
	}

	@Override
    public String toString() {
    	return "{transactionNumber: "+transactionNumber+",cashierId: "+cashierId+"}";
    }
	
}
