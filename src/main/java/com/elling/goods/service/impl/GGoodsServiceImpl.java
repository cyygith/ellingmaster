package com.elling.goods.service.impl;

import com.elling.goods.dao.mapper.GGoodsMapper;
import com.elling.goods.model.GGoods;
import com.elling.goods.service.GGoodsService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019/08/01.
 */
@Service
public class GGoodsServiceImpl extends AbstractService<GGoods> implements GGoodsService {

    @Autowired
    private GGoodsMapper gGoodsMapper;

	@Override
	public int deleteByUuids(List<String> ids) {
		gGoodsMapper.deleteByUuids(ids);
		return 0;
	}

	@Override
	public GGoods getGoodsById(GGoods gGoods) {
		return gGoodsMapper.getGoodsById(gGoods);
	}
    
}
