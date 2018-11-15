package com.ogloba.ew.wsrg.api.message;

import java.util.List;

import org.apache.ibatis.annotations.Result;

import com.ogloba.ew.wsrg.api.model.gwModel.ApiResponse;
import com.ogloba.ew.wsrg.api.model.gwModel.PinCode;

public class ReservePinCodeResponse extends ApiResponse {

	private boolean isNeedUpdateProduct;
	
	private boolean isNeedUpdateTicket;
	
	private String referenceNumber;

	private List<PinCode> pinCodes;
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public boolean isNeedUpdateProduct() {
		return isNeedUpdateProduct;
	}

	public void setNeedUpdateProduct(boolean isNeedUpdateProduct) {
		this.isNeedUpdateProduct = isNeedUpdateProduct;
	}

	public boolean isNeedUpdateTicket() {
		return isNeedUpdateTicket;
	}

	public void setNeedUpdateTicket(boolean isNeedUpdateTicket) {
		this.isNeedUpdateTicket = isNeedUpdateTicket;
	}

	public List<PinCode> getPinCodes() {
		return pinCodes;
	}

	public void setPinCodes(List<PinCode> pinCodes) {
		this.pinCodes = pinCodes;
	}
}
