package com.elling.sys.service.impl;

import com.elling.sys.dao.mapper.SysConfigMapper;
import com.elling.sys.model.SysConfig;
import com.elling.sys.service.SysConfigService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019/08/14.
 */
@Service
public class SysConfigServiceImpl extends AbstractService<SysConfig> implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

}
