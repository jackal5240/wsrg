package com.ogloba.ew.wsrg.api.model.spModel;

import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.ConfirmInfo;

public class ConfirmPinCodeByTerminal {
	
	private String terminalId;
	private String operatorId;
	private String transactionNumber;
	private String returnCode;
	private String response;
    private List<ConfirmInfo> resultCursor;

	public List<ConfirmInfo> getResultCursor() {
		return resultCursor;
	}
	public void setResultCursor(List<ConfirmInfo> resultCursor) {
		this.resultCursor = resultCursor;
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
    	return "{terminalId: "+terminalId+",operatorId: "+operatorId+",transactionNumber: "+transactionNumber
    			+",response: "+response+",returnCode: "+returnCode+"}";
    }
}
