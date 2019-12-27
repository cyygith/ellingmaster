package com.elling.tool.generator.servicecopy.impl;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elling.common.utils.CodeUtils;
import com.elling.common.utils.StringUtil;
import com.elling.common.utils.StringUtils;
import com.elling.tool.database.QueryMysqlTable;
import com.elling.tool.generator.servicecopy.CodeManager;
import com.elling.tool.generator.servicecopy.Config;
import com.elling.tool.generator.servicecopy.ICode;
import com.elling.tool.model.TableColEntity;
import com.google.common.base.CaseFormat;

import freemarker.template.Configuration;

public class ListAndManGenerator extends CodeManager implements ICode{

	@Override
	public void genCode(String tableName, String modelName, String sign) {
		String templatePath = "/src/test/resources/template/frontTemplate";
		Configuration cfg = getFreemarkerConfiguration(templatePath);
		String customMapping = "/" + sign + "/";
		String modelNameLowerCamel = StringUtils.isNullOrEmpty(modelName) ? CodeUtils.tableNameConvertLowerCamel(tableName) : modelName;
		String PAGE_PATH = Config.getConf("page.path");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		map.put("sign", sign);
		map.put("modelNameLowerCamel", modelNameLowerCamel);
		Map<String, Object> data = getInitData(map);
		try {
			// 创建 list 页面
			File listFile = new File(PROJECT_PATH + PAGE_PATH + customMapping + modelNameLowerCamel + "List.vue");
			// 查看父级目录是否存在, 不存在则创建
			if (!listFile.getParentFile().exists()) {
				listFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("List.ftl").process(data, new FileWriter(listFile));
			logger.info (modelNameLowerCamel+ "List.vue 生成成功!");
			
			// 创建 Manager 页面
			File ManagerFile = new File(PROJECT_PATH + PAGE_PATH + customMapping + modelNameLowerCamel + "Manager.vue");
			// 查看父级目录是否存在, 不存在则创建
			if (!ManagerFile.getParentFile().exists()) {
				ManagerFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("manager.ftl").process(data, new FileWriter(ManagerFile));
			logger.info (modelNameLowerCamel+ "manager.vue 生成成功!");
		} catch (Exception e) {
			throw new RuntimeException("vue 生成失败!", e);
		}
	}

	@Override
	public Map<String, Object> getInitData(Map<String, Object> pmap) {
		Map<String, Object> data = new HashMap<>();
		String tableName = pmap.get("tableName")+"";
		String schema = Config.getConf("jdbc.schema");
		data.put("sign", pmap.get("sign"));
		data.put("modelNameLowerCamel", pmap.get("modelNameLowerCamel"));
		data.put("modelNameMidCamel",CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, tableName.toLowerCase()));
		data.put("base_model",Config.getConf("base.model"));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		map.put("url", Config.getConf("jdbc.url"));
		map.put("username", Config.getConf("jdbc.username"));
		map.put("passwd", Config.getConf("jdbc.password"));
		map.put("schema", schema);
		
		QueryMysqlTable query = new QueryMysqlTable();
		String sql = "select t.COLUMN_NAME,t.COLUMN_COMMENT,t.COLUMN_KEY,T.DATA_TYPE from information_schema.`COLUMNS` t where t.TABLE_NAME = '"+tableName+"' AND t.TABLE_SCHEMA='"+schema+"'";    //要执行的SQL
		String[] arr = new String[] {null};
		List<Map<String,Object>> list = query.getBySql(sql, null);
		List<TableColEntity> colList = new ArrayList<TableColEntity>();
		TableColEntity col = null;
		for(int i=0,len=list.size();i<len;i++) {
			col = new TableColEntity();
			Map tMap = list.get(i);
			col.setColunm(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tMap.get("COLUMN_NAME")+""));
			col.setComment(tMap.get("COLUMN_COMMENT")+"");
			col.setColunmKey(tMap.get("COLUMN_KEY")+"");
			col.setDataType(tMap.get("DATA_TYPE")+"");
			if(StringUtil.getString(tMap.get("COLUMN_KEY")).equals("PRI")) {
				data.put("primaryKey", tMap.get("COLUMN_NAME"));
			}
			/**
        	 * .设置处理类型,如果包含time的话则显示time，
        	 * .如果是status,则显示select类型，以后可以规范一下，下拉框如何设置，这里手动写
        	 * .如果是...可以设置为radio类型
        	 * .其他的默认为text类型
        	 */
			String colunm = tMap.get("COLUMN_NAME")+"";
        	if(colunm.toLowerCase().contains("time")) {
        		col.setDealType("time");
        	}else if(colunm.toLowerCase().contains("status")) {
        		col.setDealType("select");
        	}else {
        		col.setDealType("text");
        	}
			
			colList.add(col);
		}
		data.put("colsEntity",colList);
		
		return data;
	}

}
