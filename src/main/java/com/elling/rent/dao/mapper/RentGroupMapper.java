package com.elling.rent.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.rent.model.RentGroup;

/**
 *
 * Created by cyy on 2020-09-11 16:26:23.
 */
public interface RentGroupMapper extends MyMapper<RentGroup> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(RentGroup rentGroup);
}