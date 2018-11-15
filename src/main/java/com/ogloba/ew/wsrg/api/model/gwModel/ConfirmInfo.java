package com.ogloba.ew.wsrg.api.model.gwModel;

import java.math.BigDecimal;

public class ConfirmInfo {
	
	private String result;
	private String error;
	private BigDecimal amount;
	private String loyaltyCardTitalPoints;
	private String originalLoyaltyCardTotalPoints;
	private String newLoyaltyCardPoints;
	private String pincodeList;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

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

	public String getPincodeList() {
		return pincodeList;
	}

	public void setPincodeList(String pincodeList) {
		this.pincodeList = pincodeList;
	}

	@Override
    public String toString() {
    	return "{result: "+result+",error: "+error+",amount: "+amount
    			+",loyaltyCardTitalPoints: "+loyaltyCardTitalPoints
    			+",originalLoyaltyCardTotalPoints: "+originalLoyaltyCardTotalPoints
    			+",newLoyaltyCardPoints: "+newLoyaltyCardPoints
    			+",pincodeList: "+pincodeList+"}";
    }
	
}
