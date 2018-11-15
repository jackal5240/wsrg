package com.ogloba.ew.wsrg.api.message;

import java.util.List;

import com.ogloba.ew.wsrg.api.model.gwModel.Product;
import com.ogloba.ew.wsrg.api.model.gwModel.Provider;

public class ProductsResponse {

	private List<Provider> providersRecords;
	
	private List<Product> productsRecords;

	public List<Provider> getProvidersRecords() {
		return providersRecords;
	}

	public void setProvidersRecords(List<Provider> providersRecords) {
		this.providersRecords = providersRecords;
	}

	public List<Product> getProductsRecords() {
		return productsRecords;
	}

	public void setProductsRecords(List<Product> productsRecords) {
		this.productsRecords = productsRecords;
	}	
	
}
