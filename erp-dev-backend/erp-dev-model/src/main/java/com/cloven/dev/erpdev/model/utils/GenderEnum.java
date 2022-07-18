package com.cloven.dev.erpdev.model.utils;

import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {
	Male(1, "Male"), 
	Female(2, "Female");
	
	private static final Map<String, GenderEnum> lookup = new HashMap<String, GenderEnum>();
	private static final Map<Integer, GenderEnum> GENDER_BY_ID = new HashMap<Integer, GenderEnum>();

	static {
		for (GenderEnum d : GenderEnum.values()) {
			lookup.put(d.getDescription(), d);
			GENDER_BY_ID.put(d.getId(), d);
		}
	}

	private final int id;
	private final String description;

	GenderEnum(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static GenderEnum get(String description) {
		return lookup.get(description);
	}

	public static GenderEnum valueOfAccountTypeId(int id) {
		return GENDER_BY_ID.get(id);
	}
}
