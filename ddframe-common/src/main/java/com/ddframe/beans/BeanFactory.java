package com.ddframe.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.beans.BeanGenerator;

public class BeanFactory {
	private static final ConcurrentHashMap<String, BaseObject> CACHE = new ConcurrentHashMap<String, BaseObject>();

	public static final BaseObject create(String name, HashMap<String, Class<?>> params) {
		if (!CACHE.containsKey(name)) {// 不存在时，加载Class
			BeanGenerator beanGenerator = new BeanGenerator();
			beanGenerator.setSuperclass(BaseObject.class);
			params.forEach((k, v) -> {
				beanGenerator.addProperty(k, v);
			});
			BaseObject object = (BaseObject) beanGenerator.create();
			object.setObjectName(name);
			CACHE.put(name, object);
		}
		BaseObject baseObject = CACHE.get(name);
		return create(baseObject, baseObject.getClass());
	}

	public static final BaseObject create(BaseObject baseObject, Class<? extends BaseObject> clazz) {
		try {
			BaseObject o = clazz.newInstance();
			o.setObjectName(baseObject.getObjectName());
			return o;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		HashMap<String, Class<?>> params = new HashMap<String, Class<?>>();
		params.put("product", String.class);
		params.put("business_key", String.class);
		params.put("business", String.class);
		params.put("action", String.class);
		params.put("succ_count", Long.class);
		params.put("fail_count", Long.class);
		params.put("succ_time_average", Long.class);
		params.put("fail_time_average", Long.class);
		params.put("status_date", Date.class);
		params.put("create_date", Date.class);
		params.put("users", Long.class);
		params.put("realusers", Long.class);
		BaseObject a = BeanFactory.create("stat_report", params);
		BaseObject b = BeanFactory.create("stat_report", params);
		BaseObject c = BeanFactory.create("stat_report", params);
		BaseObject d = BeanFactory.create("stat_report", params);
		System.out.println(a + ":" + b + ":" + c + ":" + d);
	}
}
