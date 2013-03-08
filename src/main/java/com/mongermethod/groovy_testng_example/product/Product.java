package com.mongermethod.groovy_testng_example.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "monger_product")
public class Product implements Serializable {
    @Id
    private String sku;
    private BigDecimal price;
    private String name;
    private String description;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        if (!sku.equals(product.sku)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sku.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
