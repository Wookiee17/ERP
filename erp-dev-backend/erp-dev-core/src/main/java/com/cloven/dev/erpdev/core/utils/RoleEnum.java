package com.cloven.dev.erpdev.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum  implements GrantedAuthority {
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_SUPERADMIN("ROLE_SUPERADMIN"),
	ROLE_STUDENT("ROLE_STUDENT"),
	ROLE_TEACHER("ROLE_TEACHER");
	
	private final String description;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<String, RoleEnum> lookup = new HashMap<String, RoleEnum>();

    static {
        for (RoleEnum d : RoleEnum.values()) {
            lookup.put(d.getDescription(), d);
        }
    }

    private RoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static RoleEnum get(String description) {
        return lookup.get(description);
    }

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return getDescription();
	}
}
