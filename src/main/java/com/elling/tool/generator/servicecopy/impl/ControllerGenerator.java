package com.elling.tool.generator.servicecopy.impl;


import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.elling.common.utils.CodeUtils;
import com.elling.common.utils.DateUtil;
import com.elling.common.utils.StringUtils;
import com.elling.tool.generator.servicecopy.CodeManager;
import com.elling.tool.generator.servicecopy.Config;
import com.elling.tool.generator.servicecopy.ICode;
import com.google.common.base.CaseFormat;

import freemarker.template.Configuration;

public class ControllerGenerator extends CodeManager implements ICode {

	@Override
	public void genCode(String tableName, String modelName, String sign) {
		Configuration cfg = getFreemarkerConfiguration(null);
		String customMapping = "/";
		String modelNameUpperCamel = StringUtils.isNullOrEmpty(modelName) ? CodeUtils.tableNameConvertUpperCamel(tableName) : modelName;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		map.put("modelName", modelName);
		map.put("sign", sign);
		map.put("modelNameUpperCamel", modelNameUpperCamel);
		Map<String, Object> data = getInitData(map); 
		try {
			String controllerPath = PROJECT_PATH +Config.getConf("java.path")+CodeUtils.packageConvertPath(Config.getConf("controller.package")+"");
			File controllerFile = new File(controllerPath + customMapping
						 + modelNameUpperCamel + "Controller.java");
	        if (!controllerFile.getParentFile().exists()) {
	        	controllerFile.getParentFile().mkdirs();
	        }
	        cfg.getTemplate("controller.ftl").process(data, new FileWriter(controllerFile));
			logger.info(modelNameUpperCamel + "Controller.java 生成成功!");
		} catch (Exception e) {
			throw new RuntimeException("Controller 生成失败!", e);
		}
		
	}

	@Override
	public Map<String, Object> getInitData(Map<String, Object> map) {
		Map<String, Object> data = new HashMap<>();
		data.put("date", DateUtil.getNowTime());
        data.put("author", Config.getConf("author"));
        data.put("sign", map.get("sign"));
        data.put("baseRequestMapping", StringUtils.toLowerCaseFirstOne(map.get("modelNameUpperCamel")+""));
        data.put("modelNameUpperCamel", map.get("modelNameUpperCamel"));
        data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, map.get("modelNameUpperCamel")+""));
        data.put("basePackage", Config.getConf("base.package"));
		
		return data;
	}

}
