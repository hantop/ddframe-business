package com.ddframe.database.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ddframe.database.domain.DatabaseType;

public class DatabaseContextHolder {
	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();
	private static List<Object> dataSourceIds = new ArrayList<>();

	public static void set(DatabaseType type) {
		contextHolder.set(type);
	}

	public static final DatabaseType get() {
		return contextHolder.get();
	}

	public static final void clear() {
		contextHolder.remove();
	}

	public static final void add(DatabaseType type) {
		dataSourceIds.add(type);
	}

	public static final boolean contains(DatabaseType type) {
		return dataSourceIds.contains(type);
	}

	public static void add(Set<Object> keySet) {
		dataSourceIds.addAll(keySet);
	}
}
