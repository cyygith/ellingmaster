package com.elling.rent.controller;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elling.common.entity.Result;
import com.elling.common.utils.DateUtil;
import com.elling.common.utils.StringUtil;
import com.elling.common.utils.pdf.Generator;
import com.elling.rent.Constant;
import com.elling.rent.model.RentBill;
import com.elling.rent.service.RentBillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.PageSize;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 *
 * Created by cyy on 2020-09-11 16:16:42.
 */
@RestController
@RequestMapping("/rentBill/")
public class RentBillController {
	private static Logger logger = Logger.getLogger(RentBillController.class);
	
    @Autowired
    RentBillService rentBillService;

    @RequestMapping("add")
    public Result add(@RequestBody RentBill rentBill) {
    	try {
	        rentBillService.save(rentBill);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("delete")
    public Result delete(Integer id) {
    	try {
		    rentBillService.deleteById(id);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }
    
    @RequestMapping("deleteByIds")
    public Result deleteByIds(@RequestBody Map map) {
    	try {
    		String ids = StringUtil.getString(map.get("ids"));
    		rentBillService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody RentBill rentBill) {
    	try {
		    rentBillService.update(rentBill);
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }
    /**
     * 收租，找到上个月的房租
     * 1、属期开始时间为上个属期的结束时间
     * 2、上月度数为上个月的本度数
     * 
     * @param rentBill
     * @return
     */
    @RequestMapping("doRent")
    public Result doRent(RentBill rentBill) {
    	try {
    		
    		List<RentBill> rentBs = rentBillService.getByCondition(rentBill);
    		if(null!=rentBs&&rentBs.size()>0) {
    			RentBill tmpRb = rentBs.get(0);
    			
    			if(Constant.STATUS_USE.equals(tmpRb.getStatus())) {//如果有值,则证明上个月已经交了
    				RentBill rb = new RentBill();
            		BeanUtils.copyProperties(rb, tmpRb);
            		rb.setId(null);
            		rb.setStartTime(tmpRb.getEndTime());
            		rb.setEndTime("");
            		
            		rb.setLastElectric(tmpRb.getCurrElectric());
            		rb.setCurrElectric("");
            		rb.setLastWater(tmpRb.getCurrWater());
            		rb.setCurrWater("");
            		rb.setStatus(Constant.STATUS_NEW);
            		rb.setRemark("");
            		
            		rb.setCreateTime(DateUtil.getNowTime());
            		
            		rentBillService.save(rb);
            		return Result.success(rb);
    			}else {
    				return Result.success(tmpRb);
    			}
    			
    		}else {
    			rentBillService.save(rentBill);
    			RentBill rbNew = new RentBill();
    			rbNew.setId(rentBill.getId());
    			List<RentBill> rbs = rentBillService.getByCondition(rbNew);
        		if(null!=rbs&&rbs.size()>0) rbNew = rbs.get(0);
    			return Result.success(rbNew); 
    		}
    		
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    
    }
    /**
     * 根据开始时间和结束时间保存
     * @param rentBill
     * @return
     */
    @RequestMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RentBill rentBill) {
    	try {
    		if(rentBill.getId()!=null) {
    			rentBillService.update(rentBill);
    		}else {
    			rentBillService.save(rentBill);
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
    	RentBill rentBill = null;
        try {
	        rentBill = rentBillService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success(rentBill);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(RentBill rentBill) {
    	RentBill rMap = null;
    	try {
    		List<RentBill> list = rentBillService.getByCondition(rentBill);
    		if(list!=null && list.size()>0) {
    			rMap = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
        return Result.success(rMap);
    }

    @RequestMapping("list")
    public Result list(RentBill rb,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
        PageInfo pageInfo = null;
        try {
//        	PageHelper.startPage(page, size);
//	        List<RentBill> list = rentBillService.findAll();
        	PageHelper.startPage(page, size);
	        List<RentBill> list = rentBillService.getByCondition(rb);
	        pageInfo = new PageInfo(list);
	    }catch(Exception e) {
    	   e.printStackTrace();
	   	   logger.error("查询错误："+e.getMessage());
	   	   return Result.error("查询错误："+e.getMessage());
       	}
        return Result.success(pageInfo);
    }
    
    @RequestMapping("getByColumn")
    public Result getByColumn(@RequestParam String colomnName, @RequestParam String colomnVal) {
        try{
	        RentBill rentBill = rentBillService.findBy(colomnName, colomnVal);
	        return Result.success(rentBill);
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error(e.getMessage());
        	return Result.error("查询错误："+e.getMessage());
        }
    }
    
    @RequestMapping("getByExample")
    public Result getByExample(@RequestBody Map map) {
    	try {
    		int page = map.get("page")==null?1:(Integer.parseInt(StringUtil.getString(map.get("page"))));
    		int size = map.get("size")==null?10:(Integer.parseInt(StringUtil.getString(map.get("size"))));
    		map.remove("page");
    		map.remove("size");
    		
    		Condition condition = new Condition(RentBill.class);
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
    		List<RentBill> list = rentBillService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
    }
    
    @RequestMapping("getListByGroup")
    public Result getListByGroup(RentBill rentBill) {
    	Map rMap = new HashMap();
    	try {
    		List<Map<String,Object>> list = rentBillService.getListByGroup(rentBill);
    		if(list!=null && list.size()>0) {
    			List tempList = new ArrayList();
    			for(Map m:list) {
    				String groupCode = StringUtil.getString(m.get("groupCode"));
    				String groupName = StringUtil.getString(m.get("groupName"));
    				String houseCode = StringUtil.getString(m.get("houseCode"));
    				if(rMap.get(groupName)==null) {
    					tempList = new ArrayList();
    					tempList.add(m);
    					rMap.put(groupName, tempList);
    				}else {
    					((List)rMap.get(groupName)).add(m);
    				}
    			}
    		}

    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
        return Result.success(rMap);
    }
    
    @RequestMapping("monitorRentEndTime")
    public Result monitorRentEndTime(RentBill rentBill) {
    	Map rMap = new HashMap();
    	try {
    		List<RentBill> list = rentBillService.monitorRentEndTime(rentBill);
    		if(list!=null && list.size()>0) {
    			List tempList = new ArrayList();
    			for(RentBill rb:list) {
    				String groupCode = StringUtil.getString(rb.getGroupCode());
    				String groupName = StringUtil.getString(rb.getGroupName());
    				String houseCode = StringUtil.getString(rb.getHouseCode());
    				if(rMap.get(groupName)==null) {
    					tempList = new ArrayList();
    					tempList.add(rb);
    					rMap.put(groupName, tempList);
    				}else {
    					((List)rMap.get(groupName)).add(rb);
    				}
    			}
    		}

    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
        return Result.success(rMap);
    }
    
    @RequestMapping("getPdf")
    public Result getPdf(RentBill rentBill,HttpServletResponse httpServletResponse) {
    	Map rMap = new HashMap();
    	try {
    		Map<String,Object> dataMap = new HashMap<String,Object>();
    		RentBill rb = rentBillService.findById(rentBill.getId());
    		if(rb!=null) {
    			dataMap.put("rentBill", rb);
        		OutputStream out = httpServletResponse.getOutputStream();
        		Generator.pdfGenerateToResponse("rentReceipt.ftl", dataMap, null, PageSize.A4, "", true, null, out);
        		out.close();
        		System.out.println("生成pdf成功~~~");
    		}else {
    			Result.error("没有可生成的数据");
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("生成错误："+e.getMessage());
    	}
        return Result.success(rMap);
    }
}
