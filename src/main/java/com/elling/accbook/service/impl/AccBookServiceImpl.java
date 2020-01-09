package com.elling.accbook.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.accbook.dao.mapper.AccBookMapper;
import com.elling.accbook.model.AccBook;
import com.elling.accbook.service.AccBookService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by CYY on 2020-01-03 10:44:03.
 */
@Service
public class AccBookServiceImpl extends AbstractService<AccBook> implements AccBookService {

    @Autowired
    private AccBookMapper accBookMapper;
    
	@Override
	public List<Map<String,Object>> getByCondition(AccBook accBook) {
		return accBookMapper.getByCondition(accBook);
	}
	
	@Override
	public List<Map<String,Object>> getSumByTypeAndTime(Map map){
		return accBookMapper.getSumByTypeAndTime(map);
	};
}
