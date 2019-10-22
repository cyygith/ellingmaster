package com.elling.sys.service;
import java.util.List;
import java.util.Map;

import com.elling.common.base.Service;
import com.elling.common.entity.TreeNode;
import com.elling.sys.model.SysUser;

/**
 *
 * Created by cyy on 2019/08/14.
 */
public interface SysUserService extends Service<SysUser> {
	
	List<TreeNode> getRoleByUserId(Map map);
	
	/**
	 * 保存用户的部门列表
	 * @param sysRole
	 * @return
	 */
	int saveModelAndDept(SysUser sysUser);
	/**
	 * 更新用户的部门列表
	 * @param sysRole
	 * @return
	 */
	int updateModelAndDept(SysUser sysUser);
	
	/**
	 * 删除用户的部门列表
	 * @param sysRole
	 * @return
	 */
	int deleteModelAndDept(Map map);
}
