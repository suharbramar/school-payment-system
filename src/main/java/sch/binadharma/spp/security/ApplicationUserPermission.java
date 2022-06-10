package sch.binadharma.spp.security;

public enum ApplicationUserPermission {
    STAFF_CATEGORY_READ("category:read"),
    STAFF_CATEGORY_WRITE("category:write"),
    ADMIN_CATEGORY_READ("category:read"),
    ADMIN_CATEGORY_WRITE("category:write"),
    ADMIN_CATEGORY_DELETE("category:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
