package com.som.android.messanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import databaseHelper.DatabaseHandler;
import databaseHelper.Model;
import databaseHelper.Model2;

public class Main2Activity extends AppCompatActivity {


    ListView l;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        db = new DatabaseHandler(Main2Activity.this);
        List<Model> model = db.getAllMessage();
        int i=0;

        for (Model mv : model) {
            i++;
        }
String[] number=new String[i];

        int j=0;
        for (Model mv : model) {
            String log = "Id: "+mv.getSenderNumber();
            number[j]=mv.getSenderNumber();
            j++;
            Log.d("Name: ", log);
        }


      l=(ListView)findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, number);


        l.setAdapter(adapter);

l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //  Toast.makeText(Main2Activity.this, l.getItemAtPosition(position)+""+position, Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
        intent.putExtra("data",""+l.getItemAtPosition(position));
        startActivity(intent);









    }
});



    }

}
