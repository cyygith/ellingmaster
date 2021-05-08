package com.elling.rent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elling.common.base.AbstractService;
import com.elling.rent.Constant;
import com.elling.rent.dao.mapper.RentBillMapper;
import com.elling.rent.dao.mapper.RentPersonMapper;
import com.elling.rent.model.RentBill;
import com.elling.rent.model.RentPerson;
import com.elling.rent.service.RentBillService;
import com.elling.sys.service.SequenceService;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
@Service
public class RentBillServiceImpl extends AbstractService<RentBill> implements RentBillService {

    @Autowired
    private RentBillMapper rentBillMapper;
    @Autowired
    private RentPersonMapper rentPersonMapper;
    @Autowired
    SequenceService sequenceService;
    
	@Override
	public List<RentBill> getByCondition(RentBill rentBill) {
		return rentBillMapper.getByCondition(rentBill);
	}
	
	@Override
	public List<Map<String,Object>> getListByGroup(RentBill rentBill) {
		return rentBillMapper.getListByGroup(rentBill);
	}
	
	@Override
	public List<RentBill> monitorRentEndTime(RentBill rentBill) {
		return rentBillMapper.monitorRentEndTime(rentBill);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdateBillAndPerson(RentBill rentBill) {
		
		//保存人员信息,当personCode为空的情况下证明是新增的
		List<String> personCodes = new ArrayList<String>();
		List<RentPerson> person = rentBill.getPersonList();
		if(null!=person&&person.size()>0) {
			List<RentPerson> tPersonList = new ArrayList<RentPerson>();
			for(int i=0;i<person.size();i++) {
				RentPerson p = person.get(i);
				if(p.getPersonCode()==null) {
					String pCode = sequenceService.getMaxBusinessValueByType(Constant.PERSON_MODULE);
					p.setPersonCode(pCode);
					personCodes.add(pCode);
					tPersonList.add(p);
				}else {
					personCodes.add(p.getPersonCode());
				}
			}
			
			if(tPersonList.size()>0) {
				rentPersonMapper.insertPersonBatch(tPersonList);
			}
			rentBill.setPersonCodes(StringUtils.join(personCodes.iterator(), ","));
		}
		
		//保存基本信息
		if(rentBill.getId()!=null) {
			rentBillMapper.updateByPrimaryKeySelective(rentBill);
		}else {
			rentBillMapper.insertSelective(rentBill);
		}
	}

	@Override
	public List<Map<String, Object>> getRentSummaryGroup(RentBill rentBill) {
		return rentBillMapper.getRentSummaryGroup(rentBill);
	}

	@Override
	public List<Map<String, Object>> getRentSummaryByMonth(RentBill rentBill) {
		return rentBillMapper.getRentSummaryByMonth(rentBill);
	}
	
	@Override
	public List<Map<String,Object>> getRentDetailByHouseCode(Map<String,Object> parmMap){
		return rentBillMapper.getRentDetailByHouseCode(parmMap);
	};
	
	public Map<String,Object> getRentAllSummary(Map<String,Object> parmMap){
		Map<String,Object> summarySum = new HashMap<String,Object>();
		Map tMap = rentBillMapper.getAllRentBillSum(parmMap);
		Map rMap1 = rentBillMapper.getAllHouseCount(parmMap);
		summarySum.putAll(tMap);
		summarySum.putAll(rMap1);
		return summarySum;
	};

}
