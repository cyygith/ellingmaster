package com.elling.rent.model;

import javax.persistence.*;

@Table(name = "rent_contract")
public class RentContract {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 合同编号
     */
    @Column(name = "CONTRACT_CODE")
    private String contractCode;

    /**
     * 合同名称
     */
    @Column(name = "CONTRACT_NAME")
    private String contractName;

    /**
     * 房屋编号
     */
    @Column(name = "HOUSE_CODE")
    private String houseCode;

    /**
     * 租客编号
     */
    @Column(name = "PERSON_CODE")
    private String personCode;

    /**
     * 签约时间
     */
    @Column(name = "SIGN_TIME")
    private String signTime;

    /**
     * 开始时间
     */
    @Column(name = "START_TIME")
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "END_TIME")
    private String endTime;

    /**
     * 租金
     */
    @Column(name = "RENTER")
    private String renter;

    /**
     * 押金
     */
    @Column(name = "DEPOSIT")
    private String deposit;

    /**
     * 水费结算方式（1-按人10元/月  2-按月结算）
     */
    @Column(name = "WATER_CLOSE_TYPE")
    private String waterCloseType;

    /**
     * 付款方式
     */
    @Column(name = "PAY_TYPE")
    private String payType;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 排序号
     */
    @Column(name = "ORDER_NUM")
    private Long orderNum;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;
    /**
     * 创建人
     */
    @Column(name = "CREATE_USER")
    private String createUser;

    /**
     * 创建部门
     */
    @Column(name = "CREATE_DEPT")
    private String createDept;
    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private String updateTime;
    
    /**
     * 实体ID
     */
    @Column(name = "TENANT_ID")
    private String tenantId;
    
    /**
     * 冗余数据  房间名
     */
    @Transient
    private String houseName;
    /**
     * 冗余数据  用户名
     */
    @Transient
    private String personName;
    
    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取合同编号
     *
     * @return CONTRACT_CODE - 合同编号
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * 设置合同编号
     *
     * @param contractCode 合同编号
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    /**
     * 获取合同名称
     *
     * @return CONTRACT_NAME - 合同名称
     */
    public String getContractName() {
        return contractName;
    }

    /**
     * 设置合同名称
     *
     * @param contractName 合同名称
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * 获取房屋编号
     *
     * @return HOUSE_CODE - 房屋编号
     */
    public String getHouseCode() {
        return houseCode;
    }

    /**
     * 设置房屋编号
     *
     * @param houseCode 房屋编号
     */
    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    /**
     * 获取租客编号
     *
     * @return PERSON_CODE - 租客编号
     */
    public String getPersonCode() {
        return personCode;
    }

    /**
     * 设置租客编号
     *
     * @param personCode 租客编号
     */
    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    /**
     * 获取签约时间
     *
     * @return SIGN_TIME - 签约时间
     */
    public String getSignTime() {
        return signTime;
    }

    /**
     * 设置签约时间
     *
     * @param signTime 签约时间
     */
    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取开始时间
     *
     * @return START_TIME - 开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return END_TIME - 结束时间
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取租金
     *
     * @return RENTER - 租金
     */
    public String getRenter() {
        return renter;
    }

    /**
     * 设置租金
     *
     * @param renter 租金
     */
    public void setRenter(String renter) {
        this.renter = renter;
    }

    /**
     * 获取押金
     *
     * @return DEPOSIT - 押金
     */
    public String getDeposit() {
        return deposit;
    }

    /**
     * 设置押金
     *
     * @param deposit 押金
     */
    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    /**
     * 获取水费结算方式（1-按人10元/月  2-按月结算）
     *
     * @return WATER_CLOSE_TYPE - 水费结算方式（1-按人10元/月  2-按月结算）
     */
    public String getWaterCloseType() {
        return waterCloseType;
    }

    /**
     * 设置水费结算方式（1-按人10元/月  2-按月结算）
     *
     * @param waterCloseType 水费结算方式（1-按人10元/月  2-按月结算）
     */
    public void setWaterCloseType(String waterCloseType) {
        this.waterCloseType = waterCloseType;
    }

    /**
     * 获取付款方式
     *
     * @return PAY_TYPE - 付款方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置付款方式
     *
     * @param payType 付款方式
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取排序号
     *
     * @return ORDER_NUM - 排序号
     */
    public Long getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序号
     *
     * @param orderNum 排序号
     */
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	/**
     * 获取创建人
     *
     * @return CREATE_USER - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建部门
     *
     * @return CREATE_DEPT - 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 设置创建部门
     *
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }
	
}