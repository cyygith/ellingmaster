package com.elling.rent.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.rent.model.RentBill;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
public interface RentBillMapper extends MyMapper<RentBill> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<RentBill> getByCondition(RentBill rentBill);
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getListByGroup(RentBill rentBill);
	/**
	 *	 监控查询
	 * @param map
	 * @return
	 */
	public List<RentBill> monitorRentEndTime(RentBill rentBill);
	
	
	/**
	 *	根据房间号统计所收租金总数
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRentSummaryGroup(RentBill rentBill);
	
	/**
	 *	根据时间，统计所收详细租金情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRentSummaryByMonth(RentBill rentBill);
	
	/**
	 *	根据具体房间号，统计所收详细租金情况
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRentDetailByHouseCode(Map<String,Object> parmMap);
	
	
	
	/**
	 *	获取收租总数及总次数
	 * @param map
	 * @return
	 */
	public Map<String,Object> getAllRentBillSum(Map<String,Object> parmMap);
	
	/**
	 *	获取收租总间数
	 * @param map
	 * @return
	 */
	public Map<String,Object> getAllHouseCount(Map<String,Object> parmMap);
	
	
	
}