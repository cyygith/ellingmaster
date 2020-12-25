package com.elling.rent.model;

import javax.persistence.*;

@Table(name = "rent_config")
public class RentConfig {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房东编号
     */
    @Column(name = "HOST_ID")
    private String hostId;

    /**
     * 房东名称
     */
    @Column(name = "HOST_NAME")
    private String hostName;

    /**
     * 状态（字典）
     */
    @Column(name = "STATUS")
    private String status;

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
     * 实体ID（字典）
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
     * 获取房东编号
     *
     * @return HOST_ID - 房东编号
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * 设置房东编号
     *
     * @param hostId 房东编号
     */
    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    /**
     * 获取房东名称
     *
     * @return HOST_NAME - 房东名称
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * 设置房东名称
     *
     * @param hostName 房东名称
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * 获取状态（字典）
     *
     * @return STATUS - 状态（字典）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（字典）
     *
     * @param status 状态（字典）
     */
    public void setStatus(String status) {
        this.status = status;
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

    /**
     * 获取实体ID（字典）
     *
     * @return TENANT_ID - 实体ID（字典）
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置实体ID（字典）
     *
     * @param tenantId 实体ID（字典）
     */
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