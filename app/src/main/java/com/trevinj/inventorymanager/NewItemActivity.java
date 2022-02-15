package com.trevinj.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class NewItemActivity extends AppCompatActivity {

    private EditText newItemName;
    private EditText newItemID;
    private EditText newItemDesc;
    private EditText newItemStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        newItemName = findViewById(R.id.newItemName);
        newItemID = findViewById(R.id.newItemID);
        newItemDesc = findViewById(R.id.newItemDescription);
        newItemStock = findViewById(R.id.initialInventory);
    }

    // This fires when the user presses the create button when adding a new item.
    public void onSubmitNew(View view) {
        ArrayList<InventoryItem> inventory = DataManager.getStoredInventory(getApplicationContext());
        String itemId = newItemID.getText().toString();
        String itemName = newItemName.getText().toString();
        String itemDesc = newItemDesc.getText().toString();
        int itemStock = Integer.parseInt(newItemStock.getText().toString());

        InventoryItem newItem = new InventoryItem(itemId, itemName, itemDesc, itemStock);

        inventory.add(newItem);

        DataManager.updateStoredInventory(getApplicationContext(), inventory);
        finish();
    }

    // For test button. Print some data to console.
//    public void onTest(View view) {
//        for (InventoryItem i:
//             DataManager.getStoredInventory(this)) {
//            System.out.println(i.id + ": " + i.name + " - " + i.description + " <" + i.stock + ">");
//        }
//    }
}