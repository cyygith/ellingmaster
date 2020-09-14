package com.elling.rent.service;
import java.util.List;
import java.util.Map;

import com.elling.rent.model.RentPerson;
import com.elling.common.base.Service;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
public interface RentPersonService extends Service<RentPerson> {
	
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(RentPerson rentPerson);
}
