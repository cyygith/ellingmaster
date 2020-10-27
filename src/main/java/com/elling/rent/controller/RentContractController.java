package com.elling.rent.controller;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.elling.rent.model.RentContract;
import com.elling.rent.service.RentContractService;
import com.elling.sys.service.SequenceService;
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
@RequestMapping("/rentContract/")
public class RentContractController {
	private static Logger logger = Logger.getLogger(RentContractController.class);
	
    @Autowired
    RentContractService rentContractService;
    @Autowired
    SequenceService sequenceService;

    @RequestMapping("add")
    public Result add(@RequestBody RentContract rentContract) {
    	try {
    		rentContract.setCreateTime(DateUtil.getNowTime());
    		rentContract.setContractCode(sequenceService.getMaxBusinessValueByType(Constant.CONTRACT_MODULE));
	        rentContractService.save(rentContract);
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
		    rentContractService.deleteById(id);
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
    		rentContractService.deleteByIds(ids);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success();
    }

    @RequestMapping("update")
    public Result update(@RequestBody RentContract rentContract) {
    	try {
    		rentContract.setUpdateTime(DateUtil.getNowTime());
		    rentContractService.update(rentContract);
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
    public Result saveOrUpdate(@RequestBody RentContract rentContract) {
    	try {
    		if(rentContract.getId()!=null) {
    			rentContract.setUpdateTime(DateUtil.getNowTime());
    			rentContractService.update(rentContract);
    		}else {
    			rentContract.setCreateTime(DateUtil.getNowTime());
    			rentContract.setContractCode(sequenceService.getMaxBusinessValueByType(Constant.CONTRACT_MODULE));
    			rentContractService.save(rentContract);
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
    	RentContract rentContract = null;
        try {
	        rentContract = rentContractService.findById(id);
	    }catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
	    return Result.success(rentContract);
    }
    
    @RequestMapping("getByCondition")
    public Result getByCondition(RentContract rentContract) {
    	RentContract rc = new RentContract();
    	try {
    		List<RentContract> list = rentContractService.getByCondition(rentContract);
    		if(list!=null && list.size()>0) {
    			rc = list.get(0);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
        return Result.success(rc);
    }

    @RequestMapping("list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageInfo pageInfo = null;
        try {
	        PageHelper.startPage(page, size);
	        List<RentContract> list = rentContractService.findAll();
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
	        RentContract rentContract = rentContractService.findBy(colomnName, colomnVal);
	        return Result.success(rentContract);
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
    		
    		Condition condition = new Condition(RentContract.class);
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
    		List<RentContract> list = rentContractService.findByCondition(condition);
            PageInfo pageInfo = new PageInfo(list);
    		return Result.success(pageInfo);
    	}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error(e.getMessage());
    	}
    }
    
    @RequestMapping("getDepositPdf")
    public Result getDepositPdf(RentContract rentContract,HttpServletResponse httpServletResponse) {
    	Map rMap = new HashMap();
    	try {
    		Map<String,Object> dataMap = new HashMap<String,Object>();
    		rentContract.setId(rentContract.getId());
    		List<RentContract> rcs = rentContractService.getByCondition(rentContract);
    		if(rcs!=null&&rcs.size()>0) {
    			RentContract rc = rcs.get(0);
    			
    			//收据编号
    			String CodeNum = DateUtil.getDateTime();
    			//开票时间
    			String startTime = rc.getEndTime();
    			if(StringUtil.isNotEmpty(startTime)) {
    				startTime = startTime.substring(0, 4)+"年"+startTime.substring(5,7)+"月"+startTime.substring(8,10)+"日";
    			}
    			
    			dataMap.put("rentContract", rc);
    			
        		OutputStream out = httpServletResponse.getOutputStream();
				Generator.pdfGenerateToResponse("rentDeposit.ftl", dataMap, null, PageSize.A4, "", true, null, out);
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
