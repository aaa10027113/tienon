/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.job.service;

import java.security.cert.X509Certificate;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccb.govpay.sign.SHA256withRSA;
import com.tienon.boot.common.constant.CommonStatic;
import com.tienon.boot.common.utils.PayUtil;
import com.tienon.boot.modules.business.domain.PayOrder;
import com.tienon.boot.modules.business.domain.ResultOut;
import com.tienon.boot.modules.business.mapper.PayOrderMapper;

/**
 * @Description 定时查询订单状态
 * 
 * @author lilei
 * @date 2019/07/01
 */
@Service
@EnableScheduling
@Transactional(rollbackFor = Exception.class)
public class OrderQuery {
	private final Logger log = LoggerFactory.getLogger(OrderQuery.class);

	@Resource
	PayOrderMapper payOrderMapper;

	/**
	 * TODO(查询支付订单)
	 * 
	 * @return void 返回类型
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	public synchronized void queryPayOrder() {
		List<PayOrder> payOrders = payOrderMapper.selectBystatus();
		for (PayOrder payOrder : payOrders) {
			String org = "VNo=" + "2" + "&" + "Sgn_Algr=" + "SHA256withRSA" + "&" + "Cmdty_Ordr_No="
					+ payOrder.getOrderNo() + "&" + "Py_Ordr_No=" + payOrder.getPayOrderNo();
			// 签名
			String signInf = SHA256withRSA.sign(CommonStatic.PRIVATEKEY, org);
			JSONObject json = new JSONObject();
			json.put("VNo", CommonStatic.PAY_VERSION);
			json.put("Sgn_Algr", CommonStatic.SIGNATURE_ALGORITHM);
			json.put("Cmdty_Ordr_No", payOrder.getOrderNo());
			json.put("Py_Ordr_No", payOrder.getPayOrderNo());
			json.put("SIGN_INF", signInf);
			log.info("查询缴费结果接口请求[{}]", json.toString());

			// 发送
			trustAllHosts();
			String result = PayUtil.doJsonPost(CommonStatic.SELECT_URL, json.toString());
			ResultOut outBo = JSON.parseObject(result, ResultOut.class);
			log.info("查询缴费结果接口返回[{}]", outBo);
			if(outBo != null){
				payOrder.setStatus(outBo.getOrdr_StCd());
				payOrderMapper.updateByPrimaryKey(payOrder);
			}
		}
	}

	/**
	 * 外呼配置
	 * 
	 * @return void 返回类型
	 */
	public static void trustAllHosts() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) {
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
