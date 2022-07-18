package com.cloven.dev.erpdev.model.utils;

import java.util.HashMap;
import java.util.Map;

public enum AttachmentTypeEnum {
	TC("Transfer Certificate"), DOB("Date Of Birth Ceritficate"),
	;

	private final String description;

	// Reverse-lookup map for getting a status from a description
	private static final Map<String, AttachmentTypeEnum> lookup = new HashMap<>();

	static {
		for (AttachmentTypeEnum d : AttachmentTypeEnum.values()) {
			lookup.put(d.getDescription(), d);
		}
	}

	private AttachmentTypeEnum(String description) {
		this.description = description;

	}

	public static Map<String, AttachmentTypeEnum> getLookup() {
		return lookup;
	}

	public String getDescription() {
		return description;
	}

	public static AttachmentTypeEnum get(String description) {
		return lookup.get(description);
	}
}
