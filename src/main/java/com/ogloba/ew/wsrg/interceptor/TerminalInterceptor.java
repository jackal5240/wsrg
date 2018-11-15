package com.ogloba.ew.wsrg.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ogloba.ew.wsrg.EwWsrg;
import com.ogloba.ew.wsrg.service.TerminalService;
import com.ogloba.ew.wsrg.utils.ContextUtils;
import com.woodoos.ew.common.base.SystemService;

@Component
public class TerminalInterceptor implements HandlerInterceptor{
	@Autowired
	protected ServletContext context;
	
	@Autowired
	private TerminalService terminalService;
	
	@Autowired
	private SystemService systemService;
	
	private Logger log = LoggerFactory.getLogger(TerminalInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String merchantId = getMerchantId(request);
//		String merchantId = "77777";
        String ecrNo = request.getHeader(EwWsrg.HEADER_ECRNO);
        Integer tidStoreDig = systemService.getTidStoreDigits();
        Integer tidEcrNoDig = systemService.getTidEcrNoDigits();
        String terminalId = StringUtils.leftPad(merchantId, tidStoreDig, '0') + StringUtils.leftPad(ecrNo, tidEcrNoDig, '0');
        
        if(StringUtils.isBlank(merchantId) || StringUtils.isBlank(ecrNo) || StringUtils.isBlank(terminalId)) {
        	log.error("terminalId / ecrNo / merchantId is null");
			throw new RuntimeException("Terminal unauthorized_1");
		}
        
        String clientTime = request.getHeader(EwWsrg.HEADER_CLIENT_TIME);
		String passphrase = terminalService.getPassphrase(merchantId);
		String dbMd5HashPassphrase = DigestUtils.md5Hex(passphrase + clientTime);
		String termainalHashPassphrase = getPassphrase(request);
//		String termainalHashPassphrase = dbMd5HashPassphrase;
        
		if(StringUtils.isBlank(clientTime) || StringUtils.isBlank(passphrase) || StringUtils.isBlank(termainalHashPassphrase)) {
			log.error("clientTime / passphrase / terminalHashPassphrase is null");
			throw new RuntimeException("Terminal unauthorized_2");
		}
		
		if (dbMd5HashPassphrase.equals(termainalHashPassphrase)) {
			autoCreateEcrTerminal(terminalId, merchantId);
			
			ContextUtils.setTerminalId(request, terminalId);
			ContextUtils.setTerminalNumber(request, ecrNo);
		} else {
			throw new RuntimeException("Passphrase mismatch");
		}
		
		return true;
	}
	
	private String getMerchantId(HttpServletRequest request) {
		String authString = request.getHeader(EwWsrg.HEADER_AUTHORIZATION);
		String merchantId = "";
        //Get encoded user and password after "BASIC "
		if (authString != null){
			String userpassBase64 = authString.substring(6);
        String userpassword = new String(Base64.decodeBase64(userpassBase64));
        int colonIdx = userpassword.indexOf(':');
        	merchantId = userpassword.substring(0, colonIdx);
		}
        
        context.log("merchantId id: " + merchantId);
		return merchantId == null ? "" : merchantId;
	}
	
	private String getPassphrase(HttpServletRequest request) {
		String authString = request.getHeader(EwWsrg.HEADER_AUTHORIZATION);
		String passphrase = "";
        //Get encoded user and password after "BASIC "
		if (authString != null){
			String userpassBase64 = authString.substring(6);
        String userpassword = new String(Base64.decodeBase64(userpassBase64));
        int colonIdx = userpassword.indexOf(':');
        passphrase = userpassword.substring(colonIdx + 1, userpassword.length());
		}
        
        context.log("passphrase id: " + passphrase);
		return passphrase == null ? "" : passphrase;
	}

	private void autoCreateEcrTerminal(String terminalId , String merchantId) {
		
		if (terminalService.isTerminalExists(terminalId)) {
			return;
		}
		
		terminalService.createEcrTerminal(terminalId, merchantId);
	}
}
