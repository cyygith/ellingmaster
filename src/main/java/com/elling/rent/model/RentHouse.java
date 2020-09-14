package com.elling.rent.model;

import javax.persistence.*;

@Table(name = "rent_house")
public class RentHouse {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 房屋编号
     */
    @Column(name = "HOUSE_CODE")
    private String houseCode;

    /**
     * 房屋名称
     */
    @Column(name = "HOUSE_NAME")
    private String houseName;

    /**
     * 房组编号
     */
    @Column(name = "GROUP_CODE")
    private String groupCode;

    /**
     * 房屋地址
     */
    @Column(name = "HOUSE_ADDRESS")
    private String houseAddress;

    /**
     * 面积
     */
    @Column(name = "HOUSE_AREA")
    private String houseArea;

    /**
     * 租金
     */
    @Column(name = "HOUSE_PRICE")
    private String housePrice;

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
     * 获取房屋名称
     *
     * @return HOUSE_NAME - 房屋名称
     */
    public String getHouseName() {
        return houseName;
    }

    /**
     * 设置房屋名称
     *
     * @param houseName 房屋名称
     */
    public void setHouseName(String houseName) {
        this.houseName = houseName;
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
     * 获取房屋地址
     *
     * @return HOUSE_ADDRESS - 房屋地址
     */
    public String getHouseAddress() {
        return houseAddress;
    }

    /**
     * 设置房屋地址
     *
     * @param houseAddress 房屋地址
     */
    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    /**
     * 获取面积
     *
     * @return HOUSE_AREA - 面积
     */
    public String getHouseArea() {
        return houseArea;
    }

    /**
     * 设置面积
     *
     * @param houseArea 面积
     */
    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    /**
     * 获取租金
     *
     * @return HOUSE_PRICE - 租金
     */
    public String getHousePrice() {
        return housePrice;
    }

    /**
     * 设置租金
     *
     * @param housePrice 租金
     */
    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
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
}