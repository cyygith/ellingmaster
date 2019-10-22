package com.elling.sys.dao.mapper;

import java.util.List;
import java.util.Map;

import com.elling.common.base.MyMapper;
import com.elling.common.entity.TreeNode;
import com.elling.sys.model.SysDept;

public interface SysDeptMapper extends MyMapper<SysDept> {
	
	/**
	 * .获取dept层级
	 * @return
	 */
	public List<SysDept> getDeptLevel(SysDept sysDept);
	
	/**
	 * 根据userId获取用户所属的部门ID，一般时单选，多选的情况下留着备用
	 * @param map
	 * @return
	 */
	public List<TreeNode> getDeptDataByUserId(Map map);
	/**
	 * 获取所有的部门树信息
	 * @param map
	 * @return
	 */
	public List<SysDept> getAllDeptData(Map map);
	
}