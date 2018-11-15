package com.ogloba.ew.wsrg.api.message;

import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.ApiResponse;
import com.ogloba.ew.wsrg.api.model.gwModel.PinCode;

public class ReceivedConfirmPinCodeResponse extends ApiResponse {

	private String referenceNumber;
	private List<PinCode> pinCodes;

	public List<PinCode> getPinCodes() {
		return pinCodes;
	}

	public void setPinCodes(List<PinCode> pinCodes) {
		this.pinCodes = pinCodes;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	@Override
    public String toString() {
    	return "{referenceNumber: "+referenceNumber+",List<PinCode>: "+pinCodes+"}";
    }
}
