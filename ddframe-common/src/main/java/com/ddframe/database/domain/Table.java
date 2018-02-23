package com.ddframe.database.domain;

import java.util.List;

public class Table {
	private String table_schema;
	private String table_name;
	private String table_comment;
	private List<TableColumn> columns;
	private List<TableIndex> tableIndexs;

	public String getTable_schema() {
		return table_schema;
	}

	public void setTable_schema(String table_schema) {
		this.table_schema = table_schema;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_comment() {
		return table_comment;
	}

	public void setTable_comment(String table_comment) {
		this.table_comment = table_comment;
	}

	public List<TableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<TableColumn> columns) {
		this.columns = columns;
	}

	public String ddl() {
		return "";
	}

	public List<TableIndex> getTableIndexs() {
		return tableIndexs;
	}

	public void setTableIndexs(List<TableIndex> tableIndexs) {
		this.tableIndexs = tableIndexs;
	}
}
