package com.elling.rent.model;

import javax.persistence.*;

@Table(name = "rent_group")
public class RentGroup {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房组编号
     */
    @Column(name = "GROUP_CODE")
    private String groupCode;

    /**
     * 房组名称
     */
    @Column(name = "GROUP_NAME")
    private String groupName;

    /**
     * 房组地址
     */
    @Column(name = "GROUP_ADDRESS")
    private String groupAddress;

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
     * 获取房组编号
     *
     * @return GROUP_CODE - 房组编号
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * 设置房组编号
     *
     * @param groupCode 房组编号
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * 获取房组名称
     *
     * @return GROUP_NAME - 房组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置房组名称
     *
     * @param groupName 房组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取房组地址
     *
     * @return GROUP_ADDRESS - 房组地址
     */
    public String getGroupAddress() {
        return groupAddress;
    }

    /**
     * 设置房组地址
     *
     * @param groupAddress 房组地址
     */
    public void setGroupAddress(String groupAddress) {
        this.groupAddress = groupAddress;
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