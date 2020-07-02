package models;

import java.util.UUID;

public class Store {
    private UUID id_store;
    private String name;

    public UUID getId_shop() {
        return id_store;
    }

    public void setId_shop(UUID id_shop) {
        this.id_store = id_shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
