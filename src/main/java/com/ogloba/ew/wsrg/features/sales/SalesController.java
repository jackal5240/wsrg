package com.ogloba.ew.wsrg.features.sales;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogloba.ew.wsrg.api.message.ConfirmPinCodeResponse;
import com.ogloba.ew.wsrg.api.message.ReceivedConfirmPinCodeResponse;
import com.ogloba.ew.wsrg.api.message.ReservePinCodeRequest;
import com.ogloba.ew.wsrg.api.message.ReservePinCodeResponse;
import com.ogloba.ew.wsrg.api.message.SalesRequest;
import com.ogloba.ew.wsrg.utils.ContextUtils;
import com.woodoos.jap.common.util.I18nUtils;

//import com.ogloba.ew.m3c.data.CreditCardPaymentInfo;
//import com.ogloba.ew.m3c.gateway.util.ContextUtils;
//import com.ogloba.ew.m3c.message.sales.CancelOrderRequest;
//import com.ogloba.ew.m3c.message.sales.GetOpenInvoiceRequest;
//import com.ogloba.ew.m3c.message.sales.GetOpenInvoiceResponse;
//import com.ogloba.ew.m3c.message.sales.GetProductStockRequest;
//import com.ogloba.ew.m3c.message.sales.GetProductStockResponse;
//import com.ogloba.ew.m3c.message.sales.GetProductsRequest;
//import com.ogloba.ew.m3c.message.sales.GetProductsResponse;
//import com.ogloba.ew.m3c.message.sales.GetProvidersRequest;
//import com.ogloba.ew.m3c.message.sales.GetProvidersResponse;
//import com.ogloba.ew.m3c.message.sales.MakeOrderPaymentRequest;
//import com.ogloba.ew.m3c.message.sales.MakePaymentRequest;
//import com.ogloba.ew.m3c.message.sales.MakePaymentResponse;
//import com.ogloba.ew.m3c.message.sales.PlaceOrderResponse;
//import com.ogloba.ew.m3c.util.I18nUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Sales")
@RestController
public class SalesController {

	private static Logger logger = LoggerFactory.getLogger(SalesController.class);

	@Autowired
	private SalesServiceImpl salesServiceImpl;
	
	@ApiOperation(value = "Reserve", notes = "Reserve Pin Code.")
	@PostMapping(value = "/sales/reservation")
	public ReservePinCodeResponse reservePinCode(@Valid ReservePinCodeRequest reservePinCodeRequest,
			HttpServletRequest request) {
		String dingVersion = request.getParameter("DingVersion");
		String appVersion = ContextUtils.getAppVersion(request);
		// String apiVersion = ContextUtils.getApiVersion(request);
		String terminalId = ContextUtils.getTerminalId(request);
		String terminalNumber = ContextUtils.getTerminalNumber(request);
		
		Locale locale = I18nUtils.toLocale( ContextUtils.getLocale(request));
		I18nUtils.setCurrentThreadLocale(locale);

		ReservePinCodeResponse response = salesServiceImpl.reservePinCode(reservePinCodeRequest,
				terminalId, appVersion, terminalNumber);
		response.setIsSuccessful("Reserve");
		return response;
	}
	
	@ApiOperation(value = "Confirm", notes = "Confirm Pin Code Reservation, deduct merchant available, call after customer pay.")
	@PostMapping(value = "/sales/reservation/{referenceNumber}/confirmation")
	public ConfirmPinCodeResponse confirmPinCode(@Valid SalesRequest confirmPinCodeRequest,
			@PathVariable String referenceNumber, HttpServletRequest request) {

		String appVersion = ContextUtils.getAppVersion(request);
		// String apiVersion = ContextUtils.getApiVersion(request);
		String terminalId = ContextUtils.getTerminalId(request);
		String terminalNumber = ContextUtils.getTerminalNumber(request);
		
		Locale locale = I18nUtils.toLocale( ContextUtils.getLocale(request));
		I18nUtils.setCurrentThreadLocale(locale);

		ConfirmPinCodeResponse response = salesServiceImpl.confirmPinCode(confirmPinCodeRequest, terminalId,
				appVersion, terminalNumber, referenceNumber);
		response.setIsSuccessful("Confirm");
		return response;
	}
	
	@ApiOperation(value = "Received Confirm", notes = "Confirm Pin Code Received Confirmation of Reservation, call after ticket printed successully.")
	@PostMapping(value = "/sales/reservation/{referenceNumber}/receivedConfirmation")
	public ReceivedConfirmPinCodeResponse receivedConfirmPinCode(@Valid SalesRequest receivedConfirmPinCodeRequest,
			@PathVariable String referenceNumber,
			HttpServletRequest request) {

		String appVersion = ContextUtils.getAppVersion(request);
		// String apiVersion = ContextUtils.getApiVersion(request);
		String terminalId = ContextUtils.getTerminalId(request);
		String terminalNumber = ContextUtils.getTerminalNumber(request);
		
		Locale locale = I18nUtils.toLocale( ContextUtils.getLocale(request));
		I18nUtils.setCurrentThreadLocale(locale);

		ReceivedConfirmPinCodeResponse response = salesServiceImpl.receivedConfirmPinCode(receivedConfirmPinCodeRequest,
				terminalId, appVersion, terminalNumber, referenceNumber);
		response.setIsSuccessful("Received Confirm");
		return response;
	}
	
}
