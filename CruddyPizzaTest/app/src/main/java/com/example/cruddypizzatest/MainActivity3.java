package com.example.cruddypizzatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.*;

import android.widget.*;
import android.widget.Toast;
import android.content.*;

import java.util.ArrayList;
import java.util.List;
import android.database.*;

public class MainActivity3 extends AppCompatActivity {


    public Button buttonChange2;
    public EditText editTextPK;
    public Button buttonDelete;
    public Button buttonBackToOrder;
    public int primaryKey;
//    Button buttonRVDelete;
//    Button buttonRVUpdate;

    //variables

    RecyclerView rvOrders;
    List<Pizza> orderList = new ArrayList<>();
    MyAdapter adapter;
    DBAdapter db = new DBAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);




        buttonBackToOrder = findViewById(R.id.buttonOrderPage2);
        buttonChange2 = findViewById(R.id.buttonChange2);
        buttonDelete = findViewById(R.id.buttonDelete2);
        editTextPK = findViewById(R.id.editTextPK);
//        buttonRVDelete = findViewById(R.id.buttonRVDelete);
//        buttonRVUpdate = findViewById(R.id.buttonRVUpdate);


        rvOrders = (RecyclerView) findViewById(R.id.rvOrders);

        //setOrderData();



//        try{
//            String destPath = "/data/data/" + getPackageName() +"/databae/PizzaDB";
////Alternate way to do destPath:
////String destPath = Environment.getExternalStorageDirectory().getPath() +
////getPackageName() + "/database/MyDB";
//            File f = new File(destPath);
//            if(!f.exists()){
//                CopyDB(getBaseContext().getAssets().open("assets/PizzaDB.db"),
//                        new FileOutputStream(destPath));
//            }
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        db.open();
        orderList = db.getAllOrders1();
        db.close();

        adapter = new MyAdapter(orderList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvOrders.setLayoutManager(manager);
        rvOrders.setAdapter(adapter);

        buttonBackToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOrder = new Intent(MainActivity3.this, MainActivity.class);
                Intent intent = getIntent();
                startActivity(intentToOrder);
            }
        });

        buttonChange2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor;
                String primaryKeyStr = editTextPK.getText().toString();
                if(!primaryKeyStr.equals("")){

                    primaryKey = (Integer.parseInt(primaryKeyStr));
                    if (primaryKey > orderList.size()){
                        Toast.makeText(getApplicationContext(), "Order Number Too Large!", Toast.LENGTH_SHORT).show();
                    }
                    else if(primaryKey == (int) primaryKey){
                        Intent intentToChange = new Intent(MainActivity3.this, MainActivity2.class);

                        intentToChange.putExtra("PK", primaryKey);

                        startActivity(intentToChange);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Must Enter Order Number to Change!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primaryKeyStr = editTextPK.getText().toString();
                if(!primaryKeyStr.equals("")){

                    primaryKey = (Integer.parseInt(primaryKeyStr));
                    if (primaryKey > orderList.size()){
                        Toast.makeText(getApplicationContext(), "Order Number Too Large!", Toast.LENGTH_SHORT).show();
                    }
                    else if(primaryKey == (int) primaryKey){
                        db.open();
                        if(db.deleteOrder(primaryKey))
                            Toast.makeText(getApplicationContext(), "Order Deleted!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(), "Delete Failed!", Toast.LENGTH_SHORT).show();
                        db.close();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Must Enter Order Number to Change!", Toast.LENGTH_SHORT).show();
                }


            }
        });


//add a contact- CREATE
//        db.open();
//        long id = db.insertOrder("Bob Loblaw","Bob@Loblaws.ca");
//        id = db.insertOrder("Sue Me","Sue@Me.ca");
//        db.close();
//get all contacts - READ
//        db.open();
//        Cursor c = db.getAllContact();
//        if(c.moveToFirst())
//        {
//            do{
//                DisplayOrder(c);
//            }while(c.moveToNext());
//        }
//        db.close();
//get a single contact - READ
//        Cursor c = db.getAllOrders();
//        db.open();
//        c = db.getOrder(2);
//        if(c.moveToFirst())
//            DisplayOrder(c);
//        else
//            Toast.makeText(this,"No contact found",Toast.LENGTH_LONG).show();
//        db.close();

//delete a contact - DELETE

    }//end method onCreate
// functions go here








}// end main