package com.ogloba.ew.wsrg.api.message;

import org.hibernate.validator.constraints.NotBlank;

public class ClaimRequest {

	@NotBlank
	private String gencode;
	
	@NotBlank
	private String pincodeSerial;
	
	@NotBlank
	private String CashierID;

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

	public String getCashierID() {
		return CashierID;
	}

	public void setCashierID(String cashierID) {
		CashierID = cashierID;
	}
	
	
}
