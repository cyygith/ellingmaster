package com.elling.rent.controller;
import com.elling.rent.Constant;
import com.elling.rent.model.RentConfig;
import com.elling.rent.service.RentConfigService;
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
import com.elling.common.utils.StringUtil;
import com.elling.common.utils.DateUtil;
import com.elling.common.entity.Result;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 *
 * Created by cyy on 2020-10-30 16:38:12.
 */
@RestController
@RequestMapping("/rentConfig/")
public class RentConfigController {
	private static Logger logger = Logger.getLogger(RentConfigController.class);
	
    @Autowired
    RentConfigService rentConfigService;
    @Autowired
    SequenceService sequenceService;

    @RequestMapping("add")
    public Result add(@RequestBody RentConfig rentConfig) {
    	try {
    		rentConfig.setCreateTime(DateUtil.getNowTime());
    		rentConfig.setHostId(sequenceService.getMaxBusinessValueByType(Constant.CONFIG_MODULE));
	        rentConfigService.save(rentConfig);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("delete")
    public Result delete(Integer id) {
    	try {
		    rentConfigService.deleteById(id);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }
    
    @RequestMapping("deleteByIds")
    public Result deleteByIds(@RequestBody Map map) {
    	try {
    		String ids = StringUtil.getString(map.get("ids"));
    		rentConfigService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody RentConfig rentConfig) {
    	try {
    		rentConfig.setUpdateTime(DateUtil.getNowTime());
		    rentConfigService.update(rentConfig);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }
	
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RentConfig rentConfig) {
    	try {
    		if(rentConfig.getId()!=null) {
    			rentConfig.setUpdateTime(DateUtil.getNowTime());
    			rentConfigService.update(rentConfig);
    		}else {
    			rentConfig.setCreateTime(DateUtil.getNowTime());
    			rentConfig.setHostId(sequenceService.getMaxBusinessValueByType(Constant.CONFIG_MODULE));
    			rentConfigService.save(rentConfig);
    		}
		    
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }
	
    @RequestMapping("detail")
    public Result detail(@RequestParam Long id) {
    	RentConfig rentConfig = null;
        try {
	        rentConfig = rentConfigService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success(rentConfig);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(RentConfig rentConfig) {
    	try {
    		List<RentConfig> list = rentConfigService.getByCondition(rentConfig);
    		if(list!=null && list.size()>0) {
    			rentConfig = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
        return Result.success(rentConfig);
    }

    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
	        PageHelper.startPage(page, size);
	        List<RentConfig> list = rentConfigService.findAll();
	        pageInfo = new PageInfo(list);
	    }catch(Exception e) {
    	   e.printStackTrace();
	   	   logger.error("操作异常",e);
	   	   return Result.error("内部出错："+e.getMessage());
       	}
        return Result.success(pageInfo);
    }
    
    @RequestMapping("getByColumn")
    public Result getByColumn(@RequestParam String colomnName, @RequestParam String colomnVal) {
        try{
	        RentConfig rentConfig = rentConfigService.findBy(colomnName, colomnVal);
	        return Result.success(rentConfig);
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error("操作异常",e);
        	return Result.error("内部出错："+e.getMessage());
        }
    }
    
    @RequestMapping("getByExample")
    public Result getByExample(@RequestBody Map map) {
    	try {
    		int page = map.get("page")==null?1:(Integer.parseInt(StringUtil.getString(map.get("page"))));
    		int size = map.get("size")==null?10:(Integer.parseInt(StringUtil.getString(map.get("size"))));
    		map.remove("page");
    		map.remove("size");
    		
    		Condition condition = new Condition(RentConfig.class);
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
    		List<RentConfig> list = rentConfigService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
    }
}
