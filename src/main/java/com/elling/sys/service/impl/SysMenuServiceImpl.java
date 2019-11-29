package com.elling.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.elling.common.base.AbstractService;
import com.elling.common.entity.TreeNode;
import com.elling.common.utils.TreeBuilder;
import com.elling.sys.dao.mapper.SysMenuMapper;
import com.elling.sys.model.SysMenu;
import com.elling.sys.service.SysMenuService;

/**
 *
 * Created by cyy on 2019/08/01.
 */
@Service
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysMenu> getMenuLevel(Map map) {
		return sysMenuMapper.getMenuLevel(map);
	}
	
	/**
	 * 获取菜单信息
	 */
	@Override
	public List getMenuData(Map map) {
		List list = sysMenuMapper.getMenuData(map);
		List resulList = TreeBuilder.buildTree(list);
		return resulList;
	}
	
	/**
	 * 获取菜单信息
	 */
	@Override
	public List getAllMenuData(Map map) {
		List list = sysMenuMapper.getAllMenuData(map);
		List resulList = TreeBuilder.buildTree(list);
		return resulList;
	}

	@Override
	public List<TreeNode> getMenuTree(SysMenu sysMenu) {
		return sysMenuMapper.getMenuTree(sysMenu);
	}

	@Override
	public List<Map<String,Object>> getByCondition(SysMenu sysMenu) {
		return sysMenuMapper.getByCondition(sysMenu);
	}

}
