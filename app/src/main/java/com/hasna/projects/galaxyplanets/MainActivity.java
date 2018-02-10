/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.hasna.projects.galaxyplanets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Displays list of Data that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {
    Cursor cursor;
    String GetSQLiteQuery, SQLiteQuery, Name;
    SQLiteDatabase SQLITEDATABASE;
    Button insertBtn, nextBtn, prevBtn;
    EditText editText, editTextIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = findViewById(R.id.insert_button);
        nextBtn = findViewById(R.id.next_button);
        prevBtn = findViewById(R.id.prev_button);
        editText = findViewById(R.id.editText);
        editTextIndex = findViewById(R.id.editTextIndex);

        SQLiteQuery = "INSERT INTO planets_table(name) VALUES('Mercury'),('Venus'),('Earth'),('Mars'),('Jupiter'),('Saturnus'),('Uranus'),('Neptunus'),('Pluto');";
        GetSQLiteQuery = "SELECT * FROM planets_table";

        SQLITEDATABASE = openOrCreateDatabase("GalaxyDB", Context.MODE_PRIVATE, null);
        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS planets_table(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR);");

        cursor = SQLITEDATABASE.rawQuery(GetSQLiteQuery, null);

        cursor.moveToFirst();

        try{
            cursor.moveToNext();
            showData();
        }
        catch(Throwable t){
            SQLITEDATABASE.execSQL(SQLiteQuery);
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextData();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevData();
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });
    }

    public void nextData() {
        if (!cursor.isLast()) {
            cursor.moveToNext();
        }
        showData();
    }

    public void prevData() {
        if (!cursor.isFirst()) {
            cursor.moveToPrevious();
        }
        showData();
    }

    public void showData(){
        editTextIndex.setText(cursor.getString(0));
        editText.setText(cursor.getString(1));
    }

}
