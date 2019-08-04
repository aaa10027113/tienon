package com.tienon.boot.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.business.constant.CommonStatic;
import com.tienon.boot.business.domain.ApplyInfo;
import com.tienon.boot.business.domain.PayOrder;
import com.tienon.boot.business.domain.ReceiveOutBo;
import com.tienon.boot.business.domain.SendInBo;
import com.tienon.boot.business.domain.SendSubInBo1;
import com.tienon.boot.business.domain.SendSubInBo2;
import com.tienon.boot.business.mapper.ApplyMapper;
import com.tienon.boot.business.mapper.PayOrderMapper;
import com.tienon.boot.business.mapper.PaymentOnlineMapper;
import com.tienon.boot.util.ASCEUtils;
import com.tienon.boot.util.PayUtil;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;

@Service
@Transactional
public class PaymentOnlieService {
	private static Logger log = Logger.getLogger(PaymentOnlieService.class);

	@Autowired
	PaymentOnlineMapper paymentOnlineMapper;
	@Autowired
	ApplyMapper applyMapper;
	@Autowired
	PayOrderMapper payOrderMapper;

	/**
	 * 发送在线支付信息
	 *
	 * @param applyNo
	 * @return
	 */
	public ActionResult sendPaymessage(String applyNo) {
		ReceiveOutBo outBo = new ReceiveOutBo();
		SendInBo bo = new SendInBo();
		PayOrder payOrder = new PayOrder();
		ActionResult actionResult = new ActionResult();
		try {
			log.info("在线支付解密数据入参applyNo=" + applyNo);
			applyNo = ASCEUtils.decrypt(applyNo);
			log.info("在线支付解密数据出参applyNo=" + applyNo);
			ApplyInfo info = applyMapper.printInfo(applyNo);
			log.info("获取支付对象信息出参：" + JSON.toJSONString(info));
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
			bo.setTAmt(new BigDecimal(0.01));// 总金额
			bo.setRmrk1("");
			bo.setRmrk2("");

			List<SendSubInBo1> list1 = new ArrayList<SendSubInBo1>();
			SendSubInBo1 subInvo1 = new SendSubInBo1();
			subInvo1.setSN("1");// 序号
			subInvo1.setFee_Itm_Cd("320100100001");// 费项代码
			subInvo1.setFee_Itm_Prj_Amt(new BigDecimal(0.01));// 此费项缴费金额
			subInvo1.setRmrk1("");
			subInvo1.setRmrk2("");
			list1.add(subInvo1);
			bo.setFEEGRP(list1);

			List<SendSubInBo2> list2 = new ArrayList<SendSubInBo2>();
			bo.setTAXGRP(list2);
			outBo = PayUtil.send(bo, CommonStatic.PUBLIC_KEY);
			try {
				// 保存支付订单号
				payOrder.setPayOrderNo(outBo.getPy_Ordr_No());
				payOrder.setApplyNo(applyNo);
				payOrderMapper.updateByPrimaryKeySelective(payOrder);
			} catch (Exception e) {
				actionResult.setMsg("保存支付订单号出错");
				actionResult.setSuccess(false);
				log.error("保存支付订单号出现异常：[" + e.getMessage() + "]");
			}

			actionResult.setMsg("跳转支付连接中请稍后...");
			actionResult.setSuccess(true);
			actionResult.setObj(outBo);

		} catch (Exception e) {
			actionResult.setMsg("发送支付连接出错");
			actionResult.setSuccess(false);
			log.error("发送支付连接出现异常：[" + e.getMessage() + "]");
		}
		return actionResult;

	}

	/**
	 * TODO(根据查询条件查询支付状态列表)
	 *
	 * @param pg
	 * @return ActionResult 返回类型
	 */
	public Object queryPayOrderList(PageGrid pg) {
		int page = pg.getPage();
		int pageSize = pg.getRows();
		PageBounds pageBounds = new PageBounds(page, pageSize, true);

		try {
			log.info("获取订单支付状态列表入参：searchCondition=" + JSON.toJSONString(pg.getSearchCondition())
					+ "********pageBounds=" + JSON.toJSONString(pageBounds));
			// 查询
			PageList<PayOrder> pageList = payOrderMapper.queryPayOrderList(pg.getSearchCondition(), pageBounds);
			log.info("获订单支付状态列列表出参：" + JSON.toJSONString(pageList));
			// 获取查询结果总条数
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询批量装载表出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询批量装载表出现异常：[" + e.getMessage() + "]");
		}

	}

	/**
	 * TODO(根据受理序号查询)
	 *
	 * @param applyNo
	 * @return ActionResult 返回类型
	 */
	public Object queryByApplyNo(String applyNo) {
		PayOrder payOrder = null;
		ActionResult actionResult = null;
		try {
			payOrder = payOrderMapper.selectByPrimaryKey(applyNo);
			String apply = payOrder.getApplyNo();
			log.info("加密后的applyNO=" + applyNo);
			payOrder.setApplyNo(ASCEUtils.encrypt(apply));
			actionResult = new ActionResult(true, "查询成功", payOrder);
		} catch (Exception e) {
			log.error("查询支付订单出现异常：[" + e.getMessage() + "]");
			actionResult = new ActionResult(false, "查询失败,原因：", e.getMessage());
		}
		return actionResult;
	}

	/**
	 * TODO(根据受理序号解密查询)
	 *
	 * @param applyNo
	 * @return ActionResult 返回类型
	 */
	public Object queryByApplyNoInfo(String applyNo) {
		PayOrder payOrder = null;
		ActionResult actionResult = null;
		try {
			applyNo = ASCEUtils.decrypt(applyNo);
			log.info("解密后的applyNO=" + applyNo);
			payOrder = payOrderMapper.selectByPrimaryKey(applyNo);
			actionResult = new ActionResult(true, "查询成功", payOrder);
		} catch (Exception e) {
			log.error("查询支付订单出现异常：[" + e.getMessage() + "]");
			actionResult = new ActionResult(false, "查询失败,原因：", e.getMessage());
		}
		return actionResult;
	}
}