package com.elling.rent.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.rent.dao.mapper.RentPersonMapper;
import com.elling.rent.model.RentPerson;
import com.elling.rent.service.RentPersonService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
@Service
public class RentPersonServiceImpl extends AbstractService<RentPerson> implements RentPersonService {

    @Autowired
    private RentPersonMapper rentPersonMapper;
    
	@Override
	public List<Map<String,Object>> getByCondition(RentPerson rentPerson) {
		return rentPersonMapper.getByCondition(rentPerson);
	}
}
