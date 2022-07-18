package com.cloven.dev.erpdev.model.utils;


import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {

	Not_Started("Not Started"), Active("Active"),InActive("In Active"),Disabled("Disabled"),;

	private final String description;

	// Reverse-lookup map for getting a status from a description
	private static final Map<String, StatusEnum> lookup = new HashMap<>();

	static {
		for (StatusEnum d : StatusEnum.values()) {
			lookup.put(d.getDescription(), d);
		}
	}

	private StatusEnum(String description) {
		this.description = description;

	}

	public static Map<String, StatusEnum> getLookup() {
		return lookup;
	}

	public String getDescription() {
		return description;
	}

	public static StatusEnum get(String description) {
		return lookup.get(description);
	}
}
