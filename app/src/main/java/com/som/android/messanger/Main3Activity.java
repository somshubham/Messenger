package com.som.android.messanger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import databaseHelper.DatabaseHandler;
import databaseHelper.Model2;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle args = new Bundle();
        String  data = getIntent().getStringExtra("data");


        DatabaseHandler  db = new DatabaseHandler(Main3Activity.this);


        List<Model2> model = db.getAllMessage2();
        ArrayList<String> a=new ArrayList<String>();
        int j=0;
        for (Model2 mv : model) {
            String log = "Id: "+mv.getSenderNumber() +""+mv.getMsg();
            String msg="";
            if(mv.getSenderNumber().equals(data))
            {
                msg=msg+"\n"+mv.getMsg()+"msg : "+mv.getSenderNumber();
                a.add(mv.getMsg());
            }
            // number[j]=mv.getSenderNumber();
            j++;
            Log.d("Name: ", log);
            Log.d("My SELECTED USER"+msg,"");
        }


     ListView   l=(ListView)findViewById(R.id.list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, a);


        l.setAdapter(adapter);

    }

}
