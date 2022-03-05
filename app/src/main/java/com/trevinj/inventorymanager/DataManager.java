package com.trevinj.inventorymanager;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * A class not meant to be instantiated, but contains static methods and data for storing and retrieving info.
 * Methods in this class that write or read data will create the files if they don't exist.
 */
public class DataManager {

    /**
     * Get the ArrayList stored in a specified file.
     * @param context The context of the App. NOT activity.
     * @return An arraylist stored in the file.
     */
    public static ArrayList<InventoryItem> getStoredInventory(Context context) {
        ArrayList<InventoryItem> inventory = new ArrayList<>();
        try {
            inventory = (ArrayList<InventoryItem>) getStoredObject(context, "inventory.dat");
        } catch (IOException | ClassNotFoundException e) {
            // The file might just not contain any data, but still exists.
            e.printStackTrace();
        }
        return inventory;
    }

    public static InventoryItem getItemWithID(String id, Context context) {
        ArrayList<InventoryItem> inventory = getStoredInventory(context);

        for (InventoryItem item:
             inventory) {
            if(item.id.equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Update the stored inventory with a new inventory.
     * @param context Activity context.
     * @param inventory ArrayList of the inventory.
     */
    public static void updateStoredInventory(Context context, ArrayList<InventoryItem> inventory) {
        try {
            FileOutputStream fileOut = context.openFileOutput("inventory.dat", Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(inventory);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a stored object from the file.
     * If the file does not exist, it will be created and an empty object is returned.
     *  If that happens, IOException or ClassNotFoundException may be thrown.
     * @param context Activity context.
     * @param fileName Name of file.
     * @return Object stored in file.
     * @throws IOException May be thrown if file has no contents.
     * @throws ClassNotFoundException May be thrown if file has no contents.
     */
    public static Object getStoredObject(Context context, String fileName) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = context.openFileInput(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            return objectIn.readObject();
        } catch (FileNotFoundException e) {
            // The file must not exist, so create it
            e.printStackTrace();

            File inventoryFile = new File(context.getFilesDir(), fileName);
            inventoryFile.createNewFile();
            return new Object();
        }
    }


    public static void updateItem(Context context, InventoryItem item) {
        ArrayList<InventoryItem> items = getStoredInventory(context);

        for (InventoryItem storedItem:
             items) {
            if (item.id.equals(storedItem.id)) {
                storedItem.stock = item.stock;
                storedItem.name = item.name;
                storedItem.description = item.description;
                break;
            }
        }

        updateStoredInventory(context, items);
    }

    public static void deleteItem(Context context, InventoryItem item) {
        ArrayList<InventoryItem> items = getStoredInventory(context);

        for (InventoryItem storedItem:
                items) {
            if (item.id.equals(storedItem.id)) {
                items.remove(storedItem);
                break;
            }
        }

        updateStoredInventory(context, items);
    }
}
