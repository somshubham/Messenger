package com.som.android.messanger;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Messanger extends AppCompatActivity {


 //int MY_PERMISSIONS_REQUEST_READ_CONTACTS=0;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
Cursor c;
    ListView list;
    CustomAdapter adapter;
    public Messanger CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    public String[] msg;
    public Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);








//
//        int permissionCheck = ContextCompat.checkSelfPermission(Messanger.this,
//                Manifest.permission.WRITE_CALENDAR);
//
//// Here, thisActivity is the current activity
//        if (ContextCompat.checkSelfPermission(Messanger.this,
//                Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(Messanger.this,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(Messanger.this,
//                        new String[]{Manifest.permission.READ_SMS},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//
//
//
//
//
//
//
//            }
//        }
//













        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(CustomListView, "hi you want to send a msg?", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(Messanger.this,MainActivity.class);

                startActivity(i);






                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });





        CustomListView = this;

        setListData();
        res =getResources();
        list=(ListView)findViewById(R.id.list);


        /**************** Create Custom Adapter *********/
























    }











    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Log.i("pergrant","okokokokokokokokokoko");


                    Uri inboxURI = Uri.parse("content://sms/inbox");

                    String[] reqCols = new String[] { "_id", "address", "body" };
                    ContentResolver cr = getContentResolver();
                   c = cr.query(inboxURI, reqCols, null, null, null);
                   int o=0;
                    String sms1 = "";
                    int size=c.getCount();
                    CustomListViewValuesArr.clear();
                    msg=new String[size];
                    while (c.moveToNext()) {
                        final ListModel sched = new ListModel();
                        sms1 += "From :" +c.getString(2)+"\n";
                        msg[o]=""+c.getString(2);

                        sched.setNumber(""+c.getString(1));
                        sched.setMessage("mmessage :"+msg[o]);
                        CustomListViewValuesArr.add(sched);
                        o++;
                    }

                    adapter=new CustomAdapter(CustomListView, CustomListViewValuesArr,res);

                    list.setAdapter(adapter);


//                    int i=0;
//
//                    CustomListViewValuesArr.clear();
//                    int k=c.getCount();
//
//                    for(i=0;i<k;i++){
//                        final ListModel sched = new ListModel();
//                        // sms1 += "From :" +c.getString(2)+"\n";
//                        /******* Firstly take data in model object ******/
//                        sched.setNumber("977613597"+i);
//
//                        sched.setMessage("mmessage :"+msg[i]);
//
//                        /******** Take Model Object in ArrayList **********/
//                        CustomListViewValuesArr.add(sched);
//
//                    }














                    Log.i("sdf",""+o);
                    Log.i("hisss",""+sms1);

                  //  int size=c.getCount();
               //     setListData();
//


                    Uri uriSMSURI = Uri.parse("content://sms/inbox");
                    Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,null);
                    String sms = "";
                   while (cur.moveToNext()) {
                        sms += "From :" + cur.getString(2) + " : " + cur.getString(11)+""+cur.getString(12)+"\n";
                    }


                    Log.i("hisms",""+sms);

                    Log.i("hello",":sdsdasd");









                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.


                    Log.i("pergrant","nonononononononononon");

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }











    public void setListData()
    {













        int permissionCheck = ContextCompat.checkSelfPermission(Messanger.this,
                Manifest.permission.WRITE_CALENDAR);

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(Messanger.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Messanger.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(Messanger.this,
                        new String[]{Manifest.permission.READ_SMS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.







            }
        }

















    }

    public void onItemClick(int mPosition)
    {
        ListModel tempValues = (ListModel) CustomListViewValuesArr.get(mPosition);

        Toast.makeText(CustomListView,
                ""+tempValues.getNumber()+" \nImage:"+tempValues.getMessage()+" \nUrl:",
                Toast.LENGTH_LONG)
                .show();
    }
















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messanger, menu);
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
