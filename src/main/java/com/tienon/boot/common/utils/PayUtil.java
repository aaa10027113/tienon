package com.tienon.boot.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccb.govpay.sign.SHA256withRSA;
import com.tienon.boot.modules.business.domain.ReceiveOutBo;
import com.tienon.boot.modules.business.domain.SendInBo;
import com.tienon.boot.modules.business.service.PaymentOnlieService;

public class PayUtil {

	private static Logger log = Logger.getLogger(PaymentOnlieService.class);

	/**
	 * 生成支付订单
	 * 
	 * @param bo
	 * @param publicKey
	 * @return void 返回类型
	 */
	public static ReceiveOutBo send(SendInBo bo, String publicKey) {
		Random r = new Random();
		String jrn = UUID.randomUUID().toString().replace("-", "");
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		String timeId = dateTimeFormatter.format(dateTime);
		String cmd = dateTimeFormatter.format(dateTime) + String.valueOf(r.nextInt(9999));
		String privateKey = bo.getAntherKek();
		String url = bo.getUrl();

		JSONObject json = new JSONObject();
		json.put("VNo", bo.getVNo());
		json.put("Sgn_Algr", bo.getSgn_Algr());
		json.put("IttParty_Stm_ID", bo.getIttParty_Stm_ID());
		json.put("IttParty_Tms", timeId);
		json.put("IttParty_Jrnl_No", jrn);
		json.put("Py_Chnl_Cd", bo.getPy_Chnl_Cd());
		json.put("Rqs_Py_Tp", bo.getRqs_Py_Tp());
		json.put("OnLn_Ofln_IndCd", bo.getOnLn_Ofln_IndCd());
		json.put("Cmdty_Ordr_No", cmd);
		json.put("Opr_No", bo.getOpr_No());
		json.put("Usr_ID", bo.getUsr_ID());
		json.put("Cst_Nm", bo.getCst_Nm());
		json.put("Crdt_Tp", bo.getCrdt_Tp());
		json.put("Crdt_No", bo.getCrdt_No());
		json.put("MblPh_No", bo.getMblPh_No());
		json.put("Email", bo.getEmail());
		json.put("TAmt", bo.getTAmt());
		json.put("Ccy", bo.getCcy());
		json.put("PgFc_Ret_URL_Adr", bo.getPgFc_Ret_URL_Adr());
		json.put("Rmrk1", bo.getRmrk1());
		json.put("Rmrk2", bo.getRmrk2());
		json.put("FEEGRP", bo.getFEEGRP());
		json.put("TAXGRP", bo.getTAXGRP());

		String jsonString = json.toString();
		Set<String> set = new HashSet<String>();
		set.add("FEEGRP");
		set.add("TAXGRP");
		String signStr = createSign(jsonString, set);
		String signInf = SHA256withRSA.sign(privateKey, signStr);
		json.put("SIGN_INF", signInf);

		log.info("生成支付订单时请求报文" + json.toString());
		String result = doJsonPost(url, json.toString());
		log.info("生成支付订单时返回报文" + result.toString());
		// 收到结果转化成实体
		ReceiveOutBo outBo = JSONObject.parseObject(result, ReceiveOutBo.class);
		return outBo;
	}

	/**
	 * 生成原串
	 */
	public static String createSign(String json, Set<String> set) {

		SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();

		// 转换JSON对象
		JSONObject jsonObject = JSONObject.parseObject(json);

		// 遍历循坏外字段
		for (Entry<String, Object> entry : jsonObject.entrySet()) {

			if (null != entry.getValue() && !"".equals(entry.getValue()) && !set.contains(entry.getKey())) {
				sortedMap.put(entry.getKey(), entry.getValue());
			}
		}

		if (!set.isEmpty()) {
			// 处理集合字段
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String listKey = it.next();

				String listValue = processingSet(jsonObject, listKey);
				sortedMap.put(listKey, listValue);
			}
		}

		// 字段拼接
		String sign = splicingSign("UTF-8", sortedMap, set);
		sign = sign.substring(0, sign.length() - 1);
		return sign;
	}

	/**
	 * 处理集合内字段
	 */
	public static String processingSet(JSONObject jsonObj, String listKey) {
		SortedMap<String, Object> sortedMap = null;
		Set<String> set = new HashSet<String>();
		// 返回拼接的原串
		StringBuffer sbuffer = new StringBuffer();

		// 根据key获取集合json字符串
		String list = jsonObj.getString(listKey);
		// System.out.println("获取List json字符串----------" + list);
		if (list != null && list != "") {
			// list转换对象
			JSONArray jarr = JSONArray.parseArray(list);

			// 创建json集合
			List<JSONObject> jsonValues = new ArrayList<JSONObject>();
			for (int i = 0; i < jarr.size(); i++) {
				jsonValues.add(jarr.getJSONObject(i));
			}

			// 将json集合按照SN排序
			Collections.sort(jsonValues, new Comparator<JSONObject>() {
				@Override
				public int compare(JSONObject a, JSONObject b) {
					String valA = a.getString("SN");
					String valB = b.getString("SN");
					return valA.compareTo(valB);
				}
			});

			// 暂存每一个子元素拼接的值
			String s = "";
			// 遍历排序后的集合
			for (JSONObject j : jsonValues) {
				sortedMap = new TreeMap<String, Object>();
				for (Entry<String, Object> entry : j.entrySet()) {

					if (null != entry.getValue() && !"".equals(entry.getValue())) {

						sortedMap.put(entry.getKey(), entry.getValue());
					}
				}
				s = splicingSign("UTF-8", sortedMap, set);
				sbuffer.append(s);
			}

			return sbuffer.toString();
		}

		return sbuffer.toString();

	}

	/**
	 * 拼接原串
	 */
	@SuppressWarnings("rawtypes")
	public static String splicingSign(String characterEncoding, SortedMap<String, Object> parameters, Set<String> set) {
		StringBuffer sbuffer = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (set.contains(k)) {
				sbuffer.append(v);
			} else {
				if (!k.equals("SIGN_INF")) {
					sbuffer.append(k + "=" + v + "&");
				}
			}
		}
		return sbuffer.toString();
	}

	/**
	 * post请求（用于请求json格式的参数）
	 * 
	 * @param urlPath
	 * @param Json
	 * @return
	 */
	public static String doJsonPost(String urlPath, String Json) {

		String result = "";
		BufferedReader reader = null;
		try {
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			// 设置文件类型:
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			// 设置接收类型否则返回415错误
			// conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
			conn.setRequestProperty("accept", "application/json");
			// 往服务器里面发送数据
			if (Json != null) {
				byte[] writebytes = Json.getBytes();
				// 设置文件长度
				conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
				OutputStream outwritestream = conn.getOutputStream();
				outwritestream.write(Json.getBytes());
				outwritestream.flush();
				outwritestream.close();
			}

			log.info("http返回码[" + conn.getResponseCode() + "]");

			if (conn.getResponseCode() == 200) {

				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				result = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 获取支付订单号
	 *
	 * @return
	 */
	public static String getPaymentOrderNo() {
		Random r = new Random();
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String orderNo = dateTimeFormatter.format(dateTime) + String.valueOf(r.nextInt(9999));
		return orderNo;
	}

}
