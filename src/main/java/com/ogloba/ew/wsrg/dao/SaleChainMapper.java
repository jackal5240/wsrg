package com.ogloba.ew.wsrg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ogloba.ew.wsrg.api.model.gwModel.MessageLog;
import com.ogloba.ew.wsrg.plugin.model.SaleschainParm;

@Mapper
public interface SaleChainMapper {
	
	@Select("SELECT ECR_PASSPHRASE FROM EW_TB_MERCHANT WHERE SEQNO = #{merchantId}")
	String getPassphrase(@Param("merchantId")String merchantId);
	
	@Select("SELECT COUNT(*) FROM EW_TB_MERCHANT WHERE SEQNO = #{merchantId} AND ECR_PASSPHRASE = #{passphrase}")
	int validMerchantPassphrase(@Param("merchantId")String merchantId, @Param("passphrase")String passphrase);
	
	@Insert("INSERT INTO " + 
			"EW_TB_POS_MESSAGE_LOG(SEQNO, ERROR_MSG, RETURN_CODE, MESSAGE_NO, MERCHANT_SEQNO," +
			"TERMINAL_ID, IN_PARAMETER, OUT_PARAMETER," + 
			"MESSAGE_TIME, CRT_USERID, CRT_DT, UPD_USERID, UPD_DT)" + 
			"VALUES (EW_SQ_POS_MESSAGE_LOG_SEQNO.NEXTVAL, #{errorMsg}, #{returnCode}, #{messageNo}, #{merchantSeqno}," + 
			"#{terminalId}, #{inParameter}, #{outParameter}, "+
			"CURRENT_TIMESTAMP, 'Auto', CURRENT_TIMESTAMP, 'Auto', CURRENT_TIMESTAMP)")
	int recordMessageLog(MessageLog messageLog);
	
	@Select("SELECT * FROM EW_TB_INTEGRATE_KV WHERE SALESCHAIN_SEQNO = #{saleschainSeqno} AND SALESCHAIN_TYPE = #{saleschaintType} " +
			"AND PRODUCT_SEQNO = #{productSeqno}")
	List<SaleschainParm> queryIntegrateParam(@Param("saleschainSeqno")String saleschainSeqno, @Param("saleschaintType")String saleschaintType,
											 @Param("productSeqno")Integer productSeqno);
	
	@Select("SELECT SEQNO FROM RTA_TB_SAM_KEY WHERE DEVICEID = #{terminalId}")
	String getTerminal(@Param("terminalId")String terminalId);
	
	@Insert("INSERT INTO EW_TB_TERMINAL (\n" + 
			"                      SEQNO, TERMINAL_ID, CUST_TERM_ID, \n" + 
			"                      TERM_SERIAL, MODEL_ID, TERMINAL_TYPE, \n" + 
			"                      MERCHANT_SEQNO, STATUS, LAST_TRANS_DATE, \n" + 
			"                      INIT_KEY, CURR_KEY, OLD_KEY, \n" + 
			"                      KEY_CNT_BEFORE_CHANGE, DOWNLOAD_CNT, HARDWARE_VERSION, \n" + 
			"                      SOFTWARE_VERSION, IS_EXTERNAL_PINPAD, IS_AUTO_UPDATE, \n" + 
			"                      IS_DOWNLOADABLE, LAST_DOWNLOAD_DATE, LAST_ERROR, \n" + 
			"                      UPDATE_TIME, RECONC_TIME, VERSION, \n" + 
			"                      CRT_USERID, CRT_DT, UPD_USERID, \n" + 
			"                      UPD_DT, IS_SCHEDULE_SETTING_DOWNLOAD, IS_UPDATE_APPLICATION, \n" + 
			"                      IS_LOCK, LOGIN_FAIL_COUNT, LAST_CONN_DATE, \n" + 
			"                      LAST_PIN_CODE_SEQNO, POS_KIND, EXPECTED_SOFTWARE_VERSION, \n" + 
			"                      STATUS_TIME) \n" + 
			"               VALUES (\n" + 
			"                      EW_SQ_TERMINAL_SEQNO.NEXTVAL, #{terminalId}, NULL, \n" + 
			"                      NULL, NULL, 'ME', \n" + 
			"                      #{merchantSeqno}, '03', NULL, \n" + 
			"                      NULL, NULL, NULL, \n" + 
			"                      NULL, NULL, NULL, \n" + 
			"                      NULL, NULL, NULL, \n" + 
			"                      NULL, NULL, NULL, \n" + 
			"                      NULL, NULL, 1, \n" + 
			"                      'AUTO', CURRENT_TIMESTAMP, 'AUTO', \n" + 
			"                      CURRENT_TIMESTAMP, NULL, NULL, \n" + 
			"                      NULL, 0, NULL, \n" + 
			"                      NULL, NULL, NULL, \n" + 
			"                      CURRENT_TIMESTAMP)")
	void autoCreateECRTerminal(@Param("terminalId")String terminalId, @Param("merchantSeqno")String merchantSeqno);
}
