package models;

import java.util.Date;
import java.util.UUID;

public class Product {
    private int id_product;
    private int id_category;
    private String description;
    private int days_of_life;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDays_of_life() {
        return days_of_life;
    }

    public void setDays_of_life(int days_of_life) {
        this.days_of_life = days_of_life;
    }
}
