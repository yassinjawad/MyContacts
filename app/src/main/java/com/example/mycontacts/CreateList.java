package com.example.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateList extends AppCompatActivity {
    // declare Intent
    Intent intent;

    // declare EditTexts
    EditText nameEditText;
    EditText emailEditText;
    EditText phoneEditText;

    // declare
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize EditTexts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        dbHandler = new DBHandler(this, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get the id of menu item selected
        switch (item.getItemId()) {
            case R.id.action_home :
                // initialize an Intent for the MainActivity and start it
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_add_contact :
                // initialize an Intent for the CreateList Activity and start it
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createList(MenuItem menuItem){

        // get data input in EditTexts and store it in String
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone =  phoneEditText.getText().toString();

        // trim String and see if they're equal to empty String
        if (name.trim().equals("") || email.trim().equals("") || phone.trim().equals("")){
            // display "Please enter a name, email, and phone!" toast
            Toast.makeText(this, "Please enter a name, email, and phone!",
                    Toast.LENGTH_LONG).show();
        } else {
            // add contact List into database
            dbHandler.addContactList(name, email, phone);

            // display "Contact List Created!" toast
            Toast.makeText(this, "Contact List Created!",
                    Toast.LENGTH_LONG).show();
        }

    }
}
