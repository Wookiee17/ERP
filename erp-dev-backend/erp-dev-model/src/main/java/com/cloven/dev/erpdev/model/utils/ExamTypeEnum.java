package com.cloven.dev.erpdev.model.utils;

import java.util.HashMap;
import java.util.Map;

public enum ExamTypeEnum {
	Term_1("Term 1"), Term_2("Term 2");

	private final String description;

	// Reverse-lookup map for getting a status from a description
	private static final Map<String, ExamTypeEnum> lookup = new HashMap<>();

	static {
		for (ExamTypeEnum d : ExamTypeEnum.values()) {
			lookup.put(d.getDescription(), d);
		}
	}

	private ExamTypeEnum(String description) {
		this.description = description;

	}

	public static Map<String, ExamTypeEnum> getLookup() {
		return lookup;
	}

	public String getDescription() {
		return description;
	}

	public static ExamTypeEnum get(String description) {
		return lookup.get(description);
	}
}
