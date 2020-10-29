package com.elling.sys.service;
import java.util.List;
import java.util.Map;

import com.elling.sys.model.SysDictType;
import com.elling.common.base.Service;

/**
 *
 * Created by cyy on 2020-10-29 09:58:22.
 */
public interface SysDictTypeService extends Service<SysDictType> {
	
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<SysDictType> getByCondition(SysDictType sysDictType);
}
