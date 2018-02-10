package com.hasna.projects.galaxyplanets;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    Button submitBtn, homeBtn;
    EditText insertTxt;
    String SQLiteQuery, Name;
    SQLiteDatabase SQLITEDATABASE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        submitBtn = findViewById(R.id.submitBtn);
        homeBtn = findViewById(R.id.HomeButton);
        insertTxt = findViewById(R.id.inputField);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InsertActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBCreate();
                insertData();
            }
        });

    }
    public void insertData() {

        Name = insertTxt.getText().toString();

        if (!TextUtils.isEmpty(Name)) {
            SQLiteQuery = "INSERT INTO planets_table(name) VALUES('" + Name + "');";

            SQLITEDATABASE.execSQL(SQLiteQuery);

            Toast.makeText(InsertActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            insertTxt.getText().clear();

        } else {

            Toast.makeText(InsertActivity.this, "Please Fill the Field", Toast.LENGTH_LONG).show();
        }
    }
    public void DBCreate() {

        SQLITEDATABASE = openOrCreateDatabase("GalaxyDB", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS planets_table(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR);");
    }
}
