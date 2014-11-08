package com.goldenbyte.loginusingparsesdk;

/**
 * Created by Jun on 11/7/2014.
 */
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
public class Deposit extends Activity implements View.OnClickListener {

    private EditText depositAmt;
    private Double Amt;
    private Button mConfirm;
    private Button mCancel;
    private Spinner mSpinner;
    //ConnectionDetector cd;
    private HashMap<String, ArrayList<String>> CheckingAccMap;
    private HashMap<String, ArrayList<String>> SavingsAccMap;
    private ArrayList<String> checklist;
    private ArrayList<String> savingslist;
    ArrayList<String> changedAmt;
    private Double CheckBal;
    private Double SavingsBal;
    private int numOfChecking;
    private int numOfSaving;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_deposit);
        Parse.initialize(this, "tmvsMl1TNmP2aF93btAiBYVf7vyWGMFy84Eh5tL2", "m9XxV2k5JNRNZJPTpT5LDj1dJVCxJFNjXzvEYaVS");
        final ParseUser userData = ParseUser.getCurrentUser();


        depositAmt = (EditText) findViewById(R.id.depositAmt);
        mConfirm = (Button) findViewById(R.id.DepConfirm);
        mCancel = (Button) findViewById(R.id.DepCancel);
        mSpinner = (Spinner) findViewById(R.id.spinner2);

        mCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in =  new Intent(Deposit.this,MainActivity.class);
                startActivity(in);
            }
        });

        List<String> spinnerArray = new ArrayList<String>();
        CheckingAccMap = (HashMap<String, ArrayList<String>>)userData.get("Checking");
        SavingsAccMap = (HashMap<String, ArrayList<String>>)userData.get("Savings");
        //numOfChecking = numOfSaving = 1;
        for( String key : CheckingAccMap.keySet()) {
            checklist = CheckingAccMap.get(key);
            CheckBal = 0.0;
            //numOfChecking += 1;
            if(checklist.size() > 1) {
                for (int i = 1; i < checklist.size(); i++) {
                    CheckBal += Double.parseDouble(checklist.get(i));
                }
            }
                spinnerArray.add("Chk " + key + " $" + CheckBal);

        }
        for( String key: SavingsAccMap.keySet()){
            ArrayList<String> savingslist = SavingsAccMap.get(key);
            SavingsBal = 0.0;

            //numOfSaving += 1;
            if(savingslist.size() > 1 ) {

                for (int i = 1; i < savingslist.size(); i++) {
                    SavingsBal += Double.parseDouble(savingslist.get(i));
                }
            }
                spinnerArray.add("Sav " + key + " $" + SavingsBal);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);


        mConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String amt = depositAmt.getEditableText().toString();
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
                    changedAmt.add(String.valueOf(actualAmt));
                    changedAmt.set(0, String.valueOf(CheckBal+actualAmt)); //update balance at 0 index
                    CheckingAccMap.put(accountNo, changedAmt);
                    userData.put("Checking", CheckingAccMap);
                    userData.saveInBackground();
                }
                else {
                    changedAmt = SavingsAccMap.get(accountNo);
                    //Make sure to check for overdrafting later
                    changedAmt.add(String.valueOf(actualAmt));
                    changedAmt.set(0, String.valueOf(actualAmt + SavingsBal)); //update balance at 0 index
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