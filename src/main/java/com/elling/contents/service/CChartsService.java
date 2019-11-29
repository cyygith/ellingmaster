package com.elling.contents.service;

import java.util.List;
import java.util.Map;

import com.elling.common.base.Service;
import com.elling.contents.model.CArticle;

public interface CChartsService extends Service<CArticle>{
	
	public List getCLineCharts(Map map);
}
