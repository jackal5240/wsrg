package com.ogloba.ew.wsrg.features.sales;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.ogloba.ew.wsrg.api.message.ConfirmPinCodeResponse;
import com.ogloba.ew.wsrg.api.message.ReservePinCodeResponse;
import com.ogloba.ew.wsrg.api.model.spModel.ConfirmPinCodeByTerminal;
import com.ogloba.ew.wsrg.api.model.spModel.ReceivedConfirmPinCodeByTerminal;
import com.ogloba.ew.wsrg.api.model.spModel.ReservationPinCodeByTerminal;

@Mapper
public interface SalesDao {
	
	static String queryEancodeByReferenceNumber = " SELECT EANCODE FROM EW_TB_TRANSACTION WHERE REFNO = ${referenceNumber}";
	
	@Select(queryEancodeByReferenceNumber)
	String executeGetEancodeByReferenceNumber(@Param("referenceNumber") String referenceNumber);
	
	@Results(id = "reservationInfo", value = {
    		// property 需與 ProductInfo 內所宣告的名字一樣
			@Result(property = "result", column = "RESULT", javaType = String.class),
			@Result(property = "error", column = "ERROR", javaType = String.class),
    		@Result(property = "amount", column = "AMOUNT", javaType = String.class),
            @Result(property = "refno", column = "REFNO", javaType = String.class),
            @Result(property = "loyaltyCardTitalPoints", column = "LOYALTYCARDTOTALPOINTS", javaType = String.class),
            @Result(property = "pincodeList", column = "PINCODELIST", javaType = String.class),
            @Result(property = "providerId", column = "PROVIDERID", javaType = String.class),
            @Result(property = "transSeqno", column = "TRANS_SEQNO", javaType = String.class),
            @Result(property = "vatRate", column = "VATRATE", javaType = String.class),
            @Result(property = "vatAmount", column = "VATAMOUNT", javaType = String.class)
    })
	@Select("{ CALL WSRG_PINCODERESERVATION(\n" +
			"          #{terminalId, jdbcType=VARCHAR},\n" +
            "          #{operatorId, jdbcType=VARCHAR},\n" +
            "          #{txno, jdbcType=VARCHAR},\n" +
            "          #{gencode, jdbcType=VARCHAR},\n" +
            "          #{quantity, jdbcType=VARCHAR},\n" +
            "          null,\n" +
            "          #{cashRegisterNo, jdbcType=VARCHAR},\n" +
            "          #{inputAmount, jdbcType=VARCHAR},\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          #{resultCursor, mode=OUT, jdbcType=CURSOR, resultMap=reservationInfo},\n" +
            "          #{returnCode, mode=OUT, jdbcType=VARCHAR},\n" +
            "          #{response, mode=OUT, jdbcType=VARCHAR}) }")
    @Options(statementType = StatementType.CALLABLE)
	ReservePinCodeResponse executeReservePinCodeForTerminal(ReservationPinCodeByTerminal parameters);
	
	@Results(id = "confirmInfo", value = {
    		// property 需與 ConfirmInfo 內所宣告的名字一樣
			@Result(property = "result", column = "RESULT", javaType = String.class),
			@Result(property = "error", column = "ERROR", javaType = String.class),
    		@Result(property = "amount", column = "AMOUNT", javaType = BigDecimal.class),
            @Result(property = "loyaltyCardTitalPoints", column = "LOYALTYCARDTOTALPOINTS", javaType = String.class),
            @Result(property = "originalLoyaltyCardTotalPoints", column = "ORIGINALLOYALTYCARDTOTALPOINTS", javaType = String.class),
            @Result(property = "newLoyaltyCardPoints", column = "NEWLOYALTYCARDPOINTS", javaType = String.class),
            @Result(property = "pincodeList", column = "PINCODELIST", javaType = String.class)
    })
	@Select("{ CALL WSRG_PINCODECONFIRMATION(\n" +
            "          #{terminalId, jdbcType=VARCHAR},\n" +
            "          #{operatorId, jdbcType=VARCHAR},\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          null,\n" +
            "          #{resultCursor, mode=OUT, jdbcType=CURSOR, resultMap=confirmInfo},\n" +
            "          #{returnCode, mode=OUT, jdbcType=VARCHAR},\n" +
            "          #{response, mode=OUT, jdbcType=VARCHAR}) }")
    @Options(statementType = StatementType.CALLABLE)
	ConfirmPinCodeResponse executeConfirmPinCodeForTerminal(ConfirmPinCodeByTerminal parameters);
	
	@Select("{ CALL WSRG_PINCODERECEIVEDCONFIRM(\n" +
            "          #{terminalId, jdbcType=VARCHAR},\n" +
            "          #{operatorId, jdbcType=VARCHAR},\n" +
            "          null,\n" +
            "          null,\n" +
            "          #{result, mode=OUT, jdbcType=VARCHAR},\n" +
            "          #{error, mode=OUT, jdbcType=VARCHAR},\n" +
            "          #{returnCode, mode=OUT, jdbcType=VARCHAR},\n" +
            "          #{response, mode=OUT, jdbcType=VARCHAR}) }")
    @Options(statementType = StatementType.CALLABLE)
    void executeReceivedConfirmPinCodeForTerminal(ReceivedConfirmPinCodeByTerminal parameters);
	
}
