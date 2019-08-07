package com.tienon.boot.modules.business.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccb.govpay.sign.SHA256withRSA;

/**
 * 接收对账单
 * 
 * @author xieyongqiang
 * @date 2019/08/07
 */
@RestController
@RequestMapping("/business/dzd")
public class ReceiveDzdController {
	private static Logger log = Logger.getLogger(ReceiveDzdController.class);
	// 公钥
	public static String pubkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkxJVDKgDhDGS/OCBDmqD1F5j8yzeimmEmaFjz3w+oO6vUDpJ+bGesONsK0Ue1ttOyFBm0x1IRRmJJWTTL9NSNm+f8P1nttZiRSdWIgOjXwxZvasndDN9cUArilIGPNhpLxkHaZkzvym/GKPPbr/p5ys/iFFjZsxGTJ5k84KnK83yC503TGqLweOp24/ghJZO80lPH3ZNTQUqmV4JodTdRvirJbPAZBoc2lUkizYX4NitAqCDnXXFN8JT9C2tJONy6s8JRsuXC7Y6+kffXTxUne2UAvuwkyBJtMsiH3a38yj9V9PLbggOST9gpUG7ISwe2PGWamYsx0/tJJR8avUnwwIDAQAB";

	/**
	 * 接收对账单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return void 返回类型
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void uploadReceive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("接收政融支付平台推送对账单文件......");

		try {
			long start = System.currentTimeMillis();
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			String jsonStr = request.getParameter("jsonStr");
			String File_Nm = JSON.parseObject(jsonStr).getString("File_Nm");
			String VNo = JSON.parseObject(jsonStr).getString("VNo");
			String Sgn_Algr = JSON.parseObject(jsonStr).getString("Sgn_Algr");
			String Rcncl_Dt = JSON.parseObject(jsonStr).getString("Rcncl_Dt");
			String MD5 = JSON.parseObject(jsonStr).getString("MD5");
			String File_Type = JSON.parseObject(jsonStr).getString("File_Type");
			String SIGN_INF = JSON.parseObject(jsonStr).getString("SIGN_INF");
			String srcMsg = "File_Nm=" + File_Nm + "&File_Type=" + File_Type + "&MD5=" + MD5 + "&Rcncl_Dt=" + Rcncl_Dt
					+ "&Sgn_Algr=" + Sgn_Algr + "&VNo=" + VNo;
			boolean flag = SHA256withRSA.verifySign(pubkey, srcMsg, SIGN_INF);

			if (!flag) {
				log.info("验签失败！");
				this.writeJson(response, "01");
				return;
			} else {
				log.info("验签失败！");
			}

			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						File localFile = new File("D:/program/test/" + file.getOriginalFilename());
						file.transferTo(localFile);
					}
				}
				long end = System.currentTimeMillis();
				log.info("下载文件时间[" + (end - start) + "]");
			}
			log.info("接收成功！");
			this.writeJson(response, "00");
		} catch (Throwable e) {
			log.info("接收失败！");
			this.writeJson(response, "01");
		}
	}
   
	/**
	 * 返回报文
	 * 
	 * @param response
	 * @param result 
	 * @return void 返回类型
	 */
	public void writeJson(HttpServletResponse response, String result) {
		PrintWriter out = null;
		try {
			String Rcv_Tm = this.today(2);
			String Rcv_StCd = result;
			JSONObject json = new JSONObject();
			json.put("Rcv_Tm", Rcv_Tm);
			json.put("Rcv_StCd", Rcv_StCd);
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.write(json.toJSONString());
			out.flush();
		} catch (IOException e) {
			log.info("IO异常！");
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public String today(int flag) {
		GregorianCalendar now = new GregorianCalendar();
		return DatetoString(now, flag);
	}

	public String DatetoString(GregorianCalendar ts, int flag) {

		SimpleDateFormat sdf = null;
		if (flag == 1)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (flag == 2)
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (flag == 3)
			sdf = new SimpleDateFormat("yyyyMMddHHmm");
		if (flag == 4)
			sdf = new SimpleDateFormat("yyyyMMddHH");
		if (flag == 5)
			sdf = new SimpleDateFormat("yyyyMMdd");
		if (flag == 6)
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (flag == 7)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (flag == 8)
			sdf = new SimpleDateFormat("yyMMddHHmm");
		if (flag == 9)
			sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		if (flag == 10)
			sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(ts.getTime());
	}
}
