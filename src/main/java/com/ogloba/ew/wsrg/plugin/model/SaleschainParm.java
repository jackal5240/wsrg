package com.ogloba.ew.wsrg.plugin.model;

public class SaleschainParm {
	
	private String saleschainSeqno;
	private String saleschainType;
	private Integer productSeqno;
	private String parmKey;
	private String parmValue;
	
	public String getSaleschainSeqno() {
		return saleschainSeqno;
	}
	public void setSaleschainSeqno(String saleschainSeqno) {
		this.saleschainSeqno = saleschainSeqno;
	}
	public String getSaleschainType() {
		return saleschainType;
	}
	public void setSaleschainType(String saleschainType) {
		this.saleschainType = saleschainType;
	}
	public Integer getProductSeqno() {
		return productSeqno;
	}
	public void setProductSeqno(Integer productSeqno) {
		this.productSeqno = productSeqno;
	}
	public String getParmKey() {
		return parmKey;
	}
	public void setParmKey(String parmKey) {
		this.parmKey = parmKey;
	}
	public String getParmValue() {
		return parmValue;
	}
	public void setParmValue(String parmValue) {
		this.parmValue = parmValue;
	}
}
