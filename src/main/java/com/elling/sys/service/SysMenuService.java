package com.elling.sys.service;
import java.util.List;
import java.util.Map;

import com.elling.common.base.Service;
import com.elling.sys.model.SysMenu;

/**
 *
 * Created by cyy on 2019/08/01.
 */
public interface SysMenuService extends Service<SysMenu> {
	/**
	 * .获取菜单层级
	 * @return
	 */
	public List<SysMenu> getMenuLevel(SysMenu sysMenu);
	
	/**
	 * 获取左边菜单数据，根据传入角色id进行筛选
	 * @param map
	 * @return
	 */
	public List getMenuData(Map map);
	
	/**
	 * 获取所有的菜单节点数据
	 * @param map
	 * @return
	 */
	public List getAllMenuData(Map map);
	
}
