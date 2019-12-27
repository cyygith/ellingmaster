package com.elling.tool.generator.run.runImpl;

import com.elling.tool.generator.run.IRunCode;
import com.elling.tool.generator.service.impl.ListAndManGenerator;
import com.elling.tool.model.ToolGenCode;

/**
 * .前端相关文件生成类
 * @author Administrator
 *
 */
public class FrontEndRunImpl implements IRunCode {
	
	/**
	 * .根据表名生成对应的前端文件xxxList.vue、xxxManager.vue两个文件
	 * @param args
	 */
	@Override
	public void runCode(ToolGenCode toolGenCode) throws Exception{
		String[] tableNames = toolGenCode.getRunTable().split(",");
		
		for(String tableName:tableNames) {
			toolGenCode.setTableName(tableName);
			new ListAndManGenerator().genCode(toolGenCode);//生成xxxList.vue和xxxManager.vue的文件
		}
		System.out.println("执行成功");
	}

}
