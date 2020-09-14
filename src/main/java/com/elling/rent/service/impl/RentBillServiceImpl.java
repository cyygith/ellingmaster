package com.elling.rent.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.rent.dao.mapper.RentBillMapper;
import com.elling.rent.model.RentBill;
import com.elling.rent.service.RentBillService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
@Service
public class RentBillServiceImpl extends AbstractService<RentBill> implements RentBillService {

    @Autowired
    private RentBillMapper rentBillMapper;
    
	@Override
	public List<Map<String,Object>> getByCondition(RentBill rentBill) {
		return rentBillMapper.getByCondition(rentBill);
	}
}
