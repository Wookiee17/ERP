package com.cloven.dev.erpdev.model.utils;

import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {
	Admin("Admin"), Student("Student"), Teacher("Teacher"), Hod("Hod"), Principal("Principal");

	private final String description;

	// Reverse-lookup map for getting a status from a description
	private static final Map<String, UserTypeEnum> lookup = new HashMap<>();

	static {
		for (UserTypeEnum d : UserTypeEnum.values()) {
			lookup.put(d.getDescription(), d);
		}
	}

	private UserTypeEnum(String description) {
		this.description = description;

	}

	public static Map<String, UserTypeEnum> getLookup() {
		return lookup;
	}

	public String getDescription() {
		return description;
	}

	public static UserTypeEnum get(String description) {
		return lookup.get(description);
	}
}
