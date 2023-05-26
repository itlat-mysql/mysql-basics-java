package com.itlat.mysql.basics.model;

import java.util.Objects;

public class Product {
    private final int id;
    private final String name;
    private final String ean;
    private final double price;
    private final String createdAt;

    public Product(int id, String name, String ean, double price, String createdAt) {
        this.id = id;
        this.name = name;
        this.ean = ean;
        this.price = price;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEan() {
        return ean;
    }

    public double getPrice() {
        return price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
