package com.ddframe.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

public class ExceptionHandle {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	public ExceptionResponse handler(Exception e) {
		e.printStackTrace();
		if (e instanceof SQLException) {
			SQLException sqlException = (SQLException) e;
			return ExceptionUtil.error(sqlException.getErrorCode(), sqlException.getMessage());
		} else if (e instanceof DataAccessException) {
			DataAccessException sqlException = (DataAccessException) e;
			return ExceptionUtil.error(-1, sqlException.getCause().getMessage());
		} else {
			logger.info("[系统异常] {}", e);
			return ExceptionUtil.error(-1, e.getMessage());
		}

	}
}
