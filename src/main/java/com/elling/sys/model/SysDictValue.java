package com.elling.sys.model;

import javax.persistence.*;

@Table(name = "sys_dict_value")
public class SysDictValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父节点
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 代码
     */
    @Column(name = "update_time")
    private String code;

    /**
     * 代码值
     */
    @Column(name = "code_value")
    private String codeValue;

    /**
     * 父级Code值
     */
    @Column(name = "p_code")
    private String pCode;

    /**
     * 状态值
     */
    @Column(name = "status")
    private String status;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private String updateTime;
    /**
     * 冗余数据  父节点类型
     */
    @Transient
    private String typeCode;

    /**
     * @return id
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
     * 获取父节点
     *
     * @return pid - 父节点
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父节点
     *
     * @param pid 父节点
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取代码值
     *
     * @return code_value - 代码值
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * 设置代码值
     *
     * @param codeValue 代码值
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * 获取父级Code值
     *
     * @return p_code - 父级Code值
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 设置父级Code值
     *
     * @param pCode 父级Code值
     */
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    /**
     * 获取状态值
     *
     * @return status - 状态值
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态值
     *
     * @param status 状态值
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取排序
     *
     * @return order_num - 排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序
     *
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
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
     * @return create_time - 创建时间
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
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
    
    
    
}