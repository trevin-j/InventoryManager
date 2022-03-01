package com.trevinj.inventorymanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageInventoryAdapter extends  RecyclerView.Adapter<ManageInventoryAdapter.ViewHolder>{

    /**
     * Message for the Intent for launching ManageItemActivity
     */
    public static final String CHOSEN_ITEM = "com.trevinj.inventorymanager.CHOSEN_ITEM";

    /**
     * Debug tag.
     */
    private static final String TAG = "Adapter";

    /**
     * Items for the RecyclerView contents.
     */
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<InventoryItem> inventory = new ArrayList<>();

    /**
     * Context of the parent Activity.
     */
    private Context context;

    /**
     * Construct the Adapter. Used when initialized by the parent Activity.
     * @param context The Activity context.
     * @param itemNames ArrayList of item names.
     */
    public ManageInventoryAdapter( Context context, ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;

        for (InventoryItem item:
             inventory) {
            itemNames.add(item.name);
        }

        this.context = context;
    }


    // The following method remains nearly the same regardless of the recyclerview and layout

    /**
     * Create the ViewHolder, inflate it, blah, blah, blah. No idea how it works.
     * @param parent The parent ViewGroup (I guess?)
     * @param viewType The type of View (Maybe?)
     * @return The created ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Bind the data to each ViewHolder.
     * Also, set the OnClickListener.
     * @param holder The ViewHolder
     * @param position The position of the ViewHolder.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.itemName.setText(itemNames.get(position));

        holder.manageButton.setOnClickListener(new View.OnClickListener(){
            /**
             * When user clicks on one of the buttons in the RecycleView,
             * open a new activity where you can add and remove stock to the selected item.
             * @param view View that triggered the action.
             */
            @Override
            public void onClick(View view) {
                // Get the more updated position.
                int position = holder.getAdapterPosition();

                // Create Intent for new Activity
                Intent intent = new Intent(context, ManageItemActivity.class);
                // Pass item id to intent so the next activity knows how to handle it.
                intent.putExtra(CHOSEN_ITEM, inventory.get(position).id);
                context.startActivity(intent);


                Log.d(TAG, "onClick: clicked on " + itemNames.get(position));

                Toast.makeText(context, inventory.get(position).id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Get the number of items that will be displayed in the list.
     * @return Number of items
     */
    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    /**
     * ViewHolder class, implemented for this specific Adapter and RecyclerView
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * The items in the ViewHolder
         */
        TextView itemName;
        Button manageButton;
        RelativeLayout rowItem;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name_view);
            manageButton = itemView.findViewById(R.id.manage_button);
            rowItem = itemView.findViewById(R.id.row_item);
        }
    }
}
