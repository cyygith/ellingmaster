package com.elling.sys.controller;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elling.common.entity.Result;
import com.elling.common.utils.StringUtil;
import com.elling.sys.model.SysMenu;
import com.elling.sys.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 *
 * Created by cyy on 2019/08/01.
 */
@CrossOrigin
@RestController
@RequestMapping("/sysMenu/")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @RequestMapping("add")
    public Result add(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.success();
    }
    
    @RequestMapping("deleteByIds")
    public Result deleteByIds(@RequestBody Map map) {
    	try {
    		String ids = StringUtil.getString(map.get("ids"));
    		sysMenuService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }
    
    @RequestMapping("delete")
    public Result delete(@RequestParam Integer id) {
	    sysMenuService.deleteById(id);
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody SysMenu sysMenu) {
	    sysMenuService.update(sysMenu);
	    return Result.success();
    }

    @RequestMapping("detail")
    public Result detail(Long id) {
        SysMenu sysMenu = sysMenuService.findById(id);
        return Result.success(sysMenu);
    }

    @RequestMapping("list")
    public PageInfo list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysMenu> list = sysMenuService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    
    @RequestMapping("getByColumn")
    public Result getByColumn(@RequestParam String colomnName, @RequestParam String colomnVal) {
        try{
	        SysMenu sysMenu = sysMenuService.findBy(colomnName, colomnVal);
	        return Result.success(sysMenu);
        }catch(Exception e){
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
    		
    		Condition condition = new Condition(SysMenu.class);
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
    		List<SysMenu> list = sysMenuService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		return Result.error(e.getMessage());
    	}
    }
    
    @RequestMapping("getMenuLevel")
    public Result getMenuLevel(@RequestBody SysMenu sysMenu) {
    	List<SysMenu> list = sysMenuService.getMenuLevel(sysMenu);
    	return Result.success(list);
    }
    
    @RequestMapping("getMenuData")
    public Result getMenuData(@RequestBody Map map) {
    	List list = sysMenuService.getMenuData(map);
    	System.out.println(list);
    	return Result.success(list);
    }
    
    /**
     * .获取所有的菜单信息
     * @param sysMenu
     * @return
     */
    @RequestMapping("getAllMenuData")
    public Result getAllMenuData(@RequestBody SysMenu sysMenu) {
    	Map map = new HashMap();
    	List list = sysMenuService.getAllMenuData(map);
    	System.out.println(list);
    	return Result.success(list);
    }
    
    
}
