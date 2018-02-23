package com.ddframe.database.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddframe.beans.BaseObject;
import com.ddframe.database.domain.Select;
import com.ddframe.database.domain.Table;
import com.ddframe.database.mapper.SelectMapper;

@Service
public class SelectManagerService {
	private final static Logger logger = LoggerFactory.getLogger(SelectManagerService.class);
	@Autowired
	private SelectMapper<BaseObject, Table> selectMapper;
	@Autowired
	private TableManagerService tableManagerService;

	public List<HashMap<String, Object>> select(Select select) {
		ResultHandler<HashMap<String, Object>> handle = new ResultHandler<HashMap<String, Object>>() {
			@Override
			public void handleResult(ResultContext<? extends HashMap<String, Object>> resultContext) {
				resultContext.getResultObject();
			}
		};
		return selectMapper.listByHandle("select * from stat_report", handle);
	}

	public List<HashMap<String, Object>> selectBySql(Select select) {
		String sql = select.sql();
		logger.debug("Sql: {}", sql);
		return selectMapper.list(sql, select.convertCondition());
	}

	public HashMap<String, Object> select(String schema,String table_name, String id) {
		Table table=tableManagerService.getTable(schema, table_name);
		table.ddl();
		return null;
	}
}
