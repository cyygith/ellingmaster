package com.elling.rent.model;

import javax.persistence.*;

@Table(name = "rent_person")
public class RentPerson {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 租客编号
     */
    @Column(name = "PERSON_CODE")
    private String personCode;

    /**
     * 租客名称
     */
    @Column(name = "PERSON_NAME")
    private String personName;

    /**
     * 身份证号码
     */
    @Column(name = "IDENT_CARD")
    private String identCard;

    /**
     * 身份证地址
     */
    @Column(name = "IDENT_ADDR")
    private String identAddr;

    /**
     * 电话
     */
    @Column(name = "TEL")
    private String tel;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 紧急联系人
     */
    @Column(name = "EMERGENCY_PERSON")
    private String emergencyPerson;

    /**
     * 紧急联系人电话
     */
    @Column(name = "EMERGENCY_TEL")
    private String emergencyTel;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private String gender;

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
    
    /**
     * 实体ID
     */
    @Column(name = "TENANT_ID")
    private String tenantId;

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
     * 获取租客名称
     *
     * @return PERSON_NAME - 租客名称
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置租客名称
     *
     * @param personName 租客名称
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 获取身份证号码
     *
     * @return IDENT_CARD - 身份证号码
     */
    public String getIdentCard() {
        return identCard;
    }

    /**
     * 设置身份证号码
     *
     * @param identCard 身份证号码
     */
    public void setIdentCard(String identCard) {
        this.identCard = identCard;
    }

    /**
     * 获取身份证地址
     *
     * @return IDENT_ADDR - 身份证地址
     */
    public String getIdentAddr() {
        return identAddr;
    }

    /**
     * 设置身份证地址
     *
     * @param identAddr 身份证地址
     */
    public void setIdentAddr(String identAddr) {
        this.identAddr = identAddr;
    }

    /**
     * 获取电话
     *
     * @return TEL - 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话
     *
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取紧急联系人
     *
     * @return EMERGENCY_PERSON - 紧急联系人
     */
    public String getEmergencyPerson() {
        return emergencyPerson;
    }

    /**
     * 设置紧急联系人
     *
     * @param emergencyPerson 紧急联系人
     */
    public void setEmergencyPerson(String emergencyPerson) {
        this.emergencyPerson = emergencyPerson;
    }

    /**
     * 获取紧急联系人电话
     *
     * @return EMERGENCY_TEL - 紧急联系人电话
     */
    public String getEmergencyTel() {
        return emergencyTel;
    }

    /**
     * 设置紧急联系人电话
     *
     * @param emergencyTel 紧急联系人电话
     */
    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel;
    }

    /**
     * 获取性别
     *
     * @return GENDER - 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
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
}