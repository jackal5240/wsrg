package com.ogloba.ew.wsrg.api.model.gwModel;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class ReconciliationRecord {

	@NotBlank
	private String referenceNumber;
	
	@NotBlank
	private String transactionNumber;
	
	private String transactionCashierId;
	
	@NotBlank
	private Date transactionTime;
	
	@NotBlank
	private String gencode;
	
	@NotBlank
	private String pincodeSerial;
	
	private BigDecimal amount;
	
	@NotBlank
	private String transactionStatus;

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getTransactionCashierId() {
		return transactionCashierId;
	}

	public void setTransactionCashierId(String transactionCashierId) {
		this.transactionCashierId = transactionCashierId;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getGencode() {
		return gencode;
	}

	public void setGencode(String gencode) {
		this.gencode = gencode;
	}

	public String getPincodeSerial() {
		return pincodeSerial;
	}

	public void setPincodeSerial(String pincodeSerial) {
		this.pincodeSerial = pincodeSerial;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	
}
