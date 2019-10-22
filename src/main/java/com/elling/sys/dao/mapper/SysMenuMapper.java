package com.elling.sys.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.common.entity.TreeNode;
import com.elling.sys.model.SysMenu;

public interface SysMenuMapper extends MyMapper<SysMenu> {
	
	public List<SysMenu> getMenuLevel(SysMenu sysMenu);
	
	/**
	 * 根据角色ID获取对应拥有的菜单信息
	 * @param map
	 * @return
	 */
	public List<TreeNode> getMenuData(Map map);
	
	/**
	 * 获取所有菜单信息
	 * @param map
	 * @return
	 */
	
	public List getAllMenuData(Map map);
}