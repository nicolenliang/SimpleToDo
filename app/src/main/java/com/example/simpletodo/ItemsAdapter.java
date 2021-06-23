package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// responsible for displaying data into a row in recyclerview
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>
{
    public interface OnClickListener
    {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener
    {
        // pass in position to know which item to delete
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener)
    {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    // resp for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap inside a viewholder and return it
        return new ViewHolder(todoView);
    }

    @Override
    // binding data to particular viewholder
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position)
    {
        // grab item at position
        String item = items.get(position);

        // bind item into specified viewholder
        holder.bind(item);
    }

    @Override
    // num items available in data
    public int getItemCount()
    {
        return items.size();
    }

    //container to provide ez access to views that rep rows of list
    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update view inside of the viewholder with item data
        public void bind(String item)
        {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    // notifying listener which item was long-pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
