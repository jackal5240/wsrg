package com.ogloba.ew.wsrg.api.message;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ogloba.ew.wsrg.api.model.gwModel.Parameter;

public class ReservePinCodeRequest extends SalesRequest {
	
	@NotBlank
	private String gencode;
	
	@NotNull
	private Integer quantity;
	
	private String dtuTarget;
	
	private BigDecimal dtuAmount;
	
	private List<Parameter> parameters;
	
	public String getGencode() {
		return gencode;
	}
	
	public void setGencode(String gencode) {
		this.gencode = gencode;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getDtuTarget() {
		return dtuTarget;
	}
	
	public void setDtuTarget(String dtuTarget) {
		this.dtuTarget = dtuTarget;
	}
	
	public BigDecimal getDtuAmount() {
		return dtuAmount;
	}
	
	public void setDtuAmount(BigDecimal dtuAmount) {
		this.dtuAmount = dtuAmount;
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	@Override
    public String toString() {
    	return "{transactionNumber: "+transactionNumber+",gencode: "+gencode+",quantity: "+quantity
    			+",cashierId: "+cashierId+",dtuTarget: "+dtuTarget+",dtuAmount: "+dtuAmount+",parameters: "+parameters+"}";
    }
}
