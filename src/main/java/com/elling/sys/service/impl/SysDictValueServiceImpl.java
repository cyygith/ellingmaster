package com.elling.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.sys.dao.mapper.SysDictValueMapper;
import com.elling.sys.model.SysDictValue;
import com.elling.sys.service.SysDictValueService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-10-29 09:58:24.
 */
@Service
public class SysDictValueServiceImpl extends AbstractService<SysDictValue> implements SysDictValueService {

    @Autowired
    private SysDictValueMapper sysDictValueMapper;
    
	@Override
	public List<SysDictValue> getByCondition(SysDictValue sysDictValue) {
		return sysDictValueMapper.getByCondition(sysDictValue);
	}
	
	/**
	 *	根据类型获取数值
	 * @param map
	 * @return
	 */
	public List<SysDictValue> getDictByType(SysDictValue sysDictValue){
		return sysDictValueMapper.getDictByType(sysDictValue);
	};
}
