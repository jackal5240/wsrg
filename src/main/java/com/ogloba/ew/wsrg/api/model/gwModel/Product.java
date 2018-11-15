package com.ogloba.ew.wsrg.api.model.gwModel;

import java.math.BigDecimal;

public class Product {

	private String gencode;
	
	private String productName;
	
	private BigDecimal consumerPrice;
	
	private BigDecimal vatRate;
	
	private String ticketId;
	
	private String saleMode;
	
	private BigDecimal dtuMinAmount;
	
	private BigDecimal dtuMaxAmount;
	
	private BigDecimal dtuAmountStep;
	
	private String productImageFileId;
	
	private String productLogoFileId;
	
	private String productMessage;

	public String getGencode() {
		return gencode;
	}

	public void setGencode(String gencode) {
		this.gencode = gencode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getConsumerPrice() {
		return consumerPrice;
	}

	public void setConsumerPrice(BigDecimal consumerPrice) {
		this.consumerPrice = consumerPrice;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(String saleMode) {
		this.saleMode = saleMode;
	}

	public BigDecimal getDtuMinAmount() {
		return dtuMinAmount;
	}

	public void setDtuMinAmount(BigDecimal dtuMinAmount) {
		this.dtuMinAmount = dtuMinAmount;
	}

	public BigDecimal getDtuMaxAmount() {
		return dtuMaxAmount;
	}

	public void setDtuMaxAmount(BigDecimal dtuMaxAmount) {
		this.dtuMaxAmount = dtuMaxAmount;
	}

	public BigDecimal getDtuAmountStep() {
		return dtuAmountStep;
	}

	public void setDtuAmountStep(BigDecimal dtuAmountStep) {
		this.dtuAmountStep = dtuAmountStep;
	}

	public String getProductImageFileId() {
		return productImageFileId;
	}

	public void setProductImageFileId(String productImageFileId) {
		this.productImageFileId = productImageFileId;
	}

	public String getProductLogoFileId() {
		return productLogoFileId;
	}

	public void setProductLogoFileId(String productLogoFileId) {
		this.productLogoFileId = productLogoFileId;
	}

	public String getProductMessage() {
		return productMessage;
	}

	public void setProductMessage(String productMessage) {
		this.productMessage = productMessage;
	}
	
}
