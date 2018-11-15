package com.ogloba.ew.wsrg;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ogloba.ew.wsrg.api.model.gwModel.MessageLog;
import com.ogloba.ew.wsrg.dao.SaleChainMapper;
import com.ogloba.ew.wsrg.utils.ContextUtils;

@WebFilter(urlPatterns = "/*", filterName="posLogFilter")
public class PosMessageLogFilter implements Filter{
	
	@Autowired
	SaleChainMapper salesChainsDao;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {		
		ReadBodyRequestWrapper requestWrapper = new ReadBodyRequestWrapper((HttpServletRequest) request);
		ReadBodyResponseWrapper responseWrapper = new ReadBodyResponseWrapper((HttpServletResponse)response);
		String requestURL = ((HttpServletRequest) request).getServletPath();
		String requestMethod = ((HttpServletRequest) request).getMethod();
		String errorCode = "0";
		String errorMessage = "";
		String responseBody = "";
		int messageCode = getMessageCode(requestURL, requestMethod);;

		if (messageCode != 0) {
			
			String requestBody = "RequestUrl : " + requestURL + "\r\n" + requestWrapper.getBody();
		
			chain.doFilter(requestWrapper, responseWrapper);
			responseWrapper.flushBuffer();
		
			byte[] copy =  responseWrapper.getCopy();
			JsonObject json = new JsonParser().parse(new String(copy, response.getCharacterEncoding())).getAsJsonObject();
			responseBody = json.toString();
			JsonElement success = json.get("isSuccessful");
		
			if ("false".equals(success.getAsString())) {
				errorCode = json.get("errorCode").getAsString();
				errorMessage = String.format("errorMessage : %s errorDetail : %s", json.get("errorMessage").getAsString(),
																				   json.get("errorDetail").getAsString());
			}
			
			String terminalId = getTerminalIdFromRequestAttr((HttpServletRequest)request);
			String merchantId = ((HttpServletRequest)request).getHeader(EwWsrg.HEADER_MERCHANT_ID);
			
			MessageLog messageLog = new MessageLog();
			messageLog.setErrorMsg(errorMessage);
			messageLog.setTerminalId(terminalId);
			messageLog.setMerchantSeqno(merchantId);
			messageLog.setReturnCode(Integer.valueOf(errorCode));
			messageLog.setInParameter(requestBody);
			messageLog.setOutParameter(responseBody);
			messageLog.setMessageNo(messageCode);
			
			recordMessageLogForTerminal(messageLog);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private int getMessageCode(String requestURL, String requestMethod) {
		int messageCode = 0;
		String [] messageKey = MessageCode.names();
		String path = getPath(requestURL);
		
		for (int i = 0; i < messageKey.length; i++) {
			String keyName = messageKey[i];
			
			if (keyName.equals(path)) {
				messageCode = MessageCode.valueOf(keyName).getMssageCode();
			}
		}
		
		return messageCode;
	}
	
	private String getPath(String requestURL) {
		String path = null;
		String[] url = StringUtils.split(requestURL, "/");
		
		for (int i = url.length -1; i >= 0 ; i--) {
			
			if (!isNumeric(url[i])) {
				path = url[i];
				break;
			}
		}
		return path;
	}
	
	public boolean isNumeric(String str){  
	    Pattern pattern = Pattern.compile("[0-9]*");  
	    return pattern.matcher(str).matches();     
	}
	
	private String getTerminalIdFromRequestAttr(HttpServletRequest request) {
		String terminalId;
		terminalId = ContextUtils.getTerminalId(request);
		return terminalId == null ? "" : terminalId;
	}
	
	private void recordMessageLogForTerminal(MessageLog messageLog) {
		salesChainsDao.recordMessageLog(messageLog);
	}

	public enum MessageCode {
		authentication(159), confirmation(160), cancellation(162), reconciliation(164), query(161), refund(163);
		
		private int messageCode;
		
		MessageCode(int messageCode) {
			this.messageCode = messageCode;
		}
		
		public int getMssageCode(){
			return this.messageCode;
		}
		
		public static String[] names() {
		    return Arrays.toString(MessageCode.values()).replaceAll("^.|.$", "").split(", ");
		}
	}
}
