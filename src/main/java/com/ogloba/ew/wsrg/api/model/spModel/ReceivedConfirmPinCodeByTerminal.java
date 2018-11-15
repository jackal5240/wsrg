package com.ogloba.ew.wsrg.api.model.spModel;

public class ReceivedConfirmPinCodeByTerminal {

	private String terminalId;
	private String operatorId;
	private String transactionNumber;
	private String result;
	private String error;
	private String returnCode;
	private String response;
	
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	@Override
    public String toString() {
    	return "{terminalId: "+terminalId+",operatorId: "+operatorId+",result: "+result
    			+",error: "+error+",response: "+response+",transactionNumber: "+transactionNumber
    			+",returnCode: "+returnCode+"}";
    }
	
}
