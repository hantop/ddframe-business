package com.ddframe.database.domain;

public class Condition {
	private String column_name;
	private String column_type;
	private Select.Type type = Select.Type.EQUEAL;
	private String value;
	private String start_value;
	private String end_value;

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Select.Type getType() {
		return type;
	}

	public void setType(Select.Type type) {
		this.type = type;
	}

	public String getColumn_type() {
		return column_type;
	}

	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}

	public String getStart_value() {
		return start_value;
	}

	public void setStart_value(String start_value) {
		this.start_value = start_value;
	}

	public String getEnd_value() {
		return end_value;
	}

	public void setEnd_value(String end_value) {
		this.end_value = end_value;
	}

	public String sql() {
		StringBuffer sb = new StringBuffer();
		switch (type) {
		case EQUEAL: {
			sb.append(column_name).append(" = #{conditions.").append(column_name).append("}");
			break;
		}
		case LIKE: {
			sb.append(column_name).append(" like #{conditions.").append(column_name).append("}");
			break;
		}
		case IN: {
			sb.append(column_name).append(" in( ").append(this.value).append(")");
			break;
		}
		case NOT_IN: {
			sb.append(column_name).append(" not in( ").append(this.value).append(")");
			break;
		}
		case NOT_EQUEAL: {
			sb.append(column_name).append(" <> #{conditions.").append(column_name).append("}");
			break;
		}
		case BETWEEN:
			sb.append(column_name).append(" between #{conditions.start_value} and #{conditions.end_value}");
			break;
		case LT:
			sb.append(column_name).append(" > #{conditions.").append(column_name).append("}");
			break;
		case LT_AND_EQUEAL:
			sb.append(column_name).append(">= #{conditions.").append(column_name).append("}");
			break;
		case RT:
			sb.append(column_name).append("< #{conditions.").append(column_name).append("}");
			break;
		case RT_AND_EQUEAL:
			sb.append(column_name).append("<= #{conditions.").append(column_name).append("}");
			break;
		case DATE_BETWEEN:
			break;
		default:
			break;
		}
		return sb.toString();
	}
}
