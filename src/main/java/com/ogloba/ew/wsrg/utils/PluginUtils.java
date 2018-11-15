package com.ogloba.ew.wsrg.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.woodoos.ew.common.EwCommonException;
import com.woodoos.ew.common.plugin.ProductPlugin;
import com.woodoos.ew.common.plugin.ProviderPlugin;
import com.woodoos.ew.common.product.ProductService;
import com.woodoos.jap.common.JapCommon;
import com.woodoos.jap.common.JapException;

public class PluginUtils {
    
    @Autowired
    private static WebApplicationContext context;
	
    public static Object getPluginObjectByEancode(String eancode, String pluginPackageName) {
    	
    	ProductService productService = (ProductService) context.getBean(ProductService.class);
        String pluginName = productService.getProductPluginNameByGencode(eancode);
        
        return getPluginObjectByPluginName(pluginName, pluginPackageName);
    }
    
    public static Object getPluginObjectByPluginName(String pluginName, String pluginPackageName) {
        Object pluginObj = null;
        
        if (pluginName != null) {
            try {
                if (pluginName.startsWith("#")) {
                   String plugin = pluginName.substring(1);
                   pluginObj = JapCommon.getBean(plugin);
                } else if (pluginName.startsWith("%")) {
                    String plugin = pluginName.substring(1);
                    Class<?> pluginClass = Class.forName(plugin);
                    pluginObj = JapCommon.getBean(pluginClass);
                }  else if (pluginName.startsWith("&")) {
                    String plugin = pluginName.substring(1);
                    Class<?> pluginClass = Class.forName(new StringBuffer(pluginPackageName).append(".").append(plugin).toString());
                    pluginObj = JapCommon.getBean(pluginClass);
                } else {
                    Class<?> pluginClass = Class.forName(pluginName);
                    pluginObj = pluginClass.newInstance();
                }

                Class<ProductPlugin> pluginInterface = ProductPlugin.class;
                Class<ProviderPlugin> providerPluginInterface = ProviderPlugin.class;
                if (!pluginInterface.isInstance(pluginObj) || providerPluginInterface.isInstance(pluginObj)) {
                    throw new RuntimeException(pluginObj.getClass().getName() + " must implement " + pluginInterface.getName()
                    		+ " or " + providerPluginInterface.getName());
                }

                return pluginObj;

            } catch (Exception e) {
                //先當作預設沒有  
            }
        }
        
        return pluginObj;
    }
    
    public static Boolean executMethodByMethodName(Object pluginObj, String methodName,  Map<String, Object> requestMap,  Map<String, Object> responseMap) {
        if (pluginObj != null) {
            if (StringUtils.isBlank(methodName)) {
                throw new IllegalArgumentException("methodName must have value");
            }
            
            try {
                Method method = pluginObj.getClass().getMethod(methodName, Map.class, Map.class);
                return (Boolean) method.invoke(pluginObj, requestMap, responseMap);
            } catch (NoSuchMethodException e) {
                throw new EwCommonException(e);
            } catch (IllegalArgumentException e) {
                throw new EwCommonException(e);
            } catch (IllegalAccessException e) {
                throw new EwCommonException(e);
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof EwCommonException) {
                    throw (EwCommonException) e.getTargetException();
                } else if (e.getTargetException() instanceof JapException) {
                    throw (JapException) e.getTargetException();
                }
                throw new EwCommonException(e);
            }  catch (EwCommonException e) {
                throw e;
            } catch (Exception e) {
                /*try {
                    Method method = pluginObj.getClass().getMethod("claimExceptionCaught", Map.class, Map.class);
                    method.invoke(pluginObj, requestMap, responseMap);
                } catch (Exception e1) {
                    throw new RuntimeException(e1);
                }*/
                
                throw new EwCommonException(e);
            }
        }
        
        return true;
    }
    
}
