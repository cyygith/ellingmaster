package com.elling.goods.dao.mapper;

import java.util.List;

import com.elling.common.base.MyMapper;
import com.elling.goods.model.GGoods;

public interface GGoodsMapper extends MyMapper<GGoods> {
	
	int deleteByUuids(List<String> ids);
	
	/**
	 * 根据其他条件查询Goods
	 * @param gGoods
	 * @return
	 */
	public GGoods getGoodsById(GGoods gGoods);
}