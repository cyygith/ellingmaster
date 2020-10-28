package com.elling.sys.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.sys.model.Sequence;

/**
 *
 * Created by cyy on 2020-10-20 17:29:00.
 */
public interface SequenceMapper extends MyMapper<Sequence> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(Sequence sequence);
	/**
	 *     根据类型获取当天  该业务类型最大值
	 * @param map
	 * @return
	 */
	public Sequence getMaxValueByType(Sequence sequence);
}