package com.ddframe.database.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ddframe.database.datasource.DatabaseContextHolder;
import com.ddframe.database.datasource.DynamicDataSource;
import com.ddframe.database.domain.DatabaseType;

@Configuration
public class DataSourceConfiguration {
	private static final String PASSWORD = ".password";
	private static final String USERNAME = ".username";
	private static final String URL = ".url";
	private static final String DRIVER_CLASS_NAME = ".driver-class-name";
	private static final String DEFAULT_DB_PREFIX = "default";
	private static final String SCHEMA_DB_PREFIX = "schema";
	@Autowired
	private Environment env;

	/**
	 * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
	 */
	@Bean
	@Primary
	public DataSource df() throws Exception {
		return DruidDataSourceFactory.createDataSource(getDatabaseProperties(DEFAULT_DB_PREFIX));
	}

	@Bean
	public DataSource schema() throws Exception {
		return DruidDataSourceFactory.createDataSource(getDatabaseProperties(SCHEMA_DB_PREFIX));
	}

	@Bean
	public DataSource db1() throws Exception {
		return DruidDataSourceFactory.createDataSource(getDatabaseProperties("db1"));
	}

	@Bean
	public DataSource db2() throws Exception {
		return DruidDataSourceFactory.createDataSource(getDatabaseProperties("db2"));
	}

	@Bean
	public DataSource db3() throws Exception {
		return DruidDataSourceFactory.createDataSource(getDatabaseProperties("db4"));
	}

	private Properties getDatabaseProperties(String prefix) {
		// 去默认链接池
		if (!DEFAULT_DB_PREFIX.equals(prefix) && env.getProperty(prefix + DRIVER_CLASS_NAME) == null) {
			return getDatabaseProperties(DEFAULT_DB_PREFIX);
		}
		if (env.getProperty(prefix + DRIVER_CLASS_NAME) == null) {
			throw new RuntimeException("No config parameter of database.");
		}
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty(prefix + DRIVER_CLASS_NAME));
		props.put("url", env.getProperty(prefix + URL));
		props.put("username", env.getProperty(prefix + USERNAME));
		props.put("password", env.getProperty(prefix + PASSWORD));
		return props;
	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	public DynamicDataSource dataSource(@Qualifier("df") DataSource df, @Qualifier("schema") DataSource schema, @Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2,
			@Qualifier("db3") DataSource db3) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DatabaseType.DEFAULT, df);
		targetDataSources.put(DatabaseType.SCHEMA, schema);
		targetDataSources.put(DatabaseType.DB1, db1);
		targetDataSources.put(DatabaseType.DB2, db2);
		targetDataSources.put(DatabaseType.DB3, db3);
		DatabaseContextHolder.add(targetDataSources.keySet());
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(df);// 默认的datasource设置为myTestDbDataSource

		return dataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
		// 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		//fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
		//fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//

		return fb.getObject();
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}
}
