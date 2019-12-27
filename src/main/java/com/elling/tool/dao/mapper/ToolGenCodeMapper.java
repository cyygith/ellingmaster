package com.elling.tool.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.tool.model.ToolGenCode;

/**
 *
 * Created by cyy on 2019-12-20 09:50:31.
 */
public interface ToolGenCodeMapper extends MyMapper<ToolGenCode> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(ToolGenCode toolGenCode);
}