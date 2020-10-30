package com.elling.rent.service;
import java.util.List;
import java.util.Map;

import com.elling.rent.model.RentConfig;
import com.elling.common.base.Service;

/**
 *
 * Created by cyy on 2020-10-30 16:38:12.
 */
public interface RentConfigService extends Service<RentConfig> {
	
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<RentConfig> getByCondition(RentConfig rentConfig);
}
