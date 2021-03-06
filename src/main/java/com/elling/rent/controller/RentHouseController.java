package com.elling.rent.controller;
import com.elling.rent.Constant;
import com.elling.rent.model.RentBill;
import com.elling.rent.model.RentHouse;
import com.elling.rent.service.RentHouseService;
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
@RequestMapping("/rentHouse/")
public class RentHouseController {
	private static Logger logger = Logger.getLogger(RentHouseController.class);
	
    @Autowired
    RentHouseService rentHouseService;
    @Autowired
    SequenceService sequenceService;

    @RequestMapping("add")
    public Result add(@RequestBody RentHouse rentHouse) {
    	try {
    		rentHouse.setCreateTime(DateUtil.getNowTime());
    		rentHouse.setHouseCode(sequenceService.getMaxBusinessValueByType(Constant.HOUSE_MODULE));
	        rentHouseService.save(rentHouse);
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
		    rentHouseService.deleteById(id);
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
    		rentHouseService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody RentHouse rentHouse) {
    	try {
    		rentHouse.setUpdateTime(DateUtil.getNowTime());
		    rentHouseService.update(rentHouse);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }
    /**
     * 新增或者更新
     * @param rentBill
     * @return
     */
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RentHouse rentHouse) {
    	try {
    		if(rentHouse.getId()!=null) {
    			rentHouse.setUpdateTime(DateUtil.getNowTime());
    			rentHouseService.update(rentHouse);
    		}else {
    			rentHouse.setCreateTime(DateUtil.getNowTime());
    			rentHouse.setHouseCode(sequenceService.getMaxBusinessValueByType(Constant.HOUSE_MODULE));
    			rentHouseService.save(rentHouse);
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
    	RentHouse rentHouse = null;
        try {
	        rentHouse = rentHouseService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success(rentHouse);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(RentHouse rentHouse) {
    	RentHouse rh = null;
    	try {
    		List<RentHouse> list = rentHouseService.getByCondition(rentHouse);
    		if(list!=null && list.size()>0) {
    			rh = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
        return Result.success(rh);
    }

    @RequestMapping("list")
    public Result list(RentHouse rentHouse,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
//	        PageHelper.startPage(page, size);
//	        List<RentHouse> list = rentHouseService.findAll();
//	        pageInfo = new PageInfo(list);
	        
	        PageHelper.startPage(page, size);
	        List<RentHouse> list = rentHouseService.getByCondition(rentHouse);
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
	        RentHouse rentHouse = rentHouseService.findBy(colomnName, colomnVal);
	        return Result.success(rentHouse);
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
    		
    		Condition condition = new Condition(RentHouse.class);
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
    		List<RentHouse> list = rentHouseService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
    }
}
