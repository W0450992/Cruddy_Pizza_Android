package com.example.cruddypizzatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;

import android.widget.*;
import android.widget.Toast;
import android.content.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import android.database.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {





    public Button buttonFrench;
    public Button buttonAddCheese;
    public Button buttonAddPepp;
    public Button buttonAddOlive;
    //public Button buttonAddBacon;
    public Button buttonAddSausa;
    public Button buttonSubCheese;
    public Button buttonSubPepp;
    public Button buttonSubOlive;
    //public Button buttonSubBacon;
    public Button buttonSubSausa;
    public Button buttonView;
    //public Button buttonChange;
    public Button buttonSave;
    //public Button buttonDelete;
    public EditText editTextName;
    public TextView textViewCheeseNum;
    public TextView textViewPeppNum;
    public TextView textViewOliveNum;
    //public TextView textViewBaconNum;
    public TextView textViewSausaNum;
    public TextView textViewSize;
    public TextView textViewToppings;
    public TextView textViewCheese;
    public TextView textViewPepp;
    public TextView textViewOlive;
    //public TextView textViewBacon;
    public TextView textViewSausa;
    public RadioButton RBSmall;
    public RadioButton RBMedium;
    public RadioButton RBLarge;
    public String frenchButtonTextFr;
    public String frenchButtonTextEn;
    public String editTextEn;
    public String editTextFR;
    public String sizeTextEn;
    public String sizeTextFr;
    public String toppingsTextEn;
    public String toppingsTextFr;

    SharedPreferences prefs; //= getPreferences((MODE_PRIVATE));
    public static final String EnglishKey = "termsEn";
    public static final String FrenchKey = "termsFr";
    public static final String LanguageKey = "Language";

    //variables
    public int numToppingsTotal = 0;
    public int numToppingsCheese = 0;
    public int numToppingsPep = 0;
    public int numToppingsOlive = 0;
    //public int numToppingsBacon = 0;
    public int numToppingsSausa = 0;

    public int getFrenchCounter() {
        return frenchCounter;
    }

    public void setFrenchCounter(int frenchCounter) {
        this.frenchCounter = frenchCounter;
    }

    public int frenchCounter;

    public String getCustomLanguage() {
        return customLanguage;
    }

    public void setCustomLanguage(String customLanguage) {
        this.customLanguage = customLanguage;
    }

    public String customLanguage;
    public String PREFERENCES = "preferences";

    List<String> english;
    List<String> french;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        english = Arrays.asList(getResources().getStringArray(R.array.English_Array));
        french = Arrays.asList(getResources().getStringArray(R.array.French_Array));

        buttonFrench = findViewById(R.id.buttonFrench);
        buttonAddCheese = findViewById(R.id.buttonAddCheese);
        buttonAddPepp = findViewById(R.id.buttonAddPep);
        buttonAddOlive = findViewById(R.id.buttonAddOlive);
        //buttonAddBacon = findViewById(R.id.buttonAddBacon);
        buttonAddSausa = findViewById(R.id.buttonAddSausa);
        buttonSubCheese = findViewById(R.id.buttonSubCheese);
        buttonSubPepp = findViewById(R.id.buttonSubPep);
        buttonSubOlive = findViewById(R.id.buttonSubOlive);
        //buttonSubBacon = findViewById(R.id.buttonSubBacon);
        buttonSubSausa = findViewById(R.id.buttonSubSausa);
        editTextName = findViewById(R.id.editTextTextPersonName);
        textViewCheeseNum = findViewById(R.id.textViewCheeseNum);
        textViewPeppNum = findViewById(R.id.textViewPepNum);
        textViewOliveNum = findViewById(R.id.textViewOliveNum);
        //textViewBaconNum = findViewById(R.id.textViewBaconNum);
        textViewSausaNum = findViewById(R.id.textViewSausaNum);
        textViewSize = findViewById(R.id.textViewSize);
        textViewToppings = findViewById(R.id.textViewToppings);
        textViewCheese = findViewById(R.id.textViewCheese);
        textViewPepp = findViewById(R.id.textViewPepper);
        textViewOlive = findViewById(R.id.textViewOlives);
        //textViewBacon = findViewById(R.id.textViewBacon);
        textViewSausa = findViewById(R.id.textViewSausa);
        buttonView = findViewById(R.id.buttonView);
//        buttonChange = findViewById(R.id.buttonChange);
        buttonSave = findViewById(R.id.buttonSave);
        //buttonDelete = findViewById(R.id.buttonDelete);

        RBSmall = findViewById(R.id.radioButtonSmall);
        RBMedium = findViewById(R.id.radioButtonMedium);
        RBLarge = findViewById(R.id.radioButtonLarge);
        frenchButtonTextFr = getString(R.string.buttonAnglaisButton);
        frenchButtonTextEn = getString(R.string.buttonFrench);
        editTextEn = getString(R.string.editTextEnterName);
        editTextFR = getString(R.string.editTextEntrezLeNom);
        sizeTextEn = getString(R.string.textViewSize);
        sizeTextFr = getString(R.string.textViewSizeFrench);
        toppingsTextEn = getString(R.string.textViewToppings);
        toppingsTextFr = getString(R.string.textViewToppingsFrench);

//        Resources res = getResources();
//        String[] englishTerms = res.getStringArray(R.array.English_Array);
//        String[] frenchTerms = res.getStringArray(R.array.French_Array);
        prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


        try{
            String destPath = "/data/data/" + getPackageName() +"/database/PizzaDB";
//Alternate way to do destPath:
//String destPath = Environment.getExternalStorageDirectory().getPath() +
//getPackageName() + "/database/MyDB";
            File f = new File(destPath);
            if(!f.exists()){
                CopyDB(getBaseContext().getAssets().open("PizzaDB"),
                        new FileOutputStream(destPath));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        DBAdapter db = new DBAdapter(this);
//add a contact- CREATE



        //buttonFrench.setText( prefs.getString(TEXT_VALUE_KEY,frenchButtonTextEn)); //set default
        //frenchCounter = prefs.getInt(TEXT_VALUE_KEY,frenchCounter);

        //SharedPreferences sharedpreferences = getSharedPreferences(EnglishKey, MODE_PRIVATE); // shared pref
        int Language = prefs.getInt(LanguageKey, frenchCounter); // set Language to saved pref as default
        setFrenchCounter(Language); //setter
        int currentLanguage = getFrenchCounter();
        if(frenchCounter == 0){
            updateView();
        }
        else if(frenchCounter == 1){
                updateView();
            }
        else{

        }

        buttonFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentLanguage = getFrenchCounter();
                if(currentLanguage == 0){
                    frenchCounter++;
                    //editor.putInt(TEXT_VALUE_KEY, frenchCounter);
                    //editor.putString(buttonFrench.getText().toString(), buttonFrench.getText().toString());
                    setCustomLanguage("1");
                    editor.putInt(LanguageKey, frenchCounter);
                    editor.commit();

                }
                else if(currentLanguage == 1){
                    frenchCounter=0;
                    //editor.putInt(TEXT_VALUE_KEY2, frenchCounter);
                    //editor.putString(buttonFrench.getText().toString(), buttonFrench.getText().toString());
                    setCustomLanguage("0");
                    editor.putInt(LanguageKey, frenchCounter);
                    editor.commit();
                }
                //SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES,MODE_PRIVATE).edit();

                editor.apply();


                updateView();

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        buttonAddCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            numToppingsTotal = getTotalToppings();

           if(numToppingsTotal >5){
                Toast.makeText(getApplicationContext(), "Cant have more than 6 toppings!", Toast.LENGTH_SHORT).show();
               numToppingsTotal = 6;
            }
            else{
                numToppingsTotal = numToppingsTotal + 1;
                numToppingsCheese++;
            }
            String numToppingsStr = String.valueOf(numToppingsCheese);
            textViewCheeseNum.setText(numToppingsStr);

            }
        });
        buttonAddPepp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numToppingsTotal = getTotalToppings();

                if(numToppingsTotal >5){
                    Toast.makeText(getApplicationContext(), "Cant have more than 6 toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 6;
                }
                else{
                    numToppingsTotal = numToppingsTotal + 1;
                    numToppingsPep++;

                }
                String numToppingsStr = String.valueOf(numToppingsPep);
                textViewPeppNum.setText(numToppingsStr);

            }

        });
        buttonAddOlive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numToppingsTotal = getTotalToppings();

                if(numToppingsTotal >5){
                    Toast.makeText(getApplicationContext(), "Cant have more than 6 toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 6;
                }
                else{
                    numToppingsTotal = numToppingsTotal + 1;
                    numToppingsOlive++;

                }
                String numToppingsStr = String.valueOf(numToppingsOlive);
                textViewOliveNum.setText(numToppingsStr);

            }


        });
//        buttonAddBacon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                numToppingsTotal = getTotalToppings();
//
//                if(numToppingsTotal >5){
//                    Toast.makeText(getApplicationContext(), "Cant have more than 6 toppings!", Toast.LENGTH_SHORT).show();
//                    numToppingsTotal = 6;
//                }
//                else{
//                    numToppingsTotal = numToppingsTotal + 1;
//                    numToppingsBacon++;
//
//                }
//                String numToppingsStr = String.valueOf(numToppingsBacon);
//                textViewBaconNum.setText(numToppingsStr);
//
//            }
//
//        });
        buttonAddSausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numToppingsTotal = getTotalToppings();

                if(numToppingsTotal >5){
                    Toast.makeText(getApplicationContext(), "Cant have more than 6 toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 6;
                }
                else{
                    numToppingsTotal = numToppingsTotal + 1;
                    numToppingsSausa++;

                }
                String numToppingsStr = String.valueOf(numToppingsSausa);
                textViewSausaNum.setText(numToppingsStr);

            }

        });

        buttonSubCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numToppingsTotal = getTotalToppings();

                if(Integer.parseInt(textViewCheeseNum.getText().toString()) == 0){
                    Toast.makeText(getApplicationContext(), "Cant have negative toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 0;
                }
                else{
                    numToppingsTotal = numToppingsTotal - 1;
                    numToppingsCheese--;
                }
                String numToppingsStr = String.valueOf(numToppingsCheese);
                textViewCheeseNum.setText(numToppingsStr);

            }
        });

        buttonSubPepp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numToppingsTotal = getTotalToppings();

                if(Integer.parseInt(textViewPeppNum.getText().toString()) == 0){
                    Toast.makeText(getApplicationContext(), "Cant have negative toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 0;
                }
                else{
                    numToppingsTotal = numToppingsTotal - 1;
                    numToppingsPep--;
                }
                String numToppingsStr = String.valueOf(numToppingsPep);
                textViewPeppNum.setText(numToppingsStr);

            }
        });
        buttonSubOlive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numToppingsTotal = getTotalToppings();

                if(Integer.parseInt(textViewOliveNum.getText().toString()) == 0){
                    Toast.makeText(getApplicationContext(), "Cant have negative toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 0;
                }
                else{
                    numToppingsTotal = numToppingsTotal - 1;
                    numToppingsOlive--;
                }
                String numToppingsStr = String.valueOf(numToppingsOlive);
                textViewOliveNum.setText(numToppingsStr);

            }
        });

//        buttonSubBacon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numToppingsTotal = getTotalToppings();
//
//                if(Integer.parseInt(textViewBaconNum.getText().toString()) == 0){
//                    Toast.makeText(getApplicationContext(), "Cant have negative toppings!", Toast.LENGTH_SHORT).show();
//                    numToppingsTotal = 0;
//                }
//                else{
//                    numToppingsTotal = numToppingsTotal - 1;
//                    numToppingsBacon--;
//                }
//                String numToppingsStr = String.valueOf(numToppingsBacon);
//                textViewBaconNum.setText(numToppingsStr);
//
//            }
//        });

        buttonSubSausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numToppingsTotal = getTotalToppings();

                if(Integer.parseInt(textViewSausaNum.getText().toString()) == 0){
                    Toast.makeText(getApplicationContext(), "Cant have negative toppings!", Toast.LENGTH_SHORT).show();
                    numToppingsTotal = 0;
                }
                else{
                    numToppingsTotal = numToppingsTotal - 1;
                    numToppingsSausa--;
                }
                String numToppingsStr = String.valueOf(numToppingsSausa);
                textViewSausaNum.setText(numToppingsStr);

            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToView = new Intent(MainActivity.this, MainActivity3.class);
                Intent intent = getIntent();
                //intentToView.putExtra("name", editTextName.getText().toString());
                startActivity(intentToView);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String custInfo = editTextName.getText().toString();
                if(custInfo.equals("")){
                    Toast.makeText(getApplicationContext(), "Must Select Name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int size = 0;
                    if (RBSmall.isChecked()) {
                        size = 0;
                        int cheese = Integer.parseInt(textViewCheeseNum.getText().toString());
                        int pepperoni = Integer.parseInt(textViewPeppNum.getText().toString());
                        int olive = Integer.parseInt(textViewOliveNum.getText().toString());
                        int sausage = Integer.parseInt(textViewSausaNum.getText().toString());

                        db.open();
                        long id = db.insertOrder(custInfo, size, cheese, pepperoni, olive, sausage);
                        db.close();
                    } else if (RBMedium.isChecked()) {
                        size = 1;
                        int cheese = Integer.parseInt(textViewCheeseNum.getText().toString());
                        int pepperoni = Integer.parseInt(textViewPeppNum.getText().toString());
                        int olive = Integer.parseInt(textViewOliveNum.getText().toString());
                        int sausage = Integer.parseInt(textViewSausaNum.getText().toString());

                        db.open();
                        long id = db.insertOrder(custInfo, size, cheese, pepperoni, olive, sausage);
                        db.close();
                    } else if (RBLarge.isChecked()) {
                        size = 2;
                        int cheese = Integer.parseInt(textViewCheeseNum.getText().toString());
                        int pepperoni = Integer.parseInt(textViewPeppNum.getText().toString());
                        int olive = Integer.parseInt(textViewOliveNum.getText().toString());
                        int sausage = Integer.parseInt(textViewSausaNum.getText().toString());

                        db.open();
                        long id = db.insertOrder(custInfo, size, cheese, pepperoni, olive, sausage);
                        db.close();
                    } else {
                        Toast.makeText(getApplicationContext(), "Must Select Size!", Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(getApplicationContext(), "Order Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }//end oncreate

    //functions go here



    public void CopyDB(InputStream inputStream,OutputStream outputStream)
            throws IOException{
//copy 1k bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    }//end method CopyDB

    public void updateView(){
        if(getFrenchCounter() == 0){
            buttonFrench.setText(frenchButtonTextFr);
            editTextName.setHint(editTextFR);
            textViewSize.setText(sizeTextFr);
            RBSmall.setText(french.get(0));
            RBMedium.setText(french.get(1));
            RBLarge.setText(french.get(2));
            textViewCheese.setText(french.get(3));
            textViewPepp.setText(french.get(4));
            textViewOlive.setText(french.get(5));
            //textViewBacon.setText(french.get(6));
            textViewSausa.setText(french.get(7));
            buttonView.setText(french.get(8));
            //buttonChange.setText(french.get(9));
            buttonSave.setText(french.get(10));
            //buttonDelete.setText(french.get(11));
            //editor.putInt(TEXT_VALUE_KEY, frenchCounter);
        }
        else if(getFrenchCounter() == 1){
            buttonFrench.setText(frenchButtonTextEn);
            editTextName.setHint(editTextEn);
            textViewSize.setText(sizeTextEn);
            RBSmall.setText(english.get(0));
            RBMedium.setText(english.get(1));
            RBLarge.setText(english.get(2));
            textViewCheese.setText(english.get(3));
            textViewPepp.setText(english.get(4));
            textViewOlive.setText(english.get(5));
            //textViewBacon.setText(english.get(6));
            textViewSausa.setText(english.get(7));
            buttonView.setText(english.get(8));
            //buttonChange.setText(english.get(9));
            buttonSave.setText(english.get(10));
            //buttonDelete.setText(english.get(11));
        }
    }

    public int getTotalToppings(){
        numToppingsCheese = Integer.parseInt(textViewCheeseNum.getText().toString());
        numToppingsPep = Integer.parseInt(textViewPeppNum.getText().toString());
        numToppingsOlive =  Integer.parseInt(textViewOliveNum.getText().toString());
        //numToppingsBacon = Integer.parseInt(textViewBaconNum.getText().toString());
        numToppingsSausa = Integer.parseInt(textViewSausaNum.getText().toString());
        numToppingsTotal = numToppingsCheese + numToppingsPep + numToppingsOlive + numToppingsSausa;
        return numToppingsTotal;
    }

    public void getTerms(){

    }
}// end main