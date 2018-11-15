package com.ogloba.ew.wsrg.api.message;

import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.Provider;

public class ProvidersResponse {

	List<Provider> providerRecoders;

	public List<Provider> getProviderRecoders() {
		return providerRecoders;
	}

	public void setProviderRecoders(List<Provider> providerRecoders) {
		this.providerRecoders = providerRecoders;
	}
	
}
