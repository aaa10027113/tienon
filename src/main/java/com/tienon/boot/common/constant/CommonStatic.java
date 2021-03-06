/**
\
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.common.constant;

/**
 * @Description TODO(公共常量)
 * 
 * @author lilei
 * @date 2019/08/04
 */
public class CommonStatic {
	/**
	 * 返回码
	 */
	/**000:交易成功*/
	public final static String R_000 = "000";
	/**022:系统内部处理错误*/
	public final static String R_022 = "022"; 
	/**029:数据库错误*/
	public final static String R_029 = "029"; 
	/**080:交易超时*/
	public final static String R_080 = "080";

	/**
	 * 秘钥
	 */
	/**秘钥*/
	public final static String KEY = "8k5a2o5";

	/**
	 * 支付状态
	 */
	/**1-待缴费*/
	public static String STATUS_1 = "1";
	/**2-成功*/
	public static String STATUS_2 = "2";
	/**3-失败*/
	public static String STATUS_3 = "3";
	/**4-全部退费*/
	public static String STATUS_4 = "4";
	/**5-部分退费*/
	public static String STATUS_5 = "5";
	/**6-失效*/
	public static String STATUS_6 = "6";
	/**9-取消*/
	public static String STATUS_9 = "9";
	/**a-处理中*/
	public static String STATUS_a = "a";
	/**b-待冲正*/
	public static String STATUS_b = "b";
	/**c-待系统退款*/
	public static String STATUS_c = "c";
	/**d-一落地*/
	public static String STATUS_d = "d";
	/**88-一无需支付*/
	public static String STATUS_88 = "88";

	/**
	 * 退款成功
	 */
	/**00:退款成功*/
	public static String refund_00 = "00";
	/**01:退款受理中*/
	public static String refund_01 = "01";
	/**02:退款失败*/
	public static String refund_02 = "02";
	/**退款超时*/
	public static String refund_03 = "03";
	/**99：未知状态*/
	public static String refund_99 = "99";
    
	/**
	 * 支付相关
	 */
	/**支付发送的Url*/
	public final static String PAY_SEND_URL = "http://govpaytestjsnjjbspj.mytunnel.site/online/multipenpayment";
	/**支付公私钥对*/
	public final static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg3I9pTRnaRMBWNfegP6XE/bPf/l4HyOq2G+RngaDmrbZxC+yQHt2W6G9F7WQzp7mD/t8mqK6lZg78BQLAArfWnPI116YIbyxyFvK3b9uY245WoznMEMvx+NvrQSoGVe6Dt6/M4+fD74144+8MBRB4mnETCN2OmAmIutKJikHgpPW2Oy+RSuzMpuBqzU+Prb+QP88gUuzbmgjvK3rqeQVbYNjI1bos6DAzjza+7E9aKhY9tSqmyKy/k80Hv4YnXTq0VWPJZQLV5qtJbzHI3534ArW7Pq4WIoyF434tVRIfnKvRpeEWP91YeShOfI4FZO4IBbsrw5q+e7A7W+tlfEYGQIDAQAB";
	/**支付秘钥*/
	public final static String ANTHER_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";
	
	/**支付结果查询url*/
	public static final String SELECT_URL = "http://govpaytestjsnjjbspj.mytunnel.site/online/enquireorder";
	/**支付结果查询公私钥对*/
	public static final String PUBKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkxJVDKgDhDGS/OCBDmqD1F5j8yzeimmEmaFjz3w+oO6vUDpJ+bGesONsK0Ue1ttOyFBm0x1IRRmJJWTTL9NSNm+f8P1nttZiRSdWIgOjXwxZvasndDN9cUArilIGPNhpLxkHaZkzvym/GKPPbr/p5ys/iFFjZsxGTJ5k84KnK83yC503TGqLweOp24/ghJZO80lPH3ZNTQUqmV4JodTdRvirJbPAZBoc2lUkizYX4NitAqCDnXXFN8JT9C2tJONy6s8JRsuXC7Y6+kffXTxUne2UAvuwkyBJtMsiH3a38yj9V9PLbggOST9gpUG7ISwe2PGWamYsx0/tJJR8avUnwwIDAQAB";
	/**支付结果查询公私钥对*/
	public static final String PRIVATEKEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";

	/**支付版本号*/
	public final static String PAY_VERSION = "2";
	/**签名算法*/
	public final static String SIGNATURE_ALGORITHM = "SHA256withRSA";
	/**发起渠道编号*/
	public final static String ITTPARTY_STM_ID = "GP105";
	/**支付渠道代码*/
	public final static String PAY_CHANNEL_CODE = "1";
	/**请求支付类型*/
	public final static String REQUEST_PAY_TYPE = "";
	/**线上线下标志代码*/
	public final static String ONLINE_OFFLINE_CODE = "1";
	/**证件类型*/
	public final static String CERTIFICATE_TYPE = "1010";
	/**币种：人民币*/
	public final static String MONEY_TYPE = "156";
	 /**SIGN_INF*/
	public final static String SIGN_INF = "SIGN_INF";
	 /**费项代码*/
	public final static String FEE_ITM_CD = "320100100001";
	 /**序号*/
	public final static String SN = "1";
}
