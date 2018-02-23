package com.ddframe.tools;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;

import com.ddframe.database.domain.Select;
import com.ddframe.database.domain.Table;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class Tempates {
	private static TemplateLoader templateLoader = null;
	private static Template TemplateTable = null;
	private static Template SELECT_TEMPLATE = null;
	private static final String TEMPLATE_TABLE_FILE = "table.ftl";
	private static final String TEMPLATE_SELECT_FILE = "select.ftl";
	static {
		URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
		System.out.println(classpath);
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
		configuration.setDefaultEncoding("utf-8");
		try {
			templateLoader = new FileTemplateLoader(new File(classpath.getPath(), "template"));
			configuration.setTemplateLoader(templateLoader);
			TemplateTable = configuration.getTemplate(TEMPLATE_TABLE_FILE);
			SELECT_TEMPLATE = configuration.getTemplate(TEMPLATE_SELECT_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String ddl(Table table) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("table", table);
		try (StringWriter writer = new StringWriter()) {
			TemplateTable.process(data, writer);
			writer.close();
			return writer.getBuffer().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dml(Select select) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("select", select);
		try (StringWriter writer = new StringWriter()) {
			SELECT_TEMPLATE.process(data, writer);
			writer.close();
			return writer.getBuffer().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
