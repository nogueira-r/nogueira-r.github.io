package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ContactModel> contactModelArrayList;
    private Context context;

    public MyRecyclerViewAdapter(ArrayList<ContactModel> contactModelArrayList, Context context) {
        this.contactModelArrayList = contactModelArrayList;
        this.context = context;
    }

    public void filterList(ArrayList<ContactModel> filterList) {
        // below line is to add our filtered
        // list in our course array list.
        contactModelArrayList = filterList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel model = contactModelArrayList.get(position);
        holder.contactNameTV.setText(model.getContactName());
        holder.contactNumberTV.setText(model.getContactNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, UpdateContact.class);

                i.putExtra("name", model.getContactName());
                i.putExtra("number", model.getContactNumber());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView contactNameTV, contactNumberTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.idTVContactName);
            contactNumberTV = itemView.findViewById(R.id.idTVContactNumber);
        }
    }
}