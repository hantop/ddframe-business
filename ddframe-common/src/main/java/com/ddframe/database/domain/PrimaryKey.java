package com.ddframe.database.domain;

public class PrimaryKey {
	private String keys;

	public PrimaryKey() {
	}
	public PrimaryKey(String keys) {
		super();
		this.keys = keys;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
}
