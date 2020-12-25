package com.elling.rent;

public class Constant {
	
	public static String STATUS_NEW = "0";//租住状态 新建中
	
	public static String STATUS_USE = "1";//租住状态 已生效
	
	public static String BILL_MODULE = "BILL";//用户
	public static String PERSON_MODULE = "PERSON";//用户
	public static String HOUSE_MODULE = "HOUSE";//房屋
	public static String GROUP_MODULE = "GROUP";//组别
	public static String CONTRACT_MODULE = "CONTRACT";//合同
	public static String CONFIG_MODULE = "CONFIG";//参数
	
	//0-新增   1-缓存   2-全新
	public static String CACHETYPE_NEW = "0";//缓存类型  新增
	public static String CACHETYPE_CACHE = "1";//缓存类型  缓存
	public static String CACHETYPE_ADD = "3";//缓存类型  全新
	
	
	public static String BILL_STATUS_FINISH = "1";//账单状态 收租完成
	public static String BILL_STATUS_ING = "2";//账单状态 收租中
	
	public static String HOUSE_STATUS_NOT_RENT = "2";//房屋状态 未出租
	public static String HOUSE_STATUS_RENT = "3";//房屋状态 已出租
}
