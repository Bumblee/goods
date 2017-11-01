package com.model.goodss.domain;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Config implements java.io.Serializable {
	private static final long serialVersionUID = -7869106526495487345L;
	private String property;
	private String value;

	public Config() {
	}

	public Config(String property, String value) {
		this.property = property;
		this.value = value;
	}
	
	private List<String> values;

	public List<String> getValues() {// 分解多选值
		return values;
	}

	public String exist(String value) {// i d e g
		try {
			if (values.contains(value)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception e) {
			return "no";
		}
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = StringUtils.deleteWhitespace(value);

		try {
			this.values = Arrays.asList(value.split(","));
		} catch (Exception e) {
		}
	}
}
