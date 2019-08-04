/**
 * Copyright by www.tienon.com
 * All right reserved.
 */

package com.tienon.boot.modules.job.domain;

/**
 * @Description TODO(定时器domain)
 * 
 * @author lilei
 * @date 2019/08/04
 */
public class JobInfo {
	/**
	 * 日志id
	 */
	private Long logId;

	/**
	 * 任务id
	 */
	private Long jobId;

	/**
	 * spring bean名称
	 */
	private String beanName;

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 参数
	 */
	private String params;

	/**
	 * 任务状态 0：成功 1：失败
	 */
	private String status;

	/**
	 * 失败信息
	 */
	private String error;

	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;

	/**
	 * 创建时间
	 */
	private String creatDate;

	/**
	 * 更新时间
	 */
	private String updateDate;

	/**
	 * 操作员
	 */
	private String operator;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}