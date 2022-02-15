package com.trevinj.inventorymanager;

import java.io.Serializable;

/**
 * An Object representing an item in inventory.
 * It contains data such as current stock, id, name, description, etc.
 */
public class InventoryItem implements Serializable {
    public String id;
    public String name;
    public String description;
    public int stock;

    public InventoryItem(String id, String name, String desc, int initialStock) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.stock = initialStock;
    }
}
