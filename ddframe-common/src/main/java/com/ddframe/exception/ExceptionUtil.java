package com.ddframe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtil {
	private final static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

	public static ExceptionResponse error(int errorCode, String message) {
		logger.error(message);
		return new ExceptionResponse(errorCode, message);
	}
}
