/**
 * Copyright by www.tienon.com
 * All right reserved.
 */
package com.tienon.boot.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tienon.EjxError;
import com.tienon.boot.constant.CommonStatic;
import com.tienon.boot.domain.PayOrder;
import com.tienon.boot.domain.RefundOrder;
import com.tienon.boot.mapper.PayOrderMapper;
import com.tienon.boot.mapper.RefundOrderMapper;
import com.tienon.boot.timingtask.OrderQuery;
import com.tienon.boot.util.PayUtil;
import com.tienon.boot.util.support.PageGrid;
import com.tienon.boot.util.support.PageResult;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageBounds;
import com.tienon.framework.persistence.mybatis.paginator.domain.PageList;
import com.tienon.framework.supports.ActionResult;

/**
 * @author zouhuaqiang
 * @Description TODO
 * @date 2019/07/19日
 */
@Service
public class RefundOrderService {
	private final Logger log = LoggerFactory.getLogger(OrderQuery.class);

	@Resource
	RefundOrderMapper refundOrderMapper;

	@Autowired
	PayOrderMapper payOrderMapper;

	public Object queryList(PageGrid pg) {
		int page = pg.getPage();
		int pageSize = pg.getRows();
		PageBounds pageBounds = new PageBounds(page, pageSize, true);
		try {
			// 查询
			PageList<RefundOrder> pageList = refundOrderMapper.selectBySelective(pg.getSearchCondition(), pageBounds);
			// 获取查询结果总条数
			int total = pageList.getPaginator().getTotalCount();
			return new ActionResult(new PageResult(total, pageList));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询退款订单出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "查询退款订单出现异常：[" + e.getMessage() + "]");
		}
	}

	/**
	 * 
	 * 订单退订受理
	 * 
	 * @param applyNo
	 * @return
	 * @return Object 返回类型
	 */
	public Object refundPayOrderByApplyNo(String applyNo) {
		String refundReasons = "refundReasons";
		PayOrder payOrder = new PayOrder();
		RefundOrder refundOrder = new RefundOrder();
		int i = 0;
		try {
			// 查询受理订单
			payOrder = payOrderMapper.selectByPrimaryKey(applyNo);
			log.info("查询支付订单：" + JSON.toJSONString(payOrder));

			// 判断受理的订单是否存在
			if (null == payOrder) {

				return new ActionResult(false, "订单已经不存在，请刷新后再进行退款操作！");
			}

			// 对支付成功的订单进行退款受理
			if (CommonStatic.ORDER_00.equals(payOrder.getStatus())) {

				refundOrder.setApplyNo(payOrder.getApplyNo());
				refundOrder.setOrderNo(payOrder.getOrderNo());
				refundOrder.setRefundNo(PayUtil.getPaymentOrderNo());
				refundOrder.setRefundReasons(refundReasons);
				refundOrder.setRefundTime(new Date());
				refundOrder.setAmt(payOrder.getAmt());
				refundOrder.setStatus("01");// 退款受理中

				// 插入一条受理记录
				i = refundOrderMapper.insert(refundOrder);

				// 退订业务

			} else {

				return new ActionResult(false, "未支付成功订单不可以做退订受理");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("订单退订受理出现异常：[" + e.getMessage() + "]");
			throw new EjxError(CommonStatic.R_029, "订单退订受理出现异常：[" + e.getMessage() + "]");
		}

		if (i == 0) {

			return new ActionResult(false, "该订单不可退订");
		}

		return new ActionResult(true);
	}
}
