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
}