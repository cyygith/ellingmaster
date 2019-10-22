package com.elling.sys.service.impl;

import com.elling.sys.dao.mapper.SysUserRoleMapper;
import com.elling.sys.model.SysUserRole;
import com.elling.sys.service.SysUserRoleService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019/08/14.
 */
@Service
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

}
