package com.elling.rent.model;

import javax.persistence.*;

@Table(name = "rent_bill")
public class RentBill {
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
     * 房屋编号
     */
    @Column(name = "HOUSE_CODE")
    private String houseCode;

    /**
     * 租金
     */
    @Column(name = "MONEY")
    private String money;

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
     * 上月电费
     */
    @Column(name = "LAST_ELECTRIC")
    private String lastElectric;

    /**
     * 本月电费
     */
    @Column(name = "CURR_ELECTRIC")
    private String currElectric;

    /**
     * 水费支付方式  1-按吨支付  2-按人数支付
     */
    @Column(name = "WATER_PAY_TYPE")
    private String waterPayType;

    /**
     * 租住人数
     */
    @Column(name = "RENT_NUM")
    private Long rentNum;

    /**
     * 上月水费
     */
    @Column(name = "LAST_WATER")
    private String lastWater;

    /**
     * 本月水费
     */
    @Column(name = "CURR_WATER")
    private String currWater;

    /**
     * 租金支付方式 1-支付宝  2-微信  3-现金
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
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private String updateTime;
    
    @Transient
    private String orderBy;
    @Transient
    private String houseName;
    @Transient
    private String groupName;
    

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
     * 获取租金
     *
     * @return MONEY - 租金
     */
    public String getMoney() {
        return money;
    }

    /**
     * 设置租金
     *
     * @param money 租金
     */
    public void setMoney(String money) {
        this.money = money;
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
     * 获取上月电费
     *
     * @return LAST_ELECTRIC - 上月电费
     */
    public String getLastElectric() {
        return lastElectric;
    }

    /**
     * 设置上月电费
     *
     * @param lastElectric 上月电费
     */
    public void setLastElectric(String lastElectric) {
        this.lastElectric = lastElectric;
    }

    /**
     * 获取本月电费
     *
     * @return CURR_ELECTRIC - 本月电费
     */
    public String getCurrElectric() {
        return currElectric;
    }

    /**
     * 设置本月电费
     *
     * @param currElectric 本月电费
     */
    public void setCurrElectric(String currElectric) {
        this.currElectric = currElectric;
    }

    /**
     * 获取水费支付方式  1-按吨支付  2-按人数支付
     *
     * @return WATER_PAY_TYPE - 水费支付方式  1-按吨支付  2-按人数支付
     */
    public String getWaterPayType() {
        return waterPayType;
    }

    /**
     * 设置水费支付方式  1-按吨支付  2-按人数支付
     *
     * @param waterPayType 水费支付方式  1-按吨支付  2-按人数支付
     */
    public void setWaterPayType(String waterPayType) {
        this.waterPayType = waterPayType;
    }

    /**
     * 获取租住人数
     *
     * @return RENT_NUM - 租住人数
     */
    public Long getRentNum() {
        return rentNum;
    }

    /**
     * 设置租住人数
     *
     * @param rentNum 租住人数
     */
    public void setRentNum(Long rentNum) {
        this.rentNum = rentNum;
    }

    /**
     * 获取上月水费
     *
     * @return LAST_WATER - 上月水费
     */
    public String getLastWater() {
        return lastWater;
    }

    /**
     * 设置上月水费
     *
     * @param lastWater 上月水费
     */
    public void setLastWater(String lastWater) {
        this.lastWater = lastWater;
    }

    /**
     * 获取本月水费
     *
     * @return CURR_WATER - 本月水费
     */
    public String getCurrWater() {
        return currWater;
    }

    /**
     * 设置本月水费
     *
     * @param currWater 本月水费
     */
    public void setCurrWater(String currWater) {
        this.currWater = currWater;
    }

    /**
     * 获取租金支付方式 1-支付宝  2-微信  3-现金
     *
     * @return PAY_TYPE - 租金支付方式 1-支付宝  2-微信  3-现金
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置租金支付方式 1-支付宝  2-微信  3-现金
     *
     * @param payType 租金支付方式 1-支付宝  2-微信  3-现金
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    
    
    
}