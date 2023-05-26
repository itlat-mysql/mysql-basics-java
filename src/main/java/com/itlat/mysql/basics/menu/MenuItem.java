package com.itlat.mysql.basics.menu;

public class MenuItem {
    private final String path;
    private final String name;
    private final String servletClassName;

    public MenuItem(String path, String name, String servletClassName) {
        this.path = path;
        this.name = name;
        this.servletClassName = servletClassName;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getServletClassName() {
        return servletClassName;
    }
}
