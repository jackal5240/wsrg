package com.ogloba.ew.wsrg.api.message;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ogloba.ew.wsrg.api.model.gwModel.ReconciliationRecord;

public class ReconciliationRequest {

	@NotBlank
	private boolean isOnlySuccessfully;
	
	@NotNull
	private List<ReconciliationRecord> reconciliationRecords;

	public boolean isOnlySuccessfully() {
		return isOnlySuccessfully;
	}

	public void setOnlySuccessfully(boolean isOnlySuccessfully) {
		this.isOnlySuccessfully = isOnlySuccessfully;
	}

	public List<ReconciliationRecord> getReconciliationRecords() {
		return reconciliationRecords;
	}

	public void setReconciliationRecords(List<ReconciliationRecord> reconciliationRecords) {
		this.reconciliationRecords = reconciliationRecords;
	}
	
	
}
