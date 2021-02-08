package com.elling.rent.controller;
import com.elling.rent.Constant;
import com.elling.rent.model.RentGroup;
import com.elling.rent.model.RentPerson;
import com.elling.rent.service.RentPersonService;
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
 * Created by cyy on 2020-09-11 16:16:42.
 */
@RestController
@RequestMapping("/rentPerson/")
public class RentPersonController {
	private static Logger logger = Logger.getLogger(RentPersonController.class);
	
    @Autowired
    RentPersonService rentPersonService;
    @Autowired
    SequenceService sequenceService;

    @RequestMapping("add")
    public Result add(@RequestBody RentPerson rentPerson) {
    	try {
    		rentPerson.setCreateTime(DateUtil.getNowTime());
    		rentPerson.setPersonCode(sequenceService.getMaxBusinessValueByType(Constant.PERSON_MODULE));
	        rentPersonService.save(rentPerson);
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
		    rentPersonService.deleteById(id);
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
    		rentPersonService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody RentPerson rentPerson) {
    	try {
    		rentPerson.setUpdateTime(DateUtil.getNowTime());
		    rentPersonService.update(rentPerson);
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
    public Result saveOrUpdate(@RequestBody RentPerson rentPerson) {
    	try {
    		if(rentPerson.getId()!=null) {
    			rentPerson.setUpdateTime(DateUtil.getNowTime());
    			rentPersonService.update(rentPerson);
    		}else {
    			rentPerson.setCreateTime(DateUtil.getNowTime());
    			rentPerson.setPersonCode(sequenceService.getMaxBusinessValueByType(Constant.PERSON_MODULE));
    			rentPersonService.save(rentPerson);
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
    	RentPerson rentPerson = null;
        try {
	        rentPerson = rentPersonService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success(rentPerson);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(RentPerson rentPerson) {
    	RentPerson person = null;
    	try {
    		List<RentPerson> list = rentPersonService.getByCondition(rentPerson);
    		if(list!=null && list.size()>0) {
    			person = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
        return Result.success(person);
    }

    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
	        PageHelper.startPage(page, size);
	        List<RentPerson> list = rentPersonService.findAll();
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
	        RentPerson rentPerson = rentPersonService.findBy(colomnName, colomnVal);
	        return Result.success(rentPerson);
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
    		
    		Condition condition = new Condition(RentPerson.class);
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
    		List<RentPerson> list = rentPersonService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
    }
}
