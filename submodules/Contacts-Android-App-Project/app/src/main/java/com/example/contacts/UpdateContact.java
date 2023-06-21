package com.example.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contacts.MyDBHandler;

public class UpdateContact extends AppCompatActivity {

    private EditText contactNameEdt, contactNumberEdt;
    private Button updateContactBtn, deleteContactBtn, cancelBtn;
    private MyDBHandler dbHandler;
    String contactName, contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        contactNameEdt = findViewById(R.id.txtEditContactName);
        contactNumberEdt = findViewById(R.id.txtEditContactNumber);
        updateContactBtn = findViewById(R.id.btnUpdate);
        deleteContactBtn = findViewById(R.id.btnDelete);
        cancelBtn = findViewById(R.id.btnCancel);

        MyDBHandler dbHandler = new MyDBHandler(UpdateContact.this);

        contactName = getIntent().getStringExtra("name");
        contactNumber = getIntent().getStringExtra("number");

        contactNameEdt.setText(contactName);
        contactNumberEdt.setText(contactNumber);

        updateContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.updateContact(contactName, contactNameEdt.getText().toString(), contactNumberEdt.getText().toString());
                Toast.makeText(UpdateContact.this, "Contact Updated", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateContact.this, MainActivity.class);
                startActivity(i);
            }
        });

        deleteContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteContact(contactName);
                Toast.makeText(UpdateContact.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateContact.this, MainActivity.class);
                startActivity(i);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
