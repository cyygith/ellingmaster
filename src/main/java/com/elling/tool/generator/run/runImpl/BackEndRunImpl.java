package com.elling.tool.generator.run.runImpl;

import com.elling.tool.generator.run.IRunCode;
import com.elling.tool.generator.service.impl.ControllerGenerator;
import com.elling.tool.generator.service.impl.MapperGenerator;
import com.elling.tool.generator.service.impl.MapperXmlGenerator;
import com.elling.tool.generator.service.impl.ModelAndMapperGenerator;
import com.elling.tool.generator.service.impl.ServiceGenerator;
import com.elling.tool.model.ToolGenCode;

/**
 * .后端相关文件生成类
 * @author Administrator
 *
 */
public class BackEndRunImpl implements IRunCode {
	/**
	 * .根据表名生成对应的后端文件Service、ServiceImpl、Controller、Mapper、entity、xml等六个文件
	 * @param args
	 */
	@Override
	public void runCode(ToolGenCode toolGenCode) {
		String[] tableNames = toolGenCode.getRunTable().split(",");
		
		for(String tableName:tableNames) {
			toolGenCode.setTableName(tableName);
			new ModelAndMapperGenerator().genCode(toolGenCode);//生成model页面相关
			new ServiceGenerator().genCode(toolGenCode);//生成service、serviceImpl页面相关
			new ControllerGenerator().genCode(toolGenCode);//生成controller页面相关
			new MapperGenerator().genCode(toolGenCode);//生成Mapper文件
			new MapperXmlGenerator().genCode(toolGenCode);//生成Mapper.xml文件
			
		}
		System.out.println("执行成功");
	}

}
