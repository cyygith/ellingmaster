package com.elling.rent.service;
import java.util.List;
import java.util.Map;

import com.elling.rent.model.RentGroup;
import com.elling.common.base.Service;

/**
 *
 * Created by cyy on 2020-09-11 16:26:23.
 */
public interface RentGroupService extends Service<RentGroup> {
	
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(RentGroup rentGroup);
}
