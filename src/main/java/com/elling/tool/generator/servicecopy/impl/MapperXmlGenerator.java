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

public class MapperXmlGenerator extends CodeManager implements ICode{

	@Override
	public void genCode(String tableName, String modelName, String sign) {
		String templatePath = "/src/test/resources/template/backTemplate";
		Configuration cfg = getFreemarkerConfiguration(templatePath);
		String customMapping = "/";
		String baseModel = "/mapper/"+Config.getConf("base.model");
		String modelNameLowerCamel = StringUtils.isNullOrEmpty(modelName) ? CodeUtils.tableNameConvertLowerCamel(tableName) : modelName;
		String modelNameUpperCamel = StringUtils.isNullOrEmpty(modelName) ? CodeUtils.tableNameConvertUpperCamel(tableName) : modelName;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		map.put("sign", sign);
		map.put("modelNameLowerCamel", modelNameLowerCamel);
		map.put("modelNameUpperCamel", modelNameUpperCamel);
		Map<String, Object> data = getInitData(map);
		try {
			// 创建 Mapper.xml 接口
			String servicePath = PROJECT_PATH+Config.getConf("resources.path");
			File serviceFile = new File(servicePath + baseModel + customMapping + modelNameUpperCamel + "Mapper.xml");
			// 查看父级目录是否存在, 不存在则创建
			if (!serviceFile.getParentFile().exists()) {
				serviceFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("mapper-xml.ftl").process(data, new FileWriter(serviceFile));
			logger.info(modelNameUpperCamel + "Mapper.xml 生成成功!");
		} catch (Exception e) {
			throw new RuntimeException("Mapper.xml 生成失败!", e);
		}
	}

	@Override
	public Map<String, Object> getInitData(Map<String, Object> pmap) {
		Map<String, Object> data = new HashMap<>();
		String tableName = pmap.get("tableName")+"";
		String schema = Config.getConf("jdbc.schema");
		data.put("tableName", tableName.toUpperCase());
		data.put("modelNameLowerCamel", pmap.get("modelNameLowerCamel"));
		data.put("modelNameUpperCamel", pmap.get("modelNameUpperCamel"));
		data.put("basePackage", Config.getConf("base.package"));
		
		QueryMysqlTable query = new QueryMysqlTable();
		String sql = "select t.COLUMN_NAME,t.COLUMN_COMMENT,t.COLUMN_KEY,T.DATA_TYPE from information_schema.`COLUMNS` t where t.TABLE_NAME = '"+tableName+"' AND t.TABLE_SCHEMA='"+schema+"'";    //要执行的SQL
		String[] arr = new String[] {null};
		List<Map<String,Object>> list = query.getBySql(sql, null);
		List<TableColEntity> colList = new ArrayList<TableColEntity>();
		TableColEntity col = null;
		for(int i=0,len=list.size();i<len;i++) {
			col = new TableColEntity();
			Map tMap = list.get(i);
			col.setColunmUp(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tMap.get("COLUMN_NAME")+""));
			col.setColunm((tMap.get("COLUMN_NAME")+"").toUpperCase());
			col.setComment(tMap.get("COLUMN_COMMENT")+"");
			col.setColunmKey(tMap.get("COLUMN_KEY")+"");
			
			//设置java的数据类型tinyint\bigint->INTEGER
			String dataType = tMap.get("DATA_TYPE")+"";
			col.setDataType(dataType.toUpperCase());
			
			//设置主键
			if(StringUtil.getString(tMap.get("COLUMN_KEY")).equals("PRI")) {
				data.put("primaryKey", tMap.get("COLUMN_NAME"));
			}
			colList.add(col);
		}
		data.put("colsEntity",colList);
		
		return data;
	}

}
