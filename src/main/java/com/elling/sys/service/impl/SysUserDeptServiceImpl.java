package com.elling.sys.service.impl;

import com.elling.sys.dao.mapper.SysUserDeptMapper;
import com.elling.sys.model.SysUserDept;
import com.elling.sys.service.SysUserDeptService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019/08/14.
 */
@Service
public class SysUserDeptServiceImpl extends AbstractService<SysUserDept> implements SysUserDeptService {

    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;

}
