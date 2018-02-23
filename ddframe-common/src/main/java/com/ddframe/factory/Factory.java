package com.ddframe.factory;

import java.util.HashMap;

import com.ddframe.database.domain.Table;

public final class Factory {
	private static final HashMap<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();
	private static final HashMap<String, Table> TABLE_SCHEMA = new HashMap<String, Table>();

	public static void put(Class<?> objClass, Object obj) {
		beanMap.put(objClass, obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> objClass) {
		return (T) beanMap.get(objClass);
	}

	public static void put(String name, Table table) {
		TABLE_SCHEMA.put(name, table);
	}

	public static final Table get(String name) {
		return TABLE_SCHEMA.get(name);
	}

	public static final boolean exists(String name) {
		return TABLE_SCHEMA.containsKey(name);
	}
}
