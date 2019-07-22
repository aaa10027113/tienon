/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.timingtask;

import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccb.govpay.sign.SHA256withRSA;
import com.tienon.EjxError;
import com.tienon.boot.constant.CommonStatic;
import com.tienon.boot.domain.PayOrder;
import com.tienon.boot.domain.pay.ResultOutBo;
import com.tienon.boot.mapper.PayOrderMapper;
import com.tienon.boot.mapper.RefundOrderMapper;
import com.tienon.boot.util.PayUtil;

/**
 * @Description 定时查询订单状态
 * @author ll
 * @date 2019/07/01
 */
@Component
@EnableScheduling
public class OrderQuery {

	private final Logger log = LoggerFactory.getLogger(OrderQuery.class);

	@Resource
	PayOrderMapper payOrderMapper;
	@Resource
	RefundOrderMapper refundOrderMapper;

	/**
	 * TODO 定时查询支付结果
	 * 
	 * @return
	 * @return void 返回类型
	 */
	// @Scheduled(cron = "0 0/1 * * * ? ")
	@Transactional
	public void queryPayOrder() {
		PayOrder queryPayOrder = new PayOrder();
		queryPayOrder.setPayTime(DateUtils.addDays(new Date(), -3));
		queryPayOrder.setStatus(CommonStatic.ORDER_01);
		List<PayOrder> payOrders = payOrderMapper.selectByrefundtimeAndStatus(queryPayOrder);
		for (PayOrder payOrder : payOrders) {
			StringBuffer orgBuf = new StringBuffer();
			orgBuf.append("VNo=")
					.append(CommonStatic.PAY_VERSION)
					.append("&Sgn_Algr=")
					.append(CommonStatic.SIGNATURE_ALGORITHM)
					.append("&Cmdty_Ordr_No=")
					.append(payOrder.getOrderNo())
					.append("&Py_Ordr_No=")
					.append(payOrder.getOrderNo());
			String org = orgBuf.toString();
			// 签名
			String signInf = SHA256withRSA.sign(CommonStatic.PRIVATEKEY, org);
			System.out.println("签名信息" + signInf);
			JSONObject json = new JSONObject();
			json.put("VNo", CommonStatic.PAY_VERSION);
			json.put("Sgn_Algr", CommonStatic.SIGNATURE_ALGORITHM);
			json.put("Cmdty_Ordr_No", payOrder.getOrderNo());
			json.put("Py_Ordr_No", payOrder.getOrderNo());
			json.put("SIGN_INF", signInf);
			log.info("查询交易传参：[{}]", json.toString());
			// 发送
			trustAllHosts();
			String result = PayUtil.doJsonPost(CommonStatic.SELECT_URL, json.toString());
			ResultOutBo outBo = JSON.parseObject(result, ResultOutBo.class);
			log.info("查询交易出参：[{}]", outBo);
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("Cmdty_Ordr_No=")
					.append(outBo.getCmdty_Ordr_No())
					.append("&Py_Ordr_No=")
					.append(outBo.getPy_Ordr_No())
					.append("&Ordr_Gen_Tm=")
					.append(outBo.getOrdr_Gen_Tm())
					.append("&Ordr_Ovtm_Tm=")
					.append(outBo.getOrdr_Ovtm_Tm())
					.append("&Ordr_StCd=")
					.append(outBo.getOrdr_StCd())
					.append("&TAmt=")
					.append(outBo.getTAmt())
					.append("&Rmrk1=")
					.append(outBo.getRmrk1())
					.append("&Rmrk2=")
					.append(outBo.getRmrk2())
					.append("&SIGN_INF=")
					.append(outBo.getSIGN_INF());
			// 验签
			boolean flag = SHA256withRSA.verifySign(CommonStatic.PUBKEY, strBuf.toString(), outBo.getSIGN_INF());
			if (flag == true) {
				log.info("验签成功！");
			} else {
				log.error("验签失败！[{}]", strBuf.toString());
				throw new EjxError(CommonStatic.R_022, "验签失败！");
			}
			if ("2".equals(outBo.getOrdr_StCd())) {
				payOrder.setStatus(CommonStatic.ORDER_00);
			} else if ("3".equals(outBo.getOrdr_StCd())) {
				payOrder.setStatus(CommonStatic.ORDER_02);
			} else if ("6".equals(outBo.getOrdr_StCd())) {
				payOrder.setStatus(CommonStatic.ORDER_03);
			}
			payOrderMapper.updateByPrimaryKey(payOrder);
		}
	}

	/**
	 * TODO 定时查询退款结果
	 * 
	 * @return
	 * @return void 返回类型
	 */
	@Scheduled(cron = "0 0/1 * * * ? ")
	@Transactional
	public void queryRefundOrder() {

	}

	/**
	 * TODO 外呼配置
	 * 
	 * @return
	 * @return void 返回类型
	 */
	public static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			// @Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

			// @Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
				// TODO Auto-generated method stub
			}

			// @Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
				// TODO Auto-generated method stub
			}
		} };
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
