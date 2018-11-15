package com.ogloba.ew.wsrg.plugin.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ogloba.ew.wsrg.api.model.spModel.ConfirmPinCodeByTerminal;
import com.ogloba.ew.wsrg.api.model.spModel.ReceivedConfirmPinCodeByTerminal;
import com.ogloba.ew.wsrg.api.model.spModel.ReservationPinCodeByTerminal;
import com.ogloba.ew.wsrg.plugin.ProductPlugin;

@Component
@Scope("prototype")
public class DefaultPlugin implements ProductPlugin{
	
	private static Log log = LogFactory.getLog(DefaultPlugin.class);

	@Override
	public boolean handleExtraBeforeReservation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraAfterReservation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraBeforeConfirmation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraAfterConfirmation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraBeforeReceivedConfirmation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraAfterReceivedConfirmation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraBeforeCancellation(Object request, Object response) {
		
		return true;
	}

	@Override
	public boolean handleExtraAfterCancellation(Object request, Object response) {
		
		return true;
	}

}
