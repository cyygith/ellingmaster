package com.elling.rent.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.rent.dao.mapper.RentGroupMapper;
import com.elling.rent.model.RentGroup;
import com.elling.rent.service.RentGroupService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-09-11 16:26:23.
 */
@Service
public class RentGroupServiceImpl extends AbstractService<RentGroup> implements RentGroupService {

    @Autowired
    private RentGroupMapper rentGroupMapper;
    
	@Override
	public List<Map<String,Object>> getByCondition(RentGroup rentGroup) {
		return rentGroupMapper.getByCondition(rentGroup);
	}
}
