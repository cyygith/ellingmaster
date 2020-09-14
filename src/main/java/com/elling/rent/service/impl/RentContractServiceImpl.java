package com.elling.rent.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.rent.dao.mapper.RentContractMapper;
import com.elling.rent.model.RentContract;
import com.elling.rent.service.RentContractService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
@Service
public class RentContractServiceImpl extends AbstractService<RentContract> implements RentContractService {

    @Autowired
    private RentContractMapper rentContractMapper;
    
	@Override
	public List<Map<String,Object>> getByCondition(RentContract rentContract) {
		return rentContractMapper.getByCondition(rentContract);
	}
}
