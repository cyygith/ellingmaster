package com.elling.sys.controller;
import com.elling.sys.model.SysDictType;
import com.elling.sys.model.SysDictValue;
import com.elling.sys.service.SysDictValueService;
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
 * Created by cyy on 2020-10-29 09:58:24.
 */
@RestController
@RequestMapping("/sysDictValue/")
public class SysDictValueController {
	private static Logger logger = Logger.getLogger(SysDictValueController.class);
	
    @Autowired
    SysDictValueService sysDictValueService;

    @RequestMapping("add")
    public Result add(@RequestBody SysDictValue sysDictValue) {
    	try {
    		sysDictValue.setCreateTime(DateUtil.getNowTime());
	        sysDictValueService.save(sysDictValue);
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
		    sysDictValueService.deleteById(id);
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
    		sysDictValueService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody SysDictValue sysDictValue) {
    	try {
    		sysDictValue.setUpdateTime(DateUtil.getNowTime());
		    sysDictValueService.update(sysDictValue);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }
	
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysDictValue sysDictValue) {
    	try {
    		if(sysDictValue.getId()!=null) {
    			sysDictValue.setUpdateTime(DateUtil.getNowTime());
    			sysDictValueService.update(sysDictValue);
    		}else {
    			sysDictValue.setCreateTime(DateUtil.getNowTime());
    			sysDictValueService.save(sysDictValue);
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
    	SysDictValue sysDictValue = null;
        try {
	        sysDictValue = sysDictValueService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success(sysDictValue);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(SysDictValue sysDictValue) {
    	try {
    		List<SysDictValue> list = sysDictValueService.getByCondition(sysDictValue);
    		if(list!=null && list.size()>0) {
    			sysDictValue = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
        return Result.success(sysDictValue);
    }

    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
	        PageHelper.startPage(page, size);
	        List<SysDictValue> list = sysDictValueService.findAll();
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
	        SysDictValue sysDictValue = sysDictValueService.findBy(colomnName, colomnVal);
	        return Result.success(sysDictValue);
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
    		
    		Condition condition = new Condition(SysDictValue.class);
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
    		List<SysDictValue> list = sysDictValueService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
    }
    @RequestMapping("getDictByType")
    public Result getDictByType(SysDictValue sysDictValue) {
        try{
        	if(!StringUtil.isNotEmpty(sysDictValue.getTypeCode())) {
        		return Result.error("typeCode,字典类型不能为空：");
        	}
	        List<SysDictValue> values = sysDictValueService.getDictByType(sysDictValue);
	      
	        return Result.success(values);
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error("操作异常",e);
        	return Result.error("内部出错："+e.getMessage());
        }
    }
}
