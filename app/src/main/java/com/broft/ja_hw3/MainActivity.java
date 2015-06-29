package com.broft.ja_hw3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Button dbTestButton, spTestButton;
    Spinner spTestSpinner;
    TextView colorTestView;
    private SharedPreferenceHelper mSharedPreferenceHelper;
    String color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////// SharedPreferenceHelper 선언
        mSharedPreferenceHelper = new SharedPreferenceHelper(this);

        dbTestButton = (Button) findViewById(R.id.btn_db);
        dbTestButton.setOnClickListener(this);
        spTestSpinner = (Spinner) findViewById(R.id.spn_sp_test);
        String[] testArray = {"Blue", "Red", "Green"};
        ArrayAdapter colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, testArray);
        spTestSpinner.setAdapter(colorAdapter);
        spTestSpinner.setOnItemSelectedListener(new ColorSelectListner());
        colorTestView = (TextView) findViewById(R.id.tv_sp_test);

        spTestButton = (Button) findViewById(R.id.btn_color_toast);
        spTestButton.setOnClickListener(this);
        ///////// SharedPreference에서 COLOR라는 key의 스트링값 가져오기
       color = mSharedPreferenceHelper.getPrefsStringValue("COLOR");
        if (color != null) {
            switch (color) {
                case "Blue":
                    colorTestView.setBackgroundColor(0XFF0000FF);
                    break;
                case "Red":
                    colorTestView.setBackgroundColor(0XFFFF0000);
                    break;
                case "Green":
                    colorTestView.setBackgroundColor(0XFF00FF00);
                    break;
            }
        }
    }


    private class ColorSelectListner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch((String) parent.getItemAtPosition(position)) {
                case "Blue":
                    mSharedPreferenceHelper.writePrefs("COLOR", "Blue");
                    colorTestView.setBackgroundColor(0XFF0000FF);
                    break;
                case "Red":
                    mSharedPreferenceHelper.writePrefs("COLOR", "Red");
                    colorTestView.setBackgroundColor(0XFFFF0000);
                    break;
                case "Green":
                    mSharedPreferenceHelper.writePrefs("COLOR", "Green");
                    colorTestView.setBackgroundColor(0XFF00FF00);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_color_toast:
                String clr = mSharedPreferenceHelper.getPrefsStringValue("COLOR");
                if(clr !=null) {
                    Toast.makeText(this, clr + "입니다.", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "설정값이 없습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_db:
                startActivity(new Intent(this, DbTestActivity.class));
                break;
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
