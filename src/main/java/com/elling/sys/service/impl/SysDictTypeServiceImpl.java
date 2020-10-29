package com.elling.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.sys.dao.mapper.SysDictTypeMapper;
import com.elling.sys.model.SysDictType;
import com.elling.sys.service.SysDictTypeService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-10-29 09:58:22.
 */
@Service
public class SysDictTypeServiceImpl extends AbstractService<SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;
    
	@Override
	public List<SysDictType> getByCondition(SysDictType sysDictType) {
		return sysDictTypeMapper.getByCondition(sysDictType);
	}
}
