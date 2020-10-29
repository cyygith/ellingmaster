package com.elling.sys.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.sys.model.SysDictType;

/**
 *
 * Created by cyy on 2020-10-29 09:58:23.
 */
public interface SysDictTypeMapper extends MyMapper<SysDictType> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<SysDictType> getByCondition(SysDictType sysDictType);
}