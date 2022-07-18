package com.cloven.dev.erpdev.model.utils;

import java.util.HashMap;
import java.util.Map;

public enum FeeTypeCategoryEnum {

	Student_Fee("Student Fee"), Examination_Fee("Examination Fee"), Quartely_Fee("Quartely Fee"),
	Admission_Fee("Admission Fee"), Library_Fee("Library Fee"), Security("Secruity (Refundable)"), Fine("Fine"),
	Parking_Fee("Parking Fee");

	private final String description;

	// Reverse-lookup map for getting a status from a description
	private static final Map<String, FeeTypeCategoryEnum> lookup = new HashMap<>();

	static {
		for (FeeTypeCategoryEnum d : FeeTypeCategoryEnum.values()) {
			lookup.put(d.getDescription(), d);
		}
	}

	private FeeTypeCategoryEnum(String description) {
		this.description = description;

	}

	public static Map<String, FeeTypeCategoryEnum> getLookup() {
		return lookup;
	}

	public String getDescription() {
		return description;
	}

	public static FeeTypeCategoryEnum get(String description) {
		return lookup.get(description);
	}
}
