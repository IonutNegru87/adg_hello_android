package com.inegru.siit.myapplication.week4;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inegru.siit.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

/**
 * https://developer.android.com/guide/topics/ui/layout/recyclerview
 */
public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // Get the recycler view
        RecyclerView rvDemo = findViewById(R.id.rv_demo);
        // Improve performance if changes in the content do not change the layout size of the RV
        rvDemo.setHasFixedSize(true);

        // Create the Linear Layout manager with vertical orientation
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.VERTICAL);
        // Set the layout manager to the recycler view
        rvDemo.setLayoutManager(lm);

        // Set divider for row separation
        DividerItemDecoration dividerItemDecoration =
            new DividerItemDecoration(rvDemo.getContext(), lm.getOrientation());
        rvDemo.addItemDecoration(dividerItemDecoration);

        // Get the data source
        List<Item> data = DataSource.getDummyItems(50);
        // Create the adapter with the data source
        ItemAdapter adapter = new ItemAdapter(data);
        // Final step, set the adapter to the recycler view
        rvDemo.setAdapter(adapter);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView firstName;
        private final TextView lastName;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
        }

        void bind(@NonNull final Item item) {
            firstName.setText(item.getFirstName());
            lastName.setText(item.getLastName());
        }

        void updateRowBackground(int position) {
            if(position %2 == 1){
                itemView.setBackgroundColor(Color.rgb(250, 250, 250));
            } else {
                itemView.setBackgroundColor(Color.rgb(255, 255, 255));
            }
        }
    }

    static class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @NonNull
        private final List<Item> items;

        ItemAdapter(@NonNull final List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Get the inflater from the ViewGroup parent - will be used to actually inflate the
            // item view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            // Inflate the view
            View view = inflater.inflate(R.layout.list_item, parent, false);
            // Create the view holder and return it to the method
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(items.get(position));
            holder.updateRowBackground(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
