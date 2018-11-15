package com.ogloba.ew.wsrg.api.message;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.PaymentMode;

public class MerchantsResponse {

	private String name;
	
	private String address;
	
	private BigDecimal latitude;
	
	private BigDecimal longitude;
	
	private BigDecimal amountPayNow;
	
	private BigDecimal amountPayNext;
	
	private Date payNextDate;
	
	private BigDecimal overallRiskLimit;
	
	private BigDecimal balance;
	
	private BigDecimal available;
	
	private boolean isInvoiceOpen;
	
	private boolean isSalesmanCommission;
	
	private String smartCardId;
	
	private String smartCardPassphrase;
	
	private String smartCardTid;	
	
	private String smartCardPassword;
	
	private List<PaymentMode> paymentModes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getAmountPayNow() {
		return amountPayNow;
	}

	public void setAmountPayNow(BigDecimal amountPayNow) {
		this.amountPayNow = amountPayNow;
	}

	public BigDecimal getAmountPayNext() {
		return amountPayNext;
	}

	public void setAmountPayNext(BigDecimal amountPayNext) {
		this.amountPayNext = amountPayNext;
	}

	public Date getPayNextDate() {
		return payNextDate;
	}

	public void setPayNextDate(Date payNextDate) {
		this.payNextDate = payNextDate;
	}

	public BigDecimal getOverallRiskLimit() {
		return overallRiskLimit;
	}

	public void setOverallRiskLimit(BigDecimal overallRiskLimit) {
		this.overallRiskLimit = overallRiskLimit;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public boolean isInvoiceOpen() {
		return isInvoiceOpen;
	}

	public void setInvoiceOpen(boolean isInvoiceOpen) {
		this.isInvoiceOpen = isInvoiceOpen;
	}

	public boolean isSalesmanCommission() {
		return isSalesmanCommission;
	}

	public void setSalesmanCommission(boolean isSalesmanCommission) {
		this.isSalesmanCommission = isSalesmanCommission;
	}

	public String getSmartCardId() {
		return smartCardId;
	}

	public void setSmartCardId(String smartCardId) {
		this.smartCardId = smartCardId;
	}

	public String getSmartCardPassphrase() {
		return smartCardPassphrase;
	}

	public void setSmartCardPassphrase(String smartCardPassphrase) {
		this.smartCardPassphrase = smartCardPassphrase;
	}

	public String getSmartCardTid() {
		return smartCardTid;
	}

	public void setSmartCardTid(String smartCardTid) {
		this.smartCardTid = smartCardTid;
	}

	public String getSmartCardPassword() {
		return smartCardPassword;
	}

	public void setSmartCardPassword(String smartCardPassword) {
		this.smartCardPassword = smartCardPassword;
	}

	public List<PaymentMode> getPaymentModes() {
		return paymentModes;
	}

	public void setPaymentModes(List<PaymentMode> paymentModes) {
		this.paymentModes = paymentModes;
	}
	
}
