package com.ddframe.database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddframe.database.datasource.TargetDataSource;
import com.ddframe.database.domain.DatabaseType;
import com.ddframe.database.domain.Table;
import com.ddframe.database.mapper.DatabaseManagerMapper;
import com.ddframe.factory.Factory;

@Service
public class DatabaseManagerService {
	private static final String ALL_TABLES = "ALL";
	@Autowired
	private DatabaseManagerMapper<?, ?> databaseManagerMapper;

	public DatabaseManagerMapper<?, ?> getDatabaseManagerMapper() {
		return databaseManagerMapper;
	}

	public void setDatabaseManagerMapper(DatabaseManagerMapper<?, ?> databaseManagerMapper) {
		this.databaseManagerMapper = databaseManagerMapper;
	}

	@TargetDataSource(name = DatabaseType.SCHEMA)
	public List<Table> loadSchema(String schema, String table_name) {
		List<Table> tables = null;
		if (ALL_TABLES.equals(table_name)) {
			tables = this.databaseManagerMapper.loadTables(schema);
		} else {
			tables = new ArrayList<Table>(1);
			tables.add(this.databaseManagerMapper.loadTable(schema, table_name));
		}
		tables.forEach((table) -> {
			table.setColumns(this.databaseManagerMapper.loadTableColumns(schema, table.getTable_name()));
			table.setTableIndexs(this.databaseManagerMapper.loadTableIndexs(schema, table.getTable_name()));
		});

		return tables;
	}

	@TargetDataSource(name = DatabaseType.SCHEMA)
	public Table loadTable(String schema, String table_name) {
		Table table = null;
		String key = schema + table_name;
		if (Factory.exists(key)) {
			table = Factory.get(key);
		} else {
			table = this.databaseManagerMapper.loadTable(schema, table_name);
			if (table != null) {
				table.setColumns(this.databaseManagerMapper.loadTableColumns(schema, table.getTable_name()));
				table.setTableIndexs(this.databaseManagerMapper.loadTableIndexs(schema, table.getTable_name()));
				Factory.put(key, table);
			}
		}
		return table;
	}
}
