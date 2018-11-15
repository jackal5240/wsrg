package com.ogloba.ew.wsrg.api.message;

import java.math.BigDecimal;
import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.ApiResponse;
import com.ogloba.ew.wsrg.api.model.gwModel.PinCode;


public class ConfirmPinCodeResponse extends ApiResponse {

	private String referenceNumber;
	private List<PinCode> pinCodes;
	private BigDecimal amount;
	private String result;
	private String error;
	private String loyaltyCardTitalPoints;
	private String originalLoyaltyCardTotalPoints;
	private String newLoyaltyCardPoints;
	
	public String getLoyaltyCardTitalPoints() {
		return loyaltyCardTitalPoints;
	}

	public void setLoyaltyCardTitalPoints(String loyaltyCardTitalPoints) {
		this.loyaltyCardTitalPoints = loyaltyCardTitalPoints;
	}

	public String getOriginalLoyaltyCardTotalPoints() {
		return originalLoyaltyCardTotalPoints;
	}

	public void setOriginalLoyaltyCardTotalPoints(String originalLoyaltyCardTotalPoints) {
		this.originalLoyaltyCardTotalPoints = originalLoyaltyCardTotalPoints;
	}

	public String getNewLoyaltyCardPoints() {
		return newLoyaltyCardPoints;
	}

	public void setNewLoyaltyCardPoints(String newLoyaltyCardPoints) {
		this.newLoyaltyCardPoints = newLoyaltyCardPoints;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<PinCode> getPinCodes() {
		return pinCodes;
	}

	public void setPinCodes(List<PinCode> pinCodes) {
		this.pinCodes = pinCodes;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
    public String toString() {
    	return "{referenceNumber: "+referenceNumber+",List<PinCode>: "+pinCodes+"}";
    }
}
