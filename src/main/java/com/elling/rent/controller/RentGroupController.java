package com.elling.rent.controller;
import com.elling.rent.Constant;
import com.elling.rent.model.RentBill;
import com.elling.rent.model.RentGroup;
import com.elling.rent.model.RentHouse;
import com.elling.rent.service.RentBillService;
import com.elling.rent.service.RentGroupService;
import com.elling.sys.service.SequenceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.elling.common.utils.DateUtil;
import com.elling.common.utils.StringUtil;
import com.elling.common.entity.Result;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 *
 * Created by cyy on 2020-09-11 16:26:23.
 */
@RestController
@RequestMapping("/rentGroup/")
public class RentGroupController {
	private static Logger logger = Logger.getLogger(RentGroupController.class);
	
    @Autowired
    RentGroupService rentGroupService;
    @Autowired
    SequenceService sequenceService;
    @Autowired
    RentBillService rentBillService;

    @RequestMapping("add")
    public Result add(@RequestBody RentGroup rentGroup) {
    	try {
    		rentGroup.setCreateTime(DateUtil.getNowTime());
    		rentGroup.setGroupCode(sequenceService.getMaxBusinessValueByType(Constant.GROUP_MODULE));
	        rentGroupService.save(rentGroup);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("delete")
    public Result delete(Integer id) {
    	try {
		    rentGroupService.deleteById(id);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }
    
    @RequestMapping("deleteByIds")
    public Result deleteByIds(@RequestBody Map map) {
    	try {
    		String ids = StringUtil.getString(map.get("ids"));
    		rentGroupService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody RentGroup rentGroup) {
    	try {
    		rentGroup.setUpdateTime(DateUtil.getNowTime());
		    rentGroupService.update(rentGroup);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }
    /**
     * 新增或者更新
     * @param
     * @return
     */
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RentGroup rentGroup) {
    	try {
    		if(rentGroup.getId()!=null) {
    			rentGroup.setUpdateTime(DateUtil.getNowTime());
    			rentGroupService.update(rentGroup);
    		}else {
    			rentGroup.setCreateTime(DateUtil.getNowTime());
    			rentGroup.setGroupCode(sequenceService.getMaxBusinessValueByType(Constant.GROUP_MODULE));
    			rentGroupService.save(rentGroup);
    		}
		    
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }
    @RequestMapping("detail")
    public Result detail(@RequestParam Long id) {
    	RentGroup rentGroup = null;
        try {
	        rentGroup = rentGroupService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success(rentGroup);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(RentGroup rentGroup) {
    	try {
    		List<Map<String,Object>> list = rentGroupService.getByCondition(rentGroup);
    		return Result.success(list);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
        
    }

    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
	        PageHelper.startPage(page, size);
	        List<RentGroup> list = rentGroupService.findAll();
	        pageInfo = new PageInfo(list);
	    }catch(Exception e) {
    	   e.printStackTrace();
	   	   logger.error(e.getMessage());
	   	   return Result.error(e.getMessage());
       	}
        return Result.success(pageInfo);
    }
    
    @RequestMapping("getByColumn")
    public Result getByColumn(@RequestParam String colomnName, @RequestParam String colomnVal) {
        try{
	        RentGroup rentGroup = rentGroupService.findBy(colomnName, colomnVal);
	        return Result.success(rentGroup);
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error(e.getMessage());
        	return Result.error(e.getMessage());
        }
    }
    
    @RequestMapping("getByExample")
    public Result getByExample(@RequestBody Map map) {
    	try {
    		int page = map.get("page")==null?1:(Integer.parseInt(StringUtil.getString(map.get("page"))));
    		int size = map.get("size")==null?10:(Integer.parseInt(StringUtil.getString(map.get("size"))));
    		map.remove("page");
    		map.remove("size");
    		
    		Condition condition = new Condition(RentGroup.class);
    		Criteria criteria  = condition.createCriteria();
    		Iterator it = map.entrySet().iterator();
    		while(it.hasNext()) {
    			Map.Entry<String,Object> entry = (Map.Entry)it.next();
    			String key = entry.getKey();
    			Object value = entry.getValue();
    			if(StringUtil.isNotEmpty(value)) {
    				criteria.andEqualTo(key, value);
    			}
    		}
    		
    		PageHelper.startPage(page, size);
    		List<RentGroup> list = rentGroupService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
    }
    @RequestMapping("getListByGroup")
    public Result getListByGroup(RentBill rentBill) {
    	Map<String,Object> rMap = new HashMap<String,Object>();
    	try {
    		List<Map<String,Object>> list = rentBillService.getListByGroup(rentBill);
    		if(list!=null && list.size()>0) {
    			List tempList = new ArrayList();
    			for(Map m:list) {
    				String groupCode = StringUtil.getString(m.get("groupCode"));
    				String groupName = StringUtil.getString(m.get("groupName"));
    				String houseCode = StringUtil.getString(m.get("houseCode"));
    				
    				if(rMap.get(groupCode)==null) {
    					Map<String,Object> tempMap = new HashMap<String,Object>();
    					tempMap.put("label", groupName);
    					tempMap.put("value", groupCode);
    					rMap.put(groupCode,tempMap);
    					
    					if(((List)((Map)rMap.get(groupCode)).get("option"))==null) {
    						tempList = new ArrayList();
        					tempList.add(m);
        					tempMap.put("option", tempList);
    					}
    					
    				}else {
//    					((List)rMap.get(groupCode)).add(m);
    					((List)((Map)rMap.get(groupCode)).get("option")).add(m);
    				}
    				
    				
//    				if(rMap.get(groupCode)==null) {
//    					
//    					
//    					((Map)rMap.get(groupCode)).get("value")==null
//    					
//    					Map<String,Object> tempMap = new HashMap<String,Object>();
//    					tempMap.put("label", groupName);
//    					tempMap.put("value", groupCode);
//    					
//    					tempList = new ArrayList();
//    					tempList.add(m);
//    					tempMap.put("option", tempList);
//    					
//    					rMap.put(groupCode,tempMap);
//    				}else {
////    					((List)rMap.get(groupCode)).add(m);
//    					((List)((Map)rMap.get(groupCode)).get("option")).add(m);
//    				}
    			}
    		}

    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
        return Result.success(rMap);
    }
}
