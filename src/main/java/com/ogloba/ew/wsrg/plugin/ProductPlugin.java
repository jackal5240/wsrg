package com.ogloba.ew.wsrg.plugin;

import java.util.Map;

/**
 * ProductPlugin interface define the a plugin that will be called to execute
 * extra work before finishing pincode confirmation.
 * 
 * Every product could have plugin doing extra work besides the main logic.
 * @author Jim
 *
 */
public interface ProductPlugin {
    
    public boolean handleExtraBeforeReservation(Object request, Object response);
    
    public boolean handleExtraAfterReservation(Object request, Object response);
    
    public boolean handleExtraBeforeConfirmation(Object request, Object response);
    
    public boolean handleExtraAfterConfirmation(Object request, Object response);
    
    public boolean handleExtraBeforeReceivedConfirmation(Object request, Object response);
    
    public boolean handleExtraAfterReceivedConfirmation(Object request, Object response);
   
    public boolean handleExtraBeforeCancellation(Object request, Object response);
    
    public boolean handleExtraAfterCancellation(Object request, Object response);
    /*
    public boolean handleExtraBeforeQuery(Object request, Object response);
    
    public boolean handleExtraAfterQuery(Object request, Object response);
    
    public boolean handleExtraBeforeRefund(Object request, Object response);
    
    public boolean handleExtraAfterRefund(Object request, Object response);
    */
}
