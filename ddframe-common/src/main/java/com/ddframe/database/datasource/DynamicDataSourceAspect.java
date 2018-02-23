package com.ddframe.database.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ddframe.database.domain.DatabaseType;

@Aspect
@Component
@Order(-1) // 保证该AOP在@Transactional之前执行
public class DynamicDataSourceAspect {
	private final static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

	@Pointcut("execution( * org.ruiyun.business.*.mapper.*.*(..))")
	public void daoAspect() {
	}

	@Before("daoAspect()")
	public void switchDataSource(JoinPoint point) {
		System.out.println(String.format("Switch DataSource to %s in Method %s", DatabaseContextHolder.get(),
				point.getSignature()));
	}
	
	@Before("@annotation(ds)")
	public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
		DatabaseType name = ds.name();
		if (!DatabaseContextHolder.contains(name)) {
			logger.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
		} else {
			logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
			DatabaseContextHolder.set(ds.name());
		}
	}

	@After("@annotation(ds)")
	public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
		logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
		DatabaseContextHolder.clear();
	}
}
