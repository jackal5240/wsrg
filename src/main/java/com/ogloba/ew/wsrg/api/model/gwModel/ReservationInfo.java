package com.ogloba.ew.wsrg.api.model.gwModel;

public class ReservationInfo {
	
	private String result;
	private String error;
	private String amount;
	private String refno;
	private String loyaltyCardTitalPoints;
	private String pincodeList;
	private String providerId;
	private String transSeqno;
	private String vatRate;
	private String vatAmount;
	
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getLoyaltyCardTitalPoints() {
		return loyaltyCardTitalPoints;
	}

	public void setLoyaltyCardTitalPoints(String loyaltyCardTitalPoints) {
		this.loyaltyCardTitalPoints = loyaltyCardTitalPoints;
	}

	public String getPincodeList() {
		return pincodeList;
	}

	public void setPincodeList(String pincodeList) {
		this.pincodeList = pincodeList;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getTransSeqno() {
		return transSeqno;
	}

	public void setTransSeqno(String transSeqno) {
		this.transSeqno = transSeqno;
	}

	public String getVatRate() {
		return vatRate;
	}

	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}

	public String getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}

	@Override
    public String toString() {
    	return "{result: "+result+",error: "+error+",amount: "+amount+",refno: "+refno
    			+",loyaltyCardTitalPoints: "+loyaltyCardTitalPoints+",pincodeList: "+pincodeList
    			+",providerId: "+providerId+",transSeqno: "+transSeqno
    			+",vatRate: "+vatRate+",vatAmount: "+vatAmount+"}";
    }
	
}
