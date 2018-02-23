package com.ddframe.database.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class Select extends SQL {
	private String table_name;
	private String columns;
	private String condition;// 特殊场景使用
	private List<Condition> conditions;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public enum Type {
		EQUEAL, NOT_EQUEAL, LIKE, BETWEEN, DATE_BETWEEN, LT, RT, LT_AND_EQUEAL, RT_AND_EQUEAL, IN, NOT_IN
	}

	public String sql() {
		SELECT(this.getColumns());
		FROM(this.getTable_name());
		parseCondition();
		return super.toString();
	}

	private void parseCondition() {
		if (conditions == null || conditions.isEmpty()) {
			return;
		}
		if (StringUtils.isNotEmpty(condition)) {
			WHERE(condition);
		}
		conditions.forEach((condition) -> {
			WHERE(condition.sql());
		});
	}

	public void addCondition(Condition condition) {
		if (conditions == null) {
			conditions = new ArrayList<Condition>();
		}
		conditions.add(condition);
	}

	public HashMap<String, Object> convertCondition() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		conditions.forEach((condition) -> {
			switch (condition.getType()) {
			case BETWEEN: {
				parameters.put("start_value", condition.getStart_value());
				parameters.put("end_value", condition.getEnd_value());
				break;
			}
			case IN: {
				break;
			}
			case NOT_IN: {
				break;
			}
			case NOT_EQUEAL:
			case EQUEAL:
			case LT:
			case LT_AND_EQUEAL:
			case RT:
			case RT_AND_EQUEAL:
			case DATE_BETWEEN:
			default:
				parameters.put(condition.getColumn_name(), condition.getValue());
				break;
			}
		});
		return parameters;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}
