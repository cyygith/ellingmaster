package com.elling.sys.model;

import javax.persistence.*;

@Table(name = "sys_dict_type")
public class SysDictType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父节点
     */
    private Long pid;

    /**
     * 父级Code
     */
    @Column(name = "p_code")
    private String pCode;

    /**
     * 字典值
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 字点名
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 状态值
     */
    private String status;

    /**
     * 排序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 备注
     */
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
     * 获取父级Code
     *
     * @return p_code - 父级Code
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 设置父级Code
     *
     * @param pCode 父级Code
     */
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    /**
     * 获取字典值
     *
     * @return type_code - 字典值
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置字典值
     *
     * @param typeCode 字典值
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取字点名
     *
     * @return type_name - 字点名
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置字点名
     *
     * @param typeName 字点名
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
}