package com.broft.ja_hw3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DbTestActivity extends ActionBarActivity {
    ListView dbTestListView;
    EditText name, age;
    SQLiteDatabase db;
    DbCreateHelper helper;
    Button storeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);

        helper = new DbCreateHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM broftMember", null);
        ArrayList<String> raw = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            raw.add("이름 : " + cursor.getString(1) + ", 나이 : " + cursor.getInt(2));
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, raw);
        dbTestListView = (ListView) findViewById(R.id.lv_db);
        dbTestListView.setAdapter(adapter);
        name = (EditText) findViewById(R.id.et_name);
        age = (EditText) findViewById(R.id.et_age);
        storeButton = (Button) findViewById(R.id.btn_registration);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeAndRenewUI();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_db_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void storeAndRenewUI() {
        if(name.getText()!=null && !name.getText().toString().equals("")&&
                age.getText()!=null && !age.getText().toString().equals("")){
            String addName = name.getText().toString();
            String addAge = age.getText().toString();
            store(addName, addAge);

        }
        else Toast.makeText(this, "두개 모두 입력해주세요", Toast.LENGTH_SHORT).show();

    }


    private void store(String name, String age){
        String sql = "INSERT INTO broftMember VALUES (null, '"
                + name + "', '"
                + age +"');";
        db.execSQL(sql);
        renewUI();

        Log.i("YJ","DB저장 완료!");
    }

    private void renewUI() {

        Cursor cursor = db.rawQuery("SELECT * FROM broftMember", null);
        ArrayList<String> raw = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            raw.add("이름 : " + cursor.getString(1) + ", 나이 : " + cursor.getInt(2));
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, raw);
        dbTestListView = (ListView) findViewById(R.id.lv_db);
        dbTestListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if(db.isOpen()) db.close();
        super.onDestroy();
    }
}
