package com.elling.sys.controller;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elling.common.entity.Result;
import com.elling.common.utils.DateUtil;
import com.elling.common.utils.StringUtil;
import com.elling.sys.model.SysDictType;
import com.elling.sys.model.SysDictValue;
import com.elling.sys.service.SysDictTypeService;
import com.elling.sys.service.SysDictValueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 *
 * Created by cyy on 2020-10-29 09:58:23.
 */
@RestController
@RequestMapping("/sysDictType/")
public class SysDictTypeController {
	private static Logger logger = Logger.getLogger(SysDictTypeController.class);
	
    @Autowired
    SysDictTypeService sysDictTypeService;
    @Autowired
    SysDictValueService sysDictValueService;

    @RequestMapping("add")
    public Result add(@RequestBody SysDictType sysDictType) {
    	try {
    		sysDictType.setCreateTime(DateUtil.getNowTime());
	        sysDictTypeService.save(sysDictType);
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
		    sysDictTypeService.deleteById(id);
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
    		sysDictTypeService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody SysDictType sysDictType) {
    	try {
    		sysDictType.setUpdateTime(DateUtil.getNowTime());
		    sysDictTypeService.update(sysDictType);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success();
    }
	
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysDictType sysDictType) {
    	try {
    		if(sysDictType.getId()!=null) {
    			sysDictType.setUpdateTime(DateUtil.getNowTime());
    			sysDictTypeService.update(sysDictType);
    		}else {
    			sysDictType.setCreateTime(DateUtil.getNowTime());
    			sysDictTypeService.save(sysDictType);
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
    	SysDictType sysDictType = null;
        try {
	        sysDictType = sysDictTypeService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
	    return Result.success(sysDictType);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(SysDictType sysDictType) {
    	try {
    		List<SysDictType> list = sysDictTypeService.getByCondition(sysDictType);
    		if(list!=null && list.size()>0) {
    			sysDictType = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
        return Result.success(sysDictType);
    }

    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
	        PageHelper.startPage(page, size);
	        List<SysDictType> list = sysDictTypeService.findAll();
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
	        SysDictType sysDictType = sysDictTypeService.findBy(colomnName, colomnVal);
	        return Result.success(sysDictType);
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
    		
    		Condition condition = new Condition(SysDictType.class);
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
    		List<SysDictType> list = sysDictTypeService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error("操作异常",e);
    		return Result.error("内部出错："+e.getMessage());
    	}
    }
    
}
