package com.ogloba.ew.wsrg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class VersionValidIntercetor extends HandlerInterceptorAdapter {

	//@Autowired
	//private GwAppService appService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

//		String appVersion = request.getHeader(EwWsgGateway.HEADER_APP_VERSION);
//		String resVersion = request.getHeader(EwWsgGateway.HEADER_RES_VERSION);
//		String authTerminal = (String) request.getAttribute(EwWsgGateway.AUTH_TERMINAL_ID_ATTR);
//
//        if (!StringUtils.isNotBlank(authTerminal)) {
//            return false;
//        }
//
//		System.out.println(appVersion + " : " + resVersion);
//		int code = appService.checkAppResVersion(authTerminal, appVersion, resVersion);
//
//		if (code != 0) {
//			throw new SolutionProductGatewayException(code);
//		}
		
		return true;
	}
}
