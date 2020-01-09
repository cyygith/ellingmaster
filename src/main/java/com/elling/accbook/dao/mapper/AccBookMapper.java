package com.elling.accbook.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.accbook.model.AccBook;
import com.elling.common.base.MyMapper;

public interface AccBookMapper extends MyMapper<AccBook> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(AccBook accBook);
	
	/**
	 *	根据类型和时间获取总结数
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getSumByTypeAndTime(Map map);
}