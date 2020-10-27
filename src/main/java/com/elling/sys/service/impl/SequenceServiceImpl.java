package com.elling.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.elling.sys.dao.mapper.SequenceMapper;
import com.elling.sys.model.Sequence;
import com.elling.sys.service.SequenceService;
import com.elling.common.base.AbstractService;
import com.elling.common.utils.DateUtil;
import com.elling.common.utils.StringUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by cyy on 2020-10-20 17:32:02.
 */
@Service
public class SequenceServiceImpl extends AbstractService<Sequence> implements SequenceService {
	
	private static String TYPE_SUFFIX = "_SEQ_NO";
    @Autowired
    private SequenceMapper sequenceMapper;
    
	@Override
	public List<Map<String,Object>> getByCondition(Sequence sequence) {
		return sequenceMapper.getByCondition(sequence);
	}
	/**
	 *     根据类型获取当天  该业务类型最大值
	 * @param map
	 * @return
	 */
	public String getMaxBusinessValueByType(String type) {
		StringBuffer sb = new StringBuffer();
		String max_value = "";
		Sequence sq = new Sequence();
		sq.setSequenceName(type+TYPE_SUFFIX);
		sq = sequenceMapper.getMaxBusinessValueByType(sq);
		if(null!=sq&&StringUtil.isNotEmpty(sq.getMaxValue())) {
			max_value = StringUtil.getString(sq.getMaxValue());
		}else {
			max_value = "10000";
		}
		sb.append(type).append(DateUtil.getNowTime()).append(max_value);
		
		return sb.toString();
	}
	
	
	
	
	
}
