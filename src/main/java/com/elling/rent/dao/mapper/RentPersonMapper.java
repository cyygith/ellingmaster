package com.elling.rent.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.elling.common.base.MyMapper;
import com.elling.rent.model.RentPerson;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
public interface RentPersonMapper extends MyMapper<RentPerson> {
	/**
	 *	根据自定义条件查询
	 * @param map
	 * @return
	 */
	public List<RentPerson> getByCondition(RentPerson rentPerson);
	
	/**
	 *	批量新增用户
	 * @param map
	 * @return
	 */
	public void insertPersonBatch(@Param("list")List<RentPerson> rentPersonList);
	
}