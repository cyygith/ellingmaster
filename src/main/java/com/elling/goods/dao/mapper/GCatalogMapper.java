package com.elling.goods.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.common.entity.TreeNode;
import com.elling.goods.model.GCatalog;

public interface GCatalogMapper extends MyMapper<GCatalog> {
	int deleteByUuids(List<String> ids);
	
	/**
	 *  根据catalog ID获取对应拥有的树信息
	 * @param map
	 * @return
	 */
	public List<TreeNode> getCatalogTree(GCatalog gCatalog);
	
	/**
	 * 获取单个节点信息（根据对应的查询条件）
	 * @param gCatalog
	 * @return
	 */
	public GCatalog getCatalogById(GCatalog gCatalog);
}