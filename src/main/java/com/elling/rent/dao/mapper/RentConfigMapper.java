package com.elling.rent.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.rent.model.RentConfig;

/**
 *
 * Created by cyy on 2020-10-30 16:38:12.
 */
public interface RentConfigMapper extends MyMapper<RentConfig> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<RentConfig> getByCondition(RentConfig rentConfig);
}