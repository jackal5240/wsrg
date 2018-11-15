package com.ogloba.ew.wsrg.features;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogloba.ew.wsrg.utils.ContextUtils;

@RestController

public class TestController {
	
	@GetMapping(path="/test")
	public String test(HttpServletRequest request) {
		String terminalId = ContextUtils.getTerminalId(request);
		System.out.println("terminalId == " + terminalId);
		return "wefwfwfwefwefweweffw";
	}
	
	@GetMapping(path="/testLeo")
	public String testLeo(HttpServletRequest request) {
		String terminalId = ContextUtils.getTerminalId(request);
		System.out.println("terminalId == " + terminalId);
		return terminalId;
	}
/*
	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private SystemParamDao paramDao;
	
	@Autowired 
	private TransactionsMapper transMapper;
//	@Autowired
//	private SystemService service;
//	@Autowired
//	private TransactionService testService;
	@Autowired
	SmsDao smsDao;
	
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	
	
	@GetMapping(path="/test")
	public DemoResponse test() {
//		String test = paramDao.getValue("SMS_CLASS_NAME");
//		
//		DemoResponse res = new DemoResponse();
//		String sysCode = service.getSystemCustCode();
//		res.setText(test + " syscode : " + sysCode);
	
		
//        TransPaymentInfo tp = new TransPaymentInfo();
//        tp.setSeqno(12345);
//        tp.setBuyerIdentityNo("12312312312312");
//        tp.setIdentityType("barcode");
//        tp.setMemo("hi");
//        tp.setTransactionName("ogloba");
//        tp.setTransactionInfo("test");
//        tp.setUpdUserid("test");
//        tp.setCrtUserid("test");
//        
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("TEST", tp);
//        
//        testObject(map);
        
//        transMapper.insertTransPaymentInfo(tp);
        
//        System.out.println("=====================" + tp.getPartnerTransId());
//        
//        TransPaymentInfo transPaymentInfo = transMapper.queryPaymentInfoByTransId("1537365582444604736889101");
//        
//		String extendInfo = transPaymentInfo.getTransactionInfo();
//		
//		extendInfo = StringUtils.replace(extendInfo, "'", "\"");
//		
//		System.out.println("extendInfo=====================" + extendInfo);
//		AlipayExtendInfo alipayExtendInfo = null;
//        
//    	ObjectMapper obMap = new ObjectMapper();
//    	try {
//    		alipayExtendInfo = obMap.readValue(extendInfo, AlipayExtendInfo.class);
//		} catch (Exception e1) {
//			throw new EwCommonException("Alipay convert extend info object fail");
//		}
//    	
//    	
//    	 System.out.println("alipayExtendInfo=====================" + alipayExtendInfo.getSecondaryMerchantName());
		
//		
//		try {
//			testService.updateTransactionPayment("Test", "1234");
//		}catch (Exception e) {
//			log.debug(ExceptionUtils.getFullStackTrace(e));
//		}
//		
		
		return new DemoResponse();
		
	}
	
	@GetMapping(path="/test2")
	public void test2(HttpServletRequest requset) {
		
//		List<ReconciliationRecords> reconcs = reconciliationRequest.getReconciliationRecords();
//		
//	    reconcs.stream().forEach(rec -> System.out.println("===Refno== : " + rec.getReferenceNumber()));
//		
	
		List<Map<String, Object>> list = smsDao.findUnsentSms();
		
		HashMap<String, Object> req = new HashMap<String, Object>();
		req.put("TERMINALID", "122341");
		req.put("PASSWORD", "1234");
		String request = ProtocolUtils.encodeMapToString(req);
		
		Map<String, Object> args = new HashMap<String, Object>();
        args.put("request", request); // IN
        args.put("response", null); // OUT
        args.put("terminalType", null); // OUT
        args.put("custTermId", null); // OUT
        args.put("serverTime", null); // OUT
        args.put("wholesalerTimeZone", null); // OUT
        args.put("terminalTimeZone", null); // OUT
        args.put("cashier", null); // OUT
		
//		transMapper.executeLogin(args);
		
		System.out.println("========== response : " + args.get("response"));
		System.out.println("========== response : " + args.get("cashier"));
		
		list.stream().forEach(l -> System.out.println("==========" + l.get("MSISDN")));
		
//		testService.insertTransactionPass("Test", "5678");
		
		System.out.println("SMS CLASS NAME : " + paramDao.getValue("SMS_CLASS_NAME"));
		
		log.debug("insert successful");
		
	}
	
	private static String insertObjectSql;
    static {
        StringBuilder sb = new StringBuilder();
        
//        sb.append("insert into zz_data_test (seqno_test, data_msg, upd_dt) \n");
//        sb.append(" values \n");
//        sb.append(" (1, 'Leotest20180815', CURRENT_TIMESTAMP) \n");
        
        
        sb.append("INSERT INTO EW_TB_RECONCILIATION_PAYMENT \n");
        sb.append("(SEQNO, PARTNER_TRANS_ID, TRANS_ID, AMOUNT, FEE,\n"); 
        sb.append(" CURRENCY, SETTLEMENT, PAYMENT_TIME, TRANS_TYPE, REMARK,\n" );
        sb.append(" RATE, SETTLEMENT_TIME, RECONC_TRANS_TIME, RECONC_SETTLE_TIME, CRT_DT,\n" ); 
        sb.append(" CRT_USERID, UPD_DT, UPD_USERID) \n");
        sb.append("VALUES \n");
        sb.append("(EW_SQ_RECONCILIATION_PY_SEQNO.NEXTVAL, :PARTNER_TRANS_ID, :TRANS_ID, :AMOUNT, null,\n");
          sb.append(" :CURRENCY, null, :PAYMENT_TIME, :TRANS_TYPE, :REMARK,\n");
          sb.append(" null, null, null, null, CURRENT_TIMESTAMP,\n");
          
          sb.append(" 'TEST', CURRENT_TIMESTAMP, 'TEST') \n");
        
        
//        sb.append("(EW_SQ_RECONCILIATION_PY_SEQNO.NEXTVAL, '1609191709476943623709', '2016092021001004650228880395', 0.01, null,\n");
//        sb.append(" 'GBP', null, '2016-09-20 00:09:50', 'REVERSAL', '',\n");
//        sb.append(" null, null, null, null, CURRENT_TIMESTAMP,\n");
//        sb.append(" 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM') \n");
        
        insertObjectSql = sb.toString();
    }
    
    private void testObject(Map<String, Object> map) {
    	TransPaymentInfo tp = (TransPaymentInfo)map.get("TEST");
    	tp.setPartnerTransId("test method");
    	
    }
    */
}
