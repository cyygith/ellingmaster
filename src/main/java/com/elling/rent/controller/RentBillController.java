package com.elling.rent.controller;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import com.elling.common.utils.MoneyUtil;
import com.elling.common.utils.StringUtil;
import com.elling.common.utils.pdf.Generator;
import com.elling.rent.Constant;
import com.elling.rent.model.RentBill;
import com.elling.rent.model.RentHouse;
import com.elling.rent.service.RentBillService;
import com.elling.rent.service.RentHouseService;
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
@RequestMapping("/rentBill/")
public class RentBillController {
	private static Logger logger = Logger.getLogger(RentBillController.class);
	
    @Autowired
    RentBillService rentBillService;
    @Autowired
    RentHouseService rentHouseService;
    @Autowired
    SequenceService sequenceService;
    

    @RequestMapping("add")
    public Result add(@RequestBody RentBill rentBill) {
    	try {
    		rentBill.setCreateTime(DateUtil.getNowTime());
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
    		rentBill.setUpdateTime(DateUtil.getNowTime());
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
     * 	属期的结束时间为下个月的同一时间
     * 
     * 2、上月度数为上个月的本度数
     * 	如果上个月的结束日期度数为空，则取上个月度数的开始时间
     * @param rentBill
     * @return
     */
    @RequestMapping("doRent")
    public Result doRent(RentBill rentBill) {
    	try {
    		
    		List<RentBill> rentBs = rentBillService.getByCondition(rentBill);
    		String cacheType = Constant.CACHETYPE_NEW; //0-新增   1-缓存   2-全新
    		Map<String,Object> parmMap = new HashMap<String,Object>();
    		if(null!=rentBs&&rentBs.size()>0) {
    			RentBill tmpRb = rentBs.get(0);
    			
    			if(Constant.BILL_STATUS_FINISH.equals(tmpRb.getStatus())) {//如果有值,则证明上个月已经交了
    				//更新rentHouse的状态为【出租中】
    				RentHouse rentHouse = new RentHouse();
    				rentHouse.setHouseCode(tmpRb.getHouseCode());
    				rentHouse.setStatus(Constant.HOUSE_STATUS_RENT);//出租中
    				rentHouseService.updateByConditionSelective(rentHouse);
    				
    				RentBill rb = new RentBill();
            		BeanUtils.copyProperties(rb, tmpRb);
            		rb.setId(null);
            		rb.setBillCode(sequenceService.getMaxBusinessValueByType(Constant.BILL_MODULE));
            		rb.setStartTime(tmpRb.getEndTime());
            		rb.setEndTime(DateUtil.getSpecifiedMonthBefore(tmpRb.getEndTime(), -1));
            		
            		rb.setLastElectric(tmpRb.getCurrElectric());
            		if(!StringUtil.isNotEmpty(tmpRb.getCurrElectric()))
            			rb.setLastElectric(tmpRb.getLastElectric());//如果上个月的当月电费为空的情况下，则取上个月的 上月电费（存在几个月一起计算电费的情况）
            		rb.setCurrElectric("");
            		rb.setLastWater(tmpRb.getCurrWater());
            		if(!StringUtil.isNotEmpty(tmpRb.getCurrWater()))
            			rb.setLastWater(tmpRb.getLastWater());//如果上个月的当月水费为空的情况下，则取上个月的 上月水费（存在几个月一起计算水费的情况）
            		rb.setCurrWater("");
            		rb.setStatus(Constant.BILL_STATUS_ING);
            		rb.setRemark(tmpRb.getRemark());
            		
            		rb.setCreateTime(DateUtil.getNowTime());
            		
            		cacheType = Constant.CACHETYPE_NEW;
            		parmMap.put("cacheType", cacheType);
            		rentBillService.save(rb);
            		return Result.success(rb,parmMap);
    			}else {
    				cacheType = Constant.CACHETYPE_CACHE;
    				parmMap.put("cacheType", cacheType);
    				return Result.success(tmpRb,parmMap);
    			}
    			
    		}else {
    			rentBillService.save(rentBill);
    			RentBill rbNew = new RentBill();
    			rbNew.setId(rentBill.getId());
    			List<RentBill> rbs = rentBillService.getByCondition(rbNew);
        		if(null!=rbs&&rbs.size()>0) rbNew = rbs.get(0);
        		cacheType = Constant.CACHETYPE_ADD;
        		parmMap.put("cacheType", cacheType);
    			return Result.success(rbNew,parmMap); 
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
    			rentBill.setUpdateTime(DateUtil.getNowTime());
    			rentBillService.update(rentBill);
    		}else {
    			rentBill.setCreateTime(DateUtil.getNowTime());
    			rentBillService.save(rentBill);
    		}
		    
		}catch(Exception e) {
    		e.printStackTrace();
    		logger.error(e.getMessage());
    		return Result.error("查询错误："+e.getMessage());
    	}
	    return Result.success();
    }
    /**
     * 更新bill的数据，并且设定租房的状态为 3（已出租）
     * @param rentBill
     * @return
     */
    @RequestMapping("updateBillAndSetRenting")
    public Result updateBillAndSetRenting(@RequestBody RentBill rentBill) {
    	try {
    		if(!StringUtil.isNotEmpty(rentBill.getId())) {
    			return Result.error("ID不能为空");
    		}
    		if(!StringUtil.isNotEmpty(rentBill.getHouseCode())) {
    			return Result.error("房屋编号不能为空");
    		}
    		
    		if(rentBill.getId()!=null) {
    			//1、更新rentBill的情况
    			rentBill.setStatus(Constant.BILL_STATUS_FINISH);
    			rentBill.setUpdateTime(DateUtil.getNowTime());
    			rentBillService.update(rentBill);
    			//2、更新rentHouse的状态为【出租中】
				RentHouse rentHouse = new RentHouse();
				rentHouse.setHouseCode(rentBill.getHouseCode());
				rentHouse.setStatus(Constant.HOUSE_STATUS_RENT);//出租中
				rentHouseService.updateByConditionSelective(rentHouse);
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
    		rentBill.setId(rentBill.getId());
    		List<RentBill> rbs = rentBillService.getByCondition(rentBill);
    		if(rbs!=null&&rbs.size()>0) {
    			RentBill rb = rbs.get(0);
    			
    			//收据编号
    			String CodeNum = DateUtil.getDateTime();
    			//开票时间
    			String startTime = rb.getStartTime();
    			if(StringUtil.isNotEmpty(startTime)) {
    				startTime = startTime.substring(0, 4)+"年"+startTime.substring(5,7)+"月"+startTime.substring(8,10)+"日";
    			}
    			
    			//电费
    			String currElect = rb.getCurrElectric();
    			String lastElect = rb.getLastElectric();
    			float eleFee = 0;
    			if(StringUtil.isNotEmpty(currElect)&&StringUtil.isNotEmpty(lastElect)) {
    				eleFee = Float.parseFloat(currElect)-Float.parseFloat(lastElect);
    			}
    			//水费
    			String currWater = rb.getCurrWater();
    			String lastWater = rb.getLastWater();
    			String waterPayType = rb.getWaterPayType();//水费付款方式
    			String waterPayTypeName = "";//水费付款方式
    			Long rentNum = rb.getRentNum();//租住人数
    			float waterFee = 0;
    			if("1".equals(waterPayType)) {//按吨支付
    				waterPayTypeName = "5元/吨";
    				if(StringUtil.isNotEmpty(currWater)&&StringUtil.isNotEmpty(lastWater)) {
        				waterFee = Float.parseFloat(currWater)-Float.parseFloat(lastWater);
        			}
    			}else {
    				waterPayTypeName = "10元/人";
    				waterFee = rentNum * 10L;
    			}
    			
    			//小写
    			String yuan = "";
    			String shi = "";
    			String bai = "";
    			String qian = "";
    			String wan = "";
    			
    			
    			//大写总值
    			String bigSum = "";
    			String sum = rb.getSum();
    			if(StringUtil.isNotEmpty(sum)) {
    				Double dsum = Double.parseDouble(sum);
    				bigSum = MoneyUtil.convert(dsum);
    				
    				String iSum = sum.indexOf(".")!=-1?sum.substring(0,sum.indexOf(".")):sum;
    				int len = iSum.length();
    				 yuan = len>=1?iSum.substring(len -1):"";
    				 shi = len>=2?iSum.substring(len -2,len-1):"";
    				 bai = len>=3?iSum.substring(len -3,len-2):"";
    				 qian = len>=4?iSum.substring(len -4,len-3):"";
    				 wan = len>=5?iSum.substring(len -5,len-4):"";
    			}
    			
    			
    			dataMap.put("CodeNum", CodeNum);
    			dataMap.put("waterPayTypeName", waterPayTypeName);
    			dataMap.put("waterFee", (waterFee==0?"":waterFee));
    			dataMap.put("eleFee", eleFee);
    			dataMap.put("rentBill", rb);
    			dataMap.put("startTime", startTime);
    			dataMap.put("bigSum", bigSum);
    			
    			dataMap.put("yuan", yuan);
    			dataMap.put("shi", shi);
    			dataMap.put("bai", bai);
    			dataMap.put("qian", qian);
    			dataMap.put("wan", wan);
    			
        		OutputStream out = httpServletResponse.getOutputStream();
        		httpServletResponse.setContentType("application/force-download");
        		httpServletResponse.setHeader("Content-Disposition", "attachment;filename*=UTF-8''"+URLEncoder.encode("房屋收据.pdf","UTF-8"));
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
