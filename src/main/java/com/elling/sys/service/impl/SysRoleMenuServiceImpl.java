package com.elling.sys.service.impl;

import com.elling.sys.dao.mapper.SysRoleMenuMapper;
import com.elling.sys.model.SysRoleMenu;
import com.elling.sys.service.SysRoleMenuService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019/08/14.
 */
@Service
public class SysRoleMenuServiceImpl extends AbstractService<SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

}
