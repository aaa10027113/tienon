package com.tienon.boot.modules.business.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送对账单文件
 * 
 * @author xieyongqiang
 * @date 2019/08/07
 */
public class TestSendDzd {

	/**
	 * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
	 * 
	 * @param actionUrl
	 *            访问的服务器URL
	 * @param params
	 *            普通参数
	 * @param files
	 *            文件参数
	 * @return
	 * @throws IOException
	 */
	public static String post(String actionUrl, Map<String, String> params, Map<String, File> files) throws Exception {

		StringBuilder sb2 = new StringBuilder();
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		URL uri = new URL(actionUrl);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(5 * 10000000); // 缓存的最长时间
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false); // 不允许使用缓存
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

		// 首先组拼文本类型的参数
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
			sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
			sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
			sb.append(LINEND);
			sb.append(entry.getValue());
			sb.append(LINEND);
		}

		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.write(sb.toString().getBytes());
		InputStream in = null;
		// 发送文件数据
		if (files != null) {
			for (Map.Entry<String, File> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				// name是post中传参的键 filename是文件的名称
				sb1.append(
						"Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getKey() + "\"" + LINEND);
				sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());

				InputStream is = new FileInputStream(file.getValue());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
				is.close();
				outStream.write(LINEND.getBytes());
			}

		}
		// 请求结束标志
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();
		// 得到响应码
		int res = conn.getResponseCode();
		if (res == 200) {
			// 读取返回数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //$NON-NLS-1$
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb2.append(line).append("\n"); //$NON-NLS-1$
			}
			reader.close();
		}
		outStream.close();
		conn.disconnect();
		return sb2.toString();
	}

	/**
	 * 以数据流的形式传参
	 * 
	 * @param actionUrl
	 * @param params
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public static String postFile(String actionUrl, Map<String, String> params, Map<String, byte[]> files)
			throws Exception {
		StringBuilder sb2 = new StringBuilder();
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		URL uri = new URL(actionUrl);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(6 * 1000); // 缓存的最长时间
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false); // 不允许使用缓存
		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

		// 首先组拼文本类型的参数
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
			sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
			sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
			sb.append(LINEND);
			sb.append(entry.getValue());
			sb.append(LINEND);
		}

		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
		outStream.write(sb.toString().getBytes());
		InputStream in = null;
		// 发送文件数据
		if (files != null) {
			for (Map.Entry<String, byte[]> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);

				sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\"" //$NON-NLS-1$
						+ file.getKey() + "\"" + LINEND);
				sb1.append("Content-Type:" + "application/octet-stream;UTF-8" + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());
				outStream.write(file.getValue());
				outStream.write(LINEND.getBytes());
			}

		}
		// 请求结束标志
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		outStream.flush();
		// 得到响应码
		int res = conn.getResponseCode();
		if (res == 200) {
			// 读取返回数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //$NON-NLS-1$
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb2.append(line).append("\n"); //$NON-NLS-1$
			}
			reader.close();
		}
		outStream.close();
		conn.disconnect();
		return sb2.toString();
	}

	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}

	public static void main(String[] args) {
		String Default_Address = "http://127.0.0.1/business/dzd/upload";
		try {
			Map<String, String> requestParamsMap = new HashMap<String, String>();
			requestParamsMap.put("jsonStr", "huadoumi");
			requestParamsMap.put("desp", "<h1>123&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;321</h1>");
			requestParamsMap.put("productId", "AFAIDE");

			Map<String, File> files = new HashMap<String, File>();
			File file = new File("D:/dev/git/sbzcmanager" + File.separator + "CLRGDETAI.txt");
			files.put("CLRGDETAI.txt", file);
			String b = post(Default_Address, requestParamsMap, files);
			System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}