package com.ogloba.ew.wsrg.features.sales;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.ogloba.ew.wsrg.api.message.ConfirmPinCodeResponse;
import com.ogloba.ew.wsrg.api.message.ReceivedConfirmPinCodeResponse;
import com.ogloba.ew.wsrg.api.message.ReservePinCodeRequest;
import com.ogloba.ew.wsrg.api.message.ReservePinCodeResponse;
import com.ogloba.ew.wsrg.api.message.SalesRequest;
import com.ogloba.ew.wsrg.api.model.spModel.ConfirmPinCodeByTerminal;
import com.ogloba.ew.wsrg.api.model.spModel.ReceivedConfirmPinCodeByTerminal;
import com.ogloba.ew.wsrg.api.model.spModel.ReservationPinCodeByTerminal;
import com.ogloba.ew.wsrg.plugin.impl.DefaultPlugin;
import com.woodoos.ew.common.EwCommonException;

@Service
public class SalesServiceImpl {
	
//	@Autowired
//	private PinCodeService pinCodeService;
	
	@Autowired
	private SalesDao salesDao;
	
	@Autowired
    private WebApplicationContext context;
	
	public ReservePinCodeResponse reservePinCode(ReservePinCodeRequest request, String terminalId,
			String appVersion, String terminalNumber) {
		
		ReservePinCodeResponse response = new ReservePinCodeResponse();
		
		ReservationPinCodeByTerminal spModel = new ReservationPinCodeByTerminal();
		
		spModel.setTerminalId(terminalId);
		spModel.setOperatorId(request.getCashierId());
		spModel.setTxno(request.getTransactionNumber());
		spModel.setQuantity("1");
		spModel.setCashRegisterNo(terminalNumber);
		spModel.setGencode(request.getGencode());
		spModel.setInputAmount(request.getDtuAmount());
		
        DefaultPlugin defaultPlugin = (DefaultPlugin) context.getBean("defaultPlugin");
        defaultPlugin.handleExtraBeforeReservation(spModel, spModel);
        
//        String genCode = ConvertUtils.convert(salesDao.executeGetEancodeByReferenceNumber(referenceNumber));
        
        try {
        	salesDao.executeReservePinCodeForTerminal(spModel);
        	
        	String returnCodeStr = spModel.getReturnCode();
        	System.out.println("returnCodeStr == " + returnCodeStr);
        	
        }catch (EwCommonException e) {
        	response.setIsSuccessful("false");
        	response.setErrorMessage("reservePinCodeForTerminal error");
        	response.setErrorDetail(ConvertUtils.convert(e.getCode()) + e.getMessage());
        	response.setErrorCode("3001");
        	
        	return response;
        }
        
        defaultPlugin.handleExtraAfterReservation(spModel, spModel);
        
        response.setIsSuccessful("true");
        
		return response;
	}
	
	public ConfirmPinCodeResponse confirmPinCode(SalesRequest request, String terminalId,
			String appVersion, String terminalNumber, String referenceNumber) {
		
		ConfirmPinCodeResponse response = new ConfirmPinCodeResponse();
		
		ConfirmPinCodeByTerminal spModel = new ConfirmPinCodeByTerminal();
		
		spModel.setTerminalId(terminalId);
		spModel.setOperatorId(request.getCashierId());
		spModel.setTransactionNumber(request.getTransactionNumber());
		
        DefaultPlugin defaultPlugin = (DefaultPlugin) context.getBean("defaultPlugin");
        defaultPlugin.handleExtraBeforeConfirmation(spModel, spModel);
        
        try {
        	salesDao.executeConfirmPinCodeForTerminal(spModel);
        	
        	String returnCodeStr = spModel.getReturnCode();
        	System.out.println("returnCodeStr == " + returnCodeStr);
        	
        }catch (EwCommonException e) {
        	response.setIsSuccessful("false");
        	response.setErrorMessage("reservePinCodeForTerminal error");
        	response.setErrorDetail(ConvertUtils.convert(e.getCode()) + e.getMessage());
        	response.setErrorCode("3001");
        	
        	return response;
        }
        
        defaultPlugin.handleExtraAfterConfirmation(spModel, spModel);
        
        response.setIsSuccessful("true");
		
		return response;
	}
	
	public ReceivedConfirmPinCodeResponse receivedConfirmPinCode(SalesRequest request, String terminalId,
			String appVersion, String terminalNumber, String referenceNumber) {
		
		ReceivedConfirmPinCodeResponse response = new ReceivedConfirmPinCodeResponse();
		
		ReceivedConfirmPinCodeByTerminal spModel = new ReceivedConfirmPinCodeByTerminal();
		
		spModel.setTerminalId(terminalId);
		spModel.setOperatorId(request.getCashierId());
		spModel.setTransactionNumber(request.getTransactionNumber());
		
        DefaultPlugin defaultPlugin = (DefaultPlugin) context.getBean("defaultPlugin");
        defaultPlugin.handleExtraBeforeReceivedConfirmation(spModel, spModel);
        
        try {
        	salesDao.executeReceivedConfirmPinCodeForTerminal(spModel);
        	
        	String returnCodeStr = spModel.getReturnCode();
        	System.out.println("returnCodeStr == " + returnCodeStr);
        	
        }catch (EwCommonException e) {
        	response.setIsSuccessful("false");
        	response.setErrorMessage("reserveConfirmPinCodeForTerminal error");
        	response.setErrorDetail(ConvertUtils.convert(e.getCode()) + e.getMessage());
        	response.setErrorCode("3001");
        	
        	return response;
        }
        
        defaultPlugin.handleExtraAfterReceivedConfirmation(spModel, spModel);
        
        response.setIsSuccessful("true");
		
		return response;
	}
	
}
