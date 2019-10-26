package com.elling.contents.service.impl;

import com.elling.contents.dao.mapper.CArticleMapper;
import com.elling.contents.model.CArticle;
import com.elling.contents.service.CArticleService;
import com.elling.common.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2019-10-25 15:01:45.
 */
@Service
public class CArticleServiceImpl extends AbstractService<CArticle> implements CArticleService {

    @Autowired
    private CArticleMapper cArticleMapper;

}
