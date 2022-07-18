package com.cloven.dev.erpdev.model.utils;


import java.util.HashMap;
import java.util.Map;

public enum BatchStatusEnum {

	Not_Started("Not Started"), Active("Active"),Passed("Passed");

	private final String description;

	// Reverse-lookup map for getting a status from a description
	private static final Map<String, BatchStatusEnum> lookup = new HashMap<>();

	static {
		for (BatchStatusEnum d : BatchStatusEnum.values()) {
			lookup.put(d.getDescription(), d);
		}
	}

	private BatchStatusEnum(String description) {
		this.description = description;

	}

	public static Map<String, BatchStatusEnum> getLookup() {
		return lookup;
	}

	public String getDescription() {
		return description;
	}

	public static BatchStatusEnum get(String description) {
		return lookup.get(description);
	}
}
