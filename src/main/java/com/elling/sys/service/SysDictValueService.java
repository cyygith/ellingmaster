package com.elling.sys.service;
import java.util.List;
import java.util.Map;

import com.elling.sys.model.SysDictValue;
import com.elling.common.base.Service;

/**
 *
 * Created by cyy on 2020-10-29 09:58:24.
 */
public interface SysDictValueService extends Service<SysDictValue> {
	
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<SysDictValue> getByCondition(SysDictValue sysDictValue);
	
	/**
	 *	根据类型获取数值
	 * @param map
	 * @return
	 */
	public List<SysDictValue> getDictByType(SysDictValue sysDictValue);
	
}
