package com.elling.rent.service;
import java.util.List;
import java.util.Map;

import com.elling.rent.model.RentBill;
import com.elling.common.base.Service;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
public interface RentBillService extends Service<RentBill> {
	
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
	 *	根据自定义条件查询  监控查询
	 * @param map
	 * @return
	 */
	public List<RentBill> monitorRentEndTime(RentBill rentBill);
}
