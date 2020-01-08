package com.elling.accbook.service;
import java.util.List;
import java.util.Map;

import com.elling.accbook.model.AccBook;
import com.elling.common.base.Service;

/**
 *
 * Created by CYY on 2020-01-03 10:44:03.
 */
public interface AccBookService extends Service<AccBook> {
	
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getByCondition(AccBook accBook);
}