package com.ogloba.ew.wsrg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogloba.ew.wsrg.dao.SaleChainMapper;
import com.woodoos.ew.common.saleschain.SalesChainService;

@Service
public class TerminalService {
	
//	@Autowired
//	private SalesChainService salesChainService;
	
	@Autowired
	private SaleChainMapper saleChainMapper;
	
	public String getPassphrase(String merchantId) {
		return saleChainMapper.getPassphrase(merchantId).equals(null) ? "" : saleChainMapper.getPassphrase(merchantId);
	}
	
	public boolean isTerminalExists(String terminalId) {
		return saleChainMapper.getTerminal(terminalId) == null ? false : true;
//		return salesChainService.getTerminal(terminalId) == null ? false : true;
	}
	
	public void createEcrTerminal(String terminalId, String merchantId) {
		saleChainMapper.autoCreateECRTerminal(terminalId, merchantId);
//		salesChainService.autoCreateECRTerminal(terminalId, merchantId);
	}
	
	public boolean authMerchantPasspharse(String merchantId, String passphrase) {
		
		int count = saleChainMapper.validMerchantPassphrase(merchantId, passphrase);
		
		return count > 0 ? true : false;
	}

}
