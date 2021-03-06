package com.elling.rent.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.rent.model.RentHouse;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
public interface RentHouseMapper extends MyMapper<RentHouse> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<RentHouse> getByCondition(RentHouse rentHouse);
	
	public int updateByUniqueId(RentHouse rentHouse);
}