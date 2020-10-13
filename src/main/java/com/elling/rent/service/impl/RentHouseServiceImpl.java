package com.elling.rent.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.rent.dao.mapper.RentHouseMapper;
import com.elling.rent.model.RentHouse;
import com.elling.rent.service.RentHouseService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
@Service
public class RentHouseServiceImpl extends AbstractService<RentHouse> implements RentHouseService {

    @Autowired
    private RentHouseMapper rentHouseMapper;
    
	@Override
	public List<RentHouse> getByCondition(RentHouse rentHouse) {
		return rentHouseMapper.getByCondition(rentHouse);
	}
}
