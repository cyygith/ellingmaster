package com.elling.sys.service.impl;

import com.elling.sys.dao.mapper.SysDictMapper;
import com.elling.sys.model.SysDict;
import com.elling.sys.service.SysDictService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019/08/14.
 */
@Service
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

}
