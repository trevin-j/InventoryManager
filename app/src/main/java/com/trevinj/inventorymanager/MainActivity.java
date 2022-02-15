package com.trevinj.inventorymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called every time the "new item" button is pressed.
     * It launches a new activity where the user creates a new item to keep track of stock.
     * @param view The required argument of the button View.
     */
    public void startNewItemActivity(View view) {
        Intent intent = new Intent(this, NewItemActivity.class);
        startActivity(intent);
    }
}