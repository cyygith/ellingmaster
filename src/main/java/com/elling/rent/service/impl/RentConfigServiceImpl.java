package com.elling.rent.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.rent.dao.mapper.RentConfigMapper;
import com.elling.rent.model.RentConfig;
import com.elling.rent.service.RentConfigService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-10-30 16:38:12.
 */
@Service
public class RentConfigServiceImpl extends AbstractService<RentConfig> implements RentConfigService {

    @Autowired
    private RentConfigMapper rentConfigMapper;
    
	@Override
	public List<RentConfig> getByCondition(RentConfig rentConfig) {
		return rentConfigMapper.getByCondition(rentConfig);
	}
}
