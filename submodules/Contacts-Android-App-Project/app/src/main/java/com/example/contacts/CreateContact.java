package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateContact extends AppCompatActivity {

    public String Name;
    public String Number;

    private TextView txtName, txtNumber;
    private Button btnCreate, btnCancel;

    MyDBHandler dbHandler = new MyDBHandler(CreateContact.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        txtName = findViewById(R.id.textName);
        txtNumber = findViewById(R.id.textNumber);

        btnCreate = findViewById(R.id.btnCreate);
        btnCancel = findViewById(R.id.btnCancel);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Name = txtName.getText().toString();
                Number = txtNumber.getText().toString();
                dbHandler.addNewContact(Name, Number);
                Intent i = new Intent(CreateContact.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}