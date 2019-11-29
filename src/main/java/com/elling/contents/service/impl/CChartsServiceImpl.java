package com.elling.contents.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elling.common.base.AbstractService;
import com.elling.contents.dao.mapper.CChartsMapper;
import com.elling.contents.model.CArticle;
import com.elling.contents.service.CChartsService;

/**
 *
 * Created by cyy on 2019-10-25 15:01:45.
 */
@Service
public class CChartsServiceImpl extends AbstractService<CArticle> implements CChartsService {

    @Autowired
    private CChartsMapper cChartsMapper;

	@Override
	public List getCLineCharts(Map map) {
		return cChartsMapper.getCLineCharts(map);
	}

}
