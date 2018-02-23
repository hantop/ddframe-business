package com.ddframe.database.domain;

/**
 * table_schema,index_schema,table_name,index_name,non_unique,SEQ_in_index,column_name,index_type
 */
public class TableIndex extends Table {
	private String index_schema;
	private String table_name;
	private String index_name;
	private int non_unique;
	private int seq_in_index;
	private String column_name;
	private String index_type;// HASH,BTREE

	public TableIndex() {
	}

	public TableIndex(String table_name, String column_name) {
		this.table_name = table_name;
		this.column_name = column_name;
	}

	public TableIndex(String table_name, String column_name, String index_type) {
		this.table_name = table_name;
		this.column_name = column_name;
		this.index_type = index_type;
	}

	public String getIndex_schema() {
		return index_schema;
	}

	public void setIndex_schema(String index_schema) {
		this.index_schema = index_schema;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getIndex_name() {
		return index_name;
	}

	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}

	

	public int getNon_unique() {
		return non_unique;
	}

	public void setNon_unique(int non_unique) {
		this.non_unique = non_unique;
	}

	public int getSeq_in_index() {
		return seq_in_index;
	}

	public void setSeq_in_index(int seq_in_index) {
		this.seq_in_index = seq_in_index;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getIndex_type() {
		return index_type;
	}

	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}
}
