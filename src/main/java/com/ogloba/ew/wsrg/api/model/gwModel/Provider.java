package com.ogloba.ew.wsrg.api.model.gwModel;

public class Provider {
	
	private Integer providerCode; 

	private String providerName;
	
	private String providerImageFileId;
	
	private String providerMessage;

	public Integer getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(Integer providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderImageFileId() {
		return providerImageFileId;
	}

	public void setProviderImageFileId(String providerImageFileId) {
		this.providerImageFileId = providerImageFileId;
	}

	public String getProviderMessage() {
		return providerMessage;
	}

	public void setProviderMessage(String providerMessage) {
		this.providerMessage = providerMessage;
	}
	
}
