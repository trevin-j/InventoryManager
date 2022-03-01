package com.trevinj.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ManageItemActivity extends AppCompatActivity {

    InventoryItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_item);

        // Get details from Intent
        Intent intent = getIntent();
        String itemID = intent.getStringExtra(ManageInventoryAdapter.CHOSEN_ITEM);

        // Get the InventoryItem whose ID is itemID
        item = DataManager.getItemWithID(itemID, this);

        updateDisplay();
    }

    private void updateDisplay() {
        TextView itemNameView = findViewById(R.id.itemName);
        TextView itemIDView = findViewById(R.id.itemID);
        TextView itemStockView = findViewById(R.id.stockNumber);

        itemNameView.setText(item.name);
        itemIDView.setText(item.id);
        itemStockView.setText(String.valueOf(item.stock));
    }

    public void deleteItem(View view) {
        DataManager.deleteItem(this, item);
        Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void changeStock(View view) {
        // If user presses the -1 button
        if (view.getId() == R.id.subtractOneButton) {
            if (item.stock > 0) {
                item.stock--;
            }
        }
        // else if user presses the "remove" button
        else if (view.getId() == R.id.removeButton) {
            TextView amountView = (TextView) findViewById(R.id.amountInput);
            int amount = Integer.parseInt(amountView.getText().toString());

            item.stock -= amount;
            if (item.stock < 0) {
                item.stock = 0;
            }
        }
        // else if user presses the "add" button
        else if (view.getId() == R.id.addButton) {
            TextView amountView = (TextView) findViewById(R.id.amountInput);
            int amount = Integer.parseInt(amountView.getText().toString());

            item.stock += amount;
        }

        DataManager.updateItem(this, item);
        Toast.makeText(this, "Updated Item", Toast.LENGTH_SHORT).show();
        finish();
    }
}