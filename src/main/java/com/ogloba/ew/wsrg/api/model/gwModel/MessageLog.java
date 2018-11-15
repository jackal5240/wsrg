package com.ogloba.ew.wsrg.api.model.gwModel;

public class MessageLog {

	private String inParameter;
	private String outParameter;
	private int messageNo;
	private int returnCode;
	private String terminalId;
	private String errorMsg;
	private String merchantSeqno;
	
	public String getMerchantSeqno() {
		return merchantSeqno;
	}
	public void setMerchantSeqno(String merchantSeqno) {
		this.merchantSeqno = merchantSeqno;
	}
	public String getInParameter() {
		return inParameter;
	}
	public void setInParameter(String inParameter) {
		this.inParameter = inParameter;
	}
	public String getOutParameter() {
		return outParameter;
	}
	public void setOutParameter(String outParameter) {
		this.outParameter = outParameter;
	}
	public int getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
