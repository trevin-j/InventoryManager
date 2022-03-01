package com.trevinj.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ManageInventoryActivity extends AppCompatActivity {

    /**
     * Debug tag.
     */
    private static final String TAG = "ManageInventoryActivity";

    /**
     * ArrayLists for the items and inventory.
     */
    private ArrayList<InventoryItem> inventory = new ArrayList<>();

    /**
     * Setup the Activity
     * @param savedInstanceState No clue what this is but it is necessary so yeah
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_inventory);

        inventory = DataManager.getStoredInventory(this);
        initRecyclerView();
    }

    /**
     * Initialize the RecyclerView.
     * This allows to show the list of items.
     */
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: Initializing...");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        ManageInventoryAdapter adapter = new ManageInventoryAdapter(this, inventory);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}