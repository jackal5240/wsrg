package com.ogloba.ew.wsrg.api.model.gwModel;

public class ApiResponse {

	private String isSuccessful;
	private String errorCode;
	private String errorMessage;
	private String errorDetail;
	
	public String getIsSuccessful() {
		return isSuccessful;
	}
	public void setIsSuccessful(String isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorDetail() {
		return errorDetail;
	}
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	
}
