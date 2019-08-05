/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.modules.business.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.common.constant.CommonStatic;
import com.tienon.boot.common.utils.AscEUtils;
import com.tienon.boot.common.utils.PayUtil;
import com.tienon.boot.modules.business.domain.ApplyInfo;
import com.tienon.boot.modules.business.domain.PayOrder;
import com.tienon.boot.modules.business.domain.ReceiveOut;
import com.tienon.boot.modules.business.domain.SendInBo;
import com.tienon.boot.modules.business.domain.SendSubInBo1;
import com.tienon.boot.modules.business.domain.SendSubInBo2;
import com.tienon.boot.modules.business.mapper.ApplyMapper;
import com.tienon.boot.modules.business.mapper.PayOrderMapper;
import com.tienon.boot.modules.business.mapper.PaymentOnlineMapper;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;

/**
 * @Description TODO(支付订单)
 * 
 * @author wangqingquan
 * @date 2019/08/05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PayOrderService {
	private static Logger log = Logger.getLogger(PayOrderService.class);

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
		ReceiveOut outBo = new ReceiveOut();
		SendInBo bo = new SendInBo();
		PayOrder payOrder = new PayOrder();
		ActionResult actionResult = new ActionResult();
		try {
			applyNo = AscEUtils.decrypt(applyNo);
			ApplyInfo info = applyMapper.selectApplyByNo(applyNo);
			if (null == info) {
				actionResult.setMsg("不能支付，原因：根据申请编号找不到支付订单！");
				actionResult.setSuccess(false);
				return actionResult;
			}
			// 发送地址
			bo.setUrl(CommonStatic.PAY_SEND_URL);
			// 支付秘钥
			bo.setAntherKek(CommonStatic.ANTHER_KEY);
			// 版本号
			bo.setVNo(CommonStatic.PAY_VERSION);
			// 签名算法
			bo.setSgn_Algr(CommonStatic.SIGNATURE_ALGORITHM);
			// 发起渠道编号
			bo.setIttParty_Stm_ID(CommonStatic.ITTPARTY_STM_ID);
			// 支付渠道代码
			bo.setPy_Chnl_Cd(CommonStatic.PAY_CHANNEL_CODE);
			// 请求支付类型
			bo.setRqs_Py_Tp(CommonStatic.REQUEST_PAY_TYPE);
			// 线上线下标志代码
			bo.setOnLn_Ofln_IndCd(CommonStatic.ONLINE_OFFLINE_CODE);
			// 证件类型
			bo.setCrdt_Tp(CommonStatic.CERTIFICATE_TYPE);
			// 币种
			bo.setCcy(CommonStatic.MONEY_TYPE);
			// 操作员号
			bo.setOpr_No(info.getOperator());
			// 用户ID
			bo.setUsr_ID(CommonStatic.FEE_ITM_CD);
			// 客户名称
			bo.setCst_Nm(info.getHumanName());
			// 证件号码
			bo.setCrdt_No(info.getIdCardNo());
			// 手机号码
			bo.setMblPh_No("");
			// 电子邮箱
			bo.setEmail("");
			// 页面返回URL地址
			bo.setPgFc_Ret_URL_Adr("http://www.baidu.com");
			// 总金额
			bo.setTAmt(new BigDecimal(0.01));
			bo.setRmrk1("");
			bo.setRmrk2("");

			List<SendSubInBo1> list1 = new ArrayList<SendSubInBo1>();
			SendSubInBo1 subInvo1 = new SendSubInBo1();
			// 序号
			subInvo1.setSN(CommonStatic.SN);
			// 费项代码
			subInvo1.setFee_Itm_Cd(CommonStatic.FEE_ITM_CD);
			// 此费项缴费金额
			subInvo1.setFee_Itm_Prj_Amt(new BigDecimal(0.01));
			subInvo1.setRmrk1("");
			subInvo1.setRmrk2("");
			list1.add(subInvo1);
			bo.setFEEGRP(list1);

			List<SendSubInBo2> list2 = new ArrayList<SendSubInBo2>();
			bo.setTAXGRP(list2);
			outBo = PayUtil.send(bo, CommonStatic.PUBLIC_KEY);
			try {
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
			payOrder.setApplyNo(AscEUtils.encrypt(apply));
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
			applyNo = AscEUtils.decrypt(applyNo);
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
