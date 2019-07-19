package com.tienon.boot.service.pay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.constant.CommonStatic;
import com.tienon.boot.domain.ApplyInfo;
import com.tienon.boot.domain.PayOrder;
import com.tienon.boot.domain.pay.ReceiveOutBo;
import com.tienon.boot.domain.pay.SendInBo;
import com.tienon.boot.domain.pay.SendSubInBo1;
import com.tienon.boot.domain.pay.SendSubInBo2;
import com.tienon.boot.mapper.OperateMapper;
import com.tienon.boot.mapper.PayOrderMapper;
import com.tienon.boot.mapper.pay.PaymentOnlineMapper;
import com.tienon.boot.util.PayUtil;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;

/**
 * 
 * @Description (在线支付)
 * @author WangQingquan
 * @date 2019年7月19日
 */
@Service
@Transactional
public class PaymentOnlieService {

	private static Logger logger = Logger.getLogger(PaymentOnlieService.class);

	@Autowired
	PaymentOnlineMapper paymentOnlineMapper;

	@Autowired
	OperateMapper operateMapper;

	@Autowired
	PayOrderMapper payOrderMapper;

	/**
	 * 发送在线支付信息
	 * 
	 * @param bo
	 * @return
	 */
	public ActionResult sendPaymessage(String applyNo) {
		ReceiveOutBo outBo = new ReceiveOutBo();
		SendInBo bo = new SendInBo();
		ActionResult actionResult = new ActionResult();
		try {
			ApplyInfo info = operateMapper.printInfo(applyNo);
			if (null == info) {
				actionResult.setMsg("根据申请序号未获取到数据");
				actionResult.setSuccess(false);
				return actionResult;
			}
			bo.setUrl(CommonStatic.PAY_SEND_URL);// 发送地址
			bo.setAntherKek(CommonStatic.ANTHER_KEY);
			bo.setVNo(CommonStatic.PAY_VERSION);// 版本号
			bo.setSgn_Algr(CommonStatic.SIGNATURE_ALGORITHM);// 签名算法
			bo.setIttParty_Stm_ID(CommonStatic.ITTPARTY_STM_ID);// 发起渠道编号
			bo.setPy_Chnl_Cd(CommonStatic.PAY_CHANNEL_CODE);// 支付渠道代码
			bo.setRqs_Py_Tp(CommonStatic.REQUEST_PAY_TYPE);// 请求支付类型
			bo.setOnLn_Ofln_IndCd(CommonStatic.ONLINE_OFFLINE_CODE);// 线上线下标志代码
			bo.setCrdt_Tp(CommonStatic.CERTIFICATE_TYPE);// 证件类型
			bo.setCcy(CommonStatic.MONEY_TYPE);// 币种

			bo.setOpr_No(info.getOperator());// 操作员号
			bo.setUsr_ID("13100112233");// 用户ID
			bo.setCst_Nm(info.getHumanName());// 客户名称
			bo.setCrdt_No(info.getIdCardNo());// 证件号码
			bo.setMblPh_No("");// 手机号码
			bo.setEmail("");// 电子邮箱
			bo.setPgFc_Ret_URL_Adr("http://www.baidu.com");// 页面返回URL地址
			bo.setTAmt(new BigDecimal(info.getAmt()));// 总金额
			bo.setRmrk1("");
			bo.setRmrk2("");

			List<SendSubInBo1> list1 = new ArrayList<SendSubInBo1>();
			SendSubInBo1 subInvo1 = new SendSubInBo1();
			subInvo1.setSN("1");// 序号
			subInvo1.setFee_Itm_Cd("10301480203");// 费项代码
			subInvo1.setFee_Itm_Prj_Amt(new BigDecimal(info.getAmt()));// 此费项缴费金额
			subInvo1.setRmrk1("");
			subInvo1.setRmrk2("");
			list1.add(subInvo1);
			bo.setFEEGRP(list1);

			List<SendSubInBo2> list2 = new ArrayList<SendSubInBo2>();
			bo.setTAXGRP(list2);

			outBo = PayUtil.send(bo, CommonStatic.PUBLIC_KEY);
			outBo.setPy_URL("http://www.baidu.com");
			actionResult.setMsg("跳转支付连接中请稍后...");
			actionResult.setSuccess(true);
			actionResult.setObj(outBo);
		} catch (Exception e) {
			logger.error("PaymentOnlieService Error, method: sendPaymessage" + e.getMessage());
		}
		return actionResult;

	}

	/**
	 * 
	 * TODO(根据查询条件查询支付状态列表)
	 * 
	 * @param payOrder
	 * @return
	 * @return ActionResult 返回类型
	 */
	public Object queryPayOrderList(PageGrid pg) {
		int page = pg.getPage();
		int pageSize = pg.getRows();
		PageBounds pageBounds = new PageBounds(page, pageSize, true);

		try {
			logger.info("获取订单支付状态列表入参：searchCondition=" + JSON.toJSONString(pg.getSearchCondition())
					+ "********pageBounds=" + JSON.toJSONString(pageBounds));
			// 查询
			PageList<PayOrder> pageList = payOrderMapper.queryPayOrderList(pg.getSearchCondition(), pageBounds);
			logger.info("获订单支付状态列列表出参：" + JSON.toJSONString(pageList));
			// 获取查询结果总条数
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询批量装载表出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询批量装载表出现异常：[" + e.getMessage() + "]");
		}

	}
}
