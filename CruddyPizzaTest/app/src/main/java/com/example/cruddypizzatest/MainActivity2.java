package com.example.cruddypizzatest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    Button buttonBackToOrder;
    //TextView textViewName2;
    Button buttonDelete;
    Button buttonUpdate;
    EditText etCustInfo;
    EditText etSize;
    EditText etCheese;
    EditText etPepp;
    EditText etOlive;
    EditText etSausa;

    List<Pizza> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonBackToOrder = findViewById(R.id.buttonOrderPage2);
        buttonDelete = findViewById(R.id.buttonDelete2);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        etCustInfo = findViewById(R.id.editTextNameEdit);
        etSize = findViewById(R.id.editTextSize);
        etCheese = findViewById(R.id.editTextTopping1);
        etPepp = findViewById(R.id.editTextTopping2);
        etOlive = findViewById(R.id.editTextTopping3);
        etSausa = findViewById(R.id.editTextTopping4);



        //textViewName2 = findViewById(R.id.textViewName2);
        Intent intent = getIntent();
        int row = intent.getIntExtra("PK",0);
        DBAdapter db = new DBAdapter(this);
        //Cursor c = db.getAllOrders();
        db.open();
        orderList = db.getAllOrders1();

        Cursor cursor = db.getOrder(row);
        String custInfo = cursor.getString(1);
        String size = Integer.toString(cursor.getInt(2));
        String cheese = Integer.toString(cursor.getInt(3));
        String pepp = Integer.toString(cursor.getInt(4));
        String olive = Integer.toString(cursor.getInt(5));
        String sausa = Integer.toString(cursor.getInt(6));
        db.close();
        etCustInfo.setText(custInfo);
        etSize.setText(size);
        etCheese.setText(cheese);
        etPepp.setText(pepp);
        etOlive.setText(olive);
        etSausa.setText(sausa);



        //get a single contact - READ

//        db.open();
//        c = db.getOrder(row);
//        if(c.moveToFirst())
//            DisplayOrder(c);
//        else
//            Toast.makeText(this,"No contact found",Toast.LENGTH_LONG).show();
//        db.close();



        buttonBackToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToOrder = new Intent(MainActivity2.this, MainActivity.class);
                Intent intent = getIntent();
                startActivity(intentToOrder);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                if(db.deleteOrder(row))
                    Toast.makeText(getApplicationContext(), "Order Deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Delete Failed!", Toast.LENGTH_SHORT).show();
                db.close();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update a contact - UPDATE
                String custInfo = etCustInfo.getText().toString();
                int size = Integer.parseInt(etSize.getText().toString());
                int cheese = Integer.parseInt(etCheese.getText().toString());
                int pepp = Integer.parseInt(etPepp.getText().toString());
                int olive = Integer.parseInt(etOlive.getText().toString());
                int sausa = Integer.parseInt(etSausa.getText().toString());

                if(ifGreaterThan6()){
                    Toast.makeText(getApplicationContext(), "Too Many Toppings!", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.open();
                    if (db.updateOrder(row, custInfo, size, cheese, pepp, olive, sausa)) {
                        Toast.makeText(getApplicationContext(), "Update Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Update Failed!", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }
            }
        });
    }// end on create

//functions go here
    public boolean ifGreaterThan6(){
        int totalToppings = Integer.parseInt(etCheese.getText().toString()) + Integer.parseInt(etPepp.getText().toString()) + Integer.parseInt(etOlive.getText().toString()) + Integer.parseInt(etPepp.getText().toString());
        if(totalToppings>6){
            return true;
        }
        else{
            return false;
        }
    }
    public void ifSizeOutOfBounds(int size){
        if(size>2){
            etSize.setText(0);
        }
    }

}