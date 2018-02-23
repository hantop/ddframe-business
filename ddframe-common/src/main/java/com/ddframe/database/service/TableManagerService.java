package com.ddframe.database.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddframe.database.domain.Table;
import com.ddframe.database.mapper.DatabaseManagerMapper;
import com.ddframe.tools.Tempates;

@Service
public class TableManagerService {
	@Autowired
	private DatabaseManagerMapper<Table, Table> databaseManagerMapper;
	@Autowired
	private DatabaseManagerService databaseManagerService;

	public int create(Table table) {
		String ddl = Tempates.ddl(table);
		System.out.println(ddl);
		ddl = ddl.replaceAll("\r\n", "").replaceAll("\n", "").replaceAll("\r", "").trim();
		String[] sqls = ddl.split(";");
		int result = 0;
		for (int i = 0; i < sqls.length; i++) {
			result += databaseManagerMapper.execute(sqls[i]);
		}
		return result;
	}

	public List<HashMap<String, Object>> list(Table table) {
		return databaseManagerMapper.list("select * from stat_report", null);
	}

	public Table getTable(String schema, String table_name) {
		return databaseManagerService.loadTable(schema, table_name);
	}
}
