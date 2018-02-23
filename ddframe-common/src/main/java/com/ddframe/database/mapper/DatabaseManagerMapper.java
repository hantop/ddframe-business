package com.ddframe.database.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ddframe.database.domain.Table;
import com.ddframe.database.domain.TableColumn;
import com.ddframe.database.domain.TableIndex;

@Mapper
public interface DatabaseManagerMapper<Bean, Condition> {
	@Update("${ddl}")
	int execute(@Param("ddl") String ddl);

	@Update("${insert}")
	int insert(@Param("insert") String insert, Bean data);

	@Update("${update}")
	int update(@Param("update") String update, Bean data);

	@Select("${select}")
	HashMap<String, Object> select(@Param("select") String select, Condition condition);

	@Select("${list}")
	List<HashMap<String, Object>> list(@Param("list") String list, Condition condition);

	@Select("select table_schema,table_name,table_collation,table_comment "
			+ " from tables where TABLE_SCHEMA='${schema}' and table_type='BASE TABLE'")
	List<Table> loadTables(@Param("schema") String schema);

	@Select("select table_schema,table_name,table_collation,table_comment "
			+ " from tables where TABLE_SCHEMA='${schema}' and TABLE_NAME='${table_name}' and table_type='BASE TABLE'")
	Table loadTable(@Param("schema") String schema, @Param("table_name") String table_name);

	@Select("select table_name,column_name,COLUMN_COMMENT,ORDINAL_POSITION,COLUMN_DEFAULT,IS_NULLABLE,\r\n"
			+ "column_type,data_type,\r\n"
			+ "case when (data_type='int' or data_type='decimal' or data_type='bigint'  or data_type='tinyint'\r\n"
			+ "  or data_type='smallint' or data_type='float' or data_type='double') \r\n"
			+ "then numeric_precision else character_maximum_length end column_length,\r\n"
			+ "CHARACTER_MAXIMUM_LENGTH,NUMERIC_PRECISION,NUMERIC_SCALE "
			+ "from COLUMNS where TABLE_SCHEMA='${schema}' and table_name='${table_name}'")
	List<TableColumn> loadTableColumns(@Param("schema") String schema, @Param("table_name") String table_name);

	@Select("select table_schema,index_schema,table_name,index_name,non_unique,SEQ_in_index,column_name,index_type "
			+ "from STATISTICS where TABLE_SCHEMA='${schema}' and table_name='${table_name}'")
	List<TableIndex> loadTableIndexs(@Param("schema") String schema, @Param("table_name") String table_name);
}
