package com.elling.tool.generator.servicecopy;


import java.io.File;
import java.io.IOException;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import tk.mybatis.mapper.util.StringUtil;

/**
 * 生成代码基类
 * @author cyy
 * @date 20191017
 */
public class CodeManager {
	
protected static final Logger logger = LoggerFactory.getLogger(CodeManager.class);
	
	private static Configuration configuration = null;
	// 项目在硬盘上的基础路径
	protected static final String PROJECT_PATH = System.getProperty("user.dir");
	
	/**
	 * 获取 Freemarker 模板环境配置
	 * path : 模板的地址，配置在config.properties中也可以，也可以自己输入
	 * @return
	 */
	public Configuration getFreemarkerConfiguration(String path) {
		if (configuration == null) {
			configuration = initFreemarkerConfiguration(path);
		}
		return configuration;
	}
	
	/**
	 * Freemarker 模板环境配置
	 * @return
	 * @throws IOException
	 */
	private Configuration initFreemarkerConfiguration(String path) {
		Configuration cfg = null;
		try {
			path = StringUtil.isEmpty(path)?Config.getConf("template.file.path"):path;//如果有，则传入，如果没有，则使用配置文件中默认配置的模板地址
			cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setDirectoryForTemplateLoading(new File(PROJECT_PATH+path));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		} catch (IOException e) {
			throw new RuntimeException("Freemarker 模板环境初始化异常!", e);
		}
		return cfg;
	}
	/**
	 * Mybatis 代码自动生成基本配置
	 * @return
	 */
	public Context initMybatisGeneratorContext(String sign) {
		Context context = new Context(ModelType.FLAT);
		context.setId("apple");
		context.setTargetRuntime("MyBatis3Simple");
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS, "true");
        
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(Config.getConf("jdbc.url"));
        jdbcConnectionConfiguration.setUserId(Config.getConf("jdbc.username)"));
        jdbcConnectionConfiguration.setPassword(Config.getConf("jdbc.password"));
        jdbcConnectionConfiguration.setDriverClass(Config.getConf("jdbc.driver.class.name"));
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
        
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + Config.getConf("resources.path"));
        sqlMapGeneratorConfiguration.setTargetPackage("mapper."+Config.getConf("base.model"));
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
        
        // 增加 mapper 插件
        addMapperPlugin(context);
        
		return context;
	}
	/**
	 * 增加 Mapper 插件
	 * @param context
	 */
	private void addMapperPlugin(Context context) {
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", Config.getConf("mapper.interface.reference"));
        context.addPluginConfiguration(pluginConfiguration);
	}
	
}
