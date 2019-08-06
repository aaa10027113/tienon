package com.tienon.boot.modules.system.domain;

import java.util.Date;

/**
 * @author ll
 * 
 * @Description TODO 定时任务
 * @date 2019/8/1
 */
public class JobInfo {
    /**
     * id
     */
    private  int id;

    /**
     * 名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数
     */
    private String parameter;

    /**
     * cron表达式
     */
    private String  cron;

    /**
     * 备注
     */
    private String description;

    /**
     * 状态 0暂停   1正常
     */
    private String status;

    /**
     * 创建时间
     */
    private String creatDate;

    /**
     * 修改时间
     */

    private String updateDate;

    /**
     * 创建|修改人
     */
    private String operator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
