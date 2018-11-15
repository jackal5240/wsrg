package com.ogloba.ew.wsrg.api.model.spModel;

import java.math.BigDecimal;
import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.ConfirmInfo;
import com.ogloba.ew.wsrg.api.model.gwModel.ReservationInfo;

public class ReservationPinCodeByTerminal {

	private String terminalId;
	private String operatorId;
	private String txno;
	private String quantity;
	private String cashRegisterNo;
	private String gencode;
	private BigDecimal inputAmount;
	private String returnCode;
	private String response;
	private List<ReservationInfo> resultCursor;

	public List<ReservationInfo> getResultCursor() {
		return resultCursor;
	}
	public void setResultCursor(List<ReservationInfo> resultCursor) {
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

	public String getTxno() {
		return txno;
	}

	public void setTxno(String txno) {
		this.txno = txno;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCashRegisterNo() {
		return cashRegisterNo;
	}

	public void setCashRegisterNo(String cashRegisterNo) {
		this.cashRegisterNo = cashRegisterNo;
	}

	public String getGencode() {
		return gencode;
	}

	public void setGencode(String gencode) {
		this.gencode = gencode;
	}

	public BigDecimal getInputAmount() {
		return inputAmount;
	}

	public void setInputAmount(BigDecimal bigDecimal) {
		this.inputAmount = bigDecimal;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	@Override
    public String toString() {
    	return "{terminalId: "+terminalId+",operatorId: "+operatorId+",txno: "+txno
    			+",quantity: "+quantity+",cashRegisterNo: "+cashRegisterNo+",gencode: "+gencode
    			+",response: "+response+",inputAmount: "+inputAmount+",returnCode: "+returnCode+"}";
    }
	
}
