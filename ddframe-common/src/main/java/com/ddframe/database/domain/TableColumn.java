package com.ddframe.database.domain;

/**
 * table_name,column_name,column_comment,ordinal_position,column_default,is_nullable
 * column_type,data_type,character_maximum_length,numeric_precision,numeric_scale
 */
public class TableColumn extends Table {
	private String column_name;
	// column type contains column length info
	private String column_type;
	// data type just data type
	private String data_type;
	private String column_comment;
	// position of table columns
	private int ordinal_position;
	private String column_default;
	private long column_length;
	// 字符型长度
	private long character_maximum_length;
	// 数字型长度
	private long numeric_precision;
	// 字段精度（数字型的时候才有）
	private long numeric_scale;
	private boolean is_nullable;

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getColumn_type() {
		return column_type;
	}

	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getColumn_comment() {
		return column_comment;
	}

	public void setColumn_comment(String column_comment) {
		this.column_comment = column_comment;
	}

	public int getOrdinal_position() {
		return ordinal_position;
	}

	public void setOrdinal_position(int ordinal_position) {
		this.ordinal_position = ordinal_position;
	}

	public String getColumn_default() {
		return column_default;
	}

	public void setColumn_default(String column_default) {
		this.column_default = column_default;
	}

	public long getColumn_length() {
		return column_length;
	}

	public void setColumn_length(long column_length) {
		this.column_length = column_length;
	}

	public long getCharacter_maximum_length() {
		return character_maximum_length;
	}

	public void setCharacter_maximum_length(long character_maximum_length) {
		this.character_maximum_length = character_maximum_length;
	}

	public long getNumeric_precision() {
		return numeric_precision;
	}

	public void setNumeric_precision(long numeric_precision) {
		this.numeric_precision = numeric_precision;
	}

	public long getNumeric_scale() {
		return numeric_scale;
	}

	public void setNumeric_scale(long numeric_scale) {
		this.numeric_scale = numeric_scale;
	}

	public boolean isIs_nullable() {
		return is_nullable;
	}

	public void setIs_nullable(boolean is_nullable) {
		this.is_nullable = is_nullable;
	}
}
