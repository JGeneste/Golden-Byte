package com.goldenbyte.loginusingparsesdk;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.Parse;
import com.parse.ParseUser;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by jasongeneste on 11/6/14.
 */
public class Withdraw extends Activity implements View.OnClickListener {

    private EditText withdrawAmt;
    private Double Amt;
    private Button mConfirm;
    private Button mCancel;
    private Spinner mSpinner;
    //ConnectionDetector cd;
    private HashMap<String, ArrayList> CheckingAccMap;
    private HashMap<String, ArrayList> SavingsAccMap;
    private ArrayList<String> checklist;
    private ArrayList<String> savingslist;
    ArrayList changedAmt;
    private Double CheckBal;
    private Double SavingsBal;
    private int numOfChecking;
    private int numOfSaving;
    //private final ParseUser userData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_withdraw);
        Parse.initialize(this, "tmvsMl1TNmP2aF93btAiBYVf7vyWGMFy84Eh5tL2", "m9XxV2k5JNRNZJPTpT5LDj1dJVCxJFNjXzvEYaVS");
        final ParseUser userData = ParseUser.getCurrentUser();
        // creating connection detector class instance
        //cd = new ConnectionDetector(getApplicationContext());

        withdrawAmt = (EditText) findViewById(R.id.withdrawAmt);
        mConfirm = (Button) findViewById(R.id.Confirm);
        mCancel = (Button) findViewById(R.id.Cancel);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        mCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in =  new Intent(Withdraw.this,MainActivity.class);
                startActivity(in);
            }
        });

        List<String> spinnerArray = new ArrayList<String>();
        CheckingAccMap = (HashMap<String, ArrayList>)userData.get("Checking");
        SavingsAccMap = (HashMap<String, ArrayList>)userData.get("Savings");
        //numOfChecking = numOfSaving = 1;
                for( String key : CheckingAccMap.keySet()) {
                    checklist = CheckingAccMap.get(key);
                    CheckBal = 0.0;//Double.valueOf(0.0);
                    System.out.println("for1");
                    //numOfChecking += 1;
                    //checklist[0] is the total balance
                    for(int i = 1; i < checklist.size(); i++) {
                        if(checklist.size() > 1) {
                            CheckBal += Double.parseDouble(checklist.get(i));
                            System.out.println("checkBal = " + CheckBal);
                        }
                        //checklist.add(0,String.valueOf(CheckBal));
                    }
                    spinnerArray.add("Chk " + key + " $" + CheckBal);
                }
        for( String key: SavingsAccMap.keySet()){
            savingslist = SavingsAccMap.get(key);
            SavingsBal = 0.0;
            System.out.println("for2");
            //numOfSaving += 1;
            for(int i = 1; i < savingslist.size(); i++){
                //only get the values if the list is not empty
                if(savingslist.size() > 1) {
                    SavingsBal += Double.parseDouble(savingslist.get(i)); //add up the balance
                }
            }
            spinnerArray.add("Sav " + key + " $" + SavingsBal); //add to the display menu
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);


        mConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String amt = withdrawAmt.getEditableText().toString();
                String spinSelect = sItems.getSelectedItem().toString();
                spinSelect.indexOf("$");
                String accType = spinSelect.substring(0, 3);
                accType.trim();
                String accountNo = spinSelect.substring(4, 5);
                //System.out.println("aaa"+accountNo+"zzz");
                Double actualAmt = Double.parseDouble(amt);
                if(accType.equals("Chk")) {
                    changedAmt = CheckingAccMap.get(accountNo);
                    //Make sure to check for overdrafting later
                    //double d = (0.0-actualAmt);
                    //String s = String.valueOf(d);
                    //changedAmt.add(Double.valueOf(0.0-actualAmt));
                    changedAmt.add(String.valueOf(0.0-actualAmt));
                    changedAmt.set(0, String.valueOf(CheckBal - actualAmt)); //save balance to first index
                    //checklist.add(0,String.valueOf(CheckBal));
                    CheckingAccMap.put(accountNo, changedAmt);
                    userData.put("Checking", CheckingAccMap);
                    userData.saveInBackground();
                }
                else {
                    changedAmt = SavingsAccMap.get(accountNo);
                    //Make sure to check for overdrafting later
                    changedAmt.add(String.valueOf(0.0-actualAmt));
                    changedAmt.set(0, String.valueOf(SavingsBal - actualAmt)); //update savings balance
                    System.out.println("changedAmt ="+changedAmt);
                    SavingsAccMap.put(accountNo, changedAmt);
                    userData.put("Savings", SavingsAccMap);
                    userData.saveInBackground();
                }

                //System.out.print(accountNo);
                Intent in =  new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });

    }


    @Override
    public void onClick(View view) {


    }

}
