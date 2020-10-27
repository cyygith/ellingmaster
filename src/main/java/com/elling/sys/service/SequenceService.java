package com.elling.sys.service;
import java.util.List;
import java.util.Map;

import com.elling.common.base.Service;
import com.elling.sys.model.Sequence;

/**
 *
 * Created by cyy on 2020-10-20 17:32:02.
 */
public interface SequenceService extends Service<Sequence> {
	
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
	public String getMaxBusinessValueByType(String type);

}
