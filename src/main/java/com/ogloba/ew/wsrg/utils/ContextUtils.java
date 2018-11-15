package com.ogloba.ew.wsrg.utils;


import javax.servlet.http.HttpServletRequest;

import com.ogloba.ew.wsrg.EwWsrg;

public abstract class ContextUtils {
	
	public static String getTerminalNumber(HttpServletRequest request) {
        return (String) request.getAttribute(EwWsrg.ATTR_TERMINAL_NUMBER);
    }
    
    public static void setTerminalNumber(HttpServletRequest request, String terminalNumber) {
        request.setAttribute(EwWsrg.ATTR_TERMINAL_NUMBER, terminalNumber);
    }
	
    public static String getTerminalId(HttpServletRequest request) {
        return (String) request.getAttribute(EwWsrg.ATTR_TERMINAL_ID);
    }
    
    public static void setTerminalId(HttpServletRequest request, String terminalId) {
        request.setAttribute(EwWsrg.ATTR_TERMINAL_ID, terminalId);
    }
    
    public static String getApp(HttpServletRequest request) {
        return (String) request.getAttribute(EwWsrg.AUTH_APP_ATTR);
    }
    
    public static void setApp(HttpServletRequest request, String app) {
        request.setAttribute(EwWsrg.AUTH_APP_ATTR, app);
    }
    
    public static String getAppVersion(HttpServletRequest request) {
        return (String) request.getAttribute(EwWsrg.APP_VERSION_ATTR);
    }
    
    public static void setAppVersion(HttpServletRequest request, String appVersion) {
        request.setAttribute(EwWsrg.APP_VERSION_ATTR, appVersion);
    }
    
    public static String getApiVersion(HttpServletRequest request) {
        return (String) request.getAttribute(EwWsrg.API_VERSION_ATTR);
    }
    
    public static void setApiVersion(HttpServletRequest request, String apiVersion) {
        request.setAttribute(EwWsrg.API_VERSION_ATTR, apiVersion);
    }
    
    public static String getLocale(HttpServletRequest request){
    	return (String) request.getAttribute("com.ogloba.ew.wsrg.locale");
    }
    
    public static void setLocale(HttpServletRequest request, String locale) {
        request.setAttribute("com.ogloba.ew.wsrg.locale", locale);
    }
}
