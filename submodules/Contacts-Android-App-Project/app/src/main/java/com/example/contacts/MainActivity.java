package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ContactModel> contactModelArrayList;
    private MyDBHandler dbHandler;
    private MyRecyclerViewAdapter contactRVAdapter;
    private RecyclerView contactsRV;

    private String Name;
    private String Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnAdd = findViewById(R.id.buttonAdd);
        SearchView searchView = findViewById(R.id.search);

        MyDBHandler dbHandler = new MyDBHandler(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateContact.class);
                startActivity(i);
            }
        });
        contactModelArrayList = new ArrayList<>();

        contactModelArrayList = dbHandler.readContacts();

        contactRVAdapter = new MyRecyclerViewAdapter(contactModelArrayList, MainActivity.this);
        contactsRV = findViewById(R.id.list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        contactsRV.setLayoutManager(linearLayoutManager);

        contactsRV.setAdapter(contactRVAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<ContactModel> filteredList = new ArrayList<ContactModel>();

        // running a for loop to compare elements.
        for (ContactModel item : contactModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getContactName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        }
        else {
            // at last we are passing that filtered
            // list to our adapter class.
            contactRVAdapter.filterList(filteredList);
        }
    }
}