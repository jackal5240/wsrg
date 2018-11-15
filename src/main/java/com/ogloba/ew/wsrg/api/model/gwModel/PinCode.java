package com.ogloba.ew.wsrg.api.model.gwModel;

import java.math.BigDecimal;

public class PinCode {

	private Integer index;
	private String serial;
	private String pinCode1;
	private String pinCode2;
	private String expiryDate;
	private BigDecimal amount;
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getPinCode1() {
		return pinCode1;
	}

	public void setPinCode1(String pinCode1) {
		this.pinCode1 = pinCode1;
	}

	public String getPinCode2() {
		return pinCode2;
	}

	public void setPinCode2(String pinCode2) {
		this.pinCode2 = pinCode2;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
    public String toString() {
    	return "{index: "+index+",serial: "+serial+",pinCode1: "+pinCode1
    			+",pinCode2: "+pinCode2+",expiryDate: "+expiryDate+",amount: "+amount+"}";
    }

}
