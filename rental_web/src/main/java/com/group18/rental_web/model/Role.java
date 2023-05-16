package com.group18.rental_web.model;

public enum Role {
    USER(0, "user"),
    ADMIN(1, "admin");

    private final int id;
    private final String value;

    public static boolean contains(String s) {
        for (Role role: values()) {
            if (role.value.equals(s)) return true;
        }
        return false;
    }

    Role(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}