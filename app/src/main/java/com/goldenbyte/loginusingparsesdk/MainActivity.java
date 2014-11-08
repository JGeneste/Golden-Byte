package com.goldenbyte.loginusingparsesdk;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends Activity{
    private Button btn_Withdraw = null;
    private Button btn_Deposit = null;
    private HashMap<String, ArrayList<String>> CheckingAccMap;
    private HashMap<String, ArrayList<String>> SavingsAccMap;
    private Double checkBal;
    private Double savingsBal;
    private TextView checkView;
    private TextView savingView;
    private ArrayList<String> checklist;
    private ArrayList<String> savingslist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
        btn_Withdraw = (Button) findViewById(R.id.btn_Withdraw);
        btn_Deposit = (Button) findViewById(R.id.btn_Deposit);
        //getParseData();

        //setContentView(R.layout.main);

        // Parent layout
        LinearLayout parentLayout = (LinearLayout)findViewById(R.id.layout);

        // Layout inflater
        LayoutInflater layoutInflater = getLayoutInflater();
        View view;
        HashMap<String, ArrayList<String>> chkMap = getParseAccountMap(false);
        HashMap<String, ArrayList<String>> savMap = getParseAccountMap(true);
        HashMap<String, ArrayList<String>> curMap = chkMap;
        Iterator<String> mapIter = chkMap.keySet().iterator();
        String acntType = "Checking";
        String accountNo = "default";
        boolean firstTime = true;
        while (mapIter.hasNext()){
            // Add the text layout to the parent layout
            view = layoutInflater.inflate(R.layout.text_layout, parentLayout, false);

            // In order to get the view we have to use the new view with text_layout in it
            TextView textView = (TextView)view.findViewById(R.id.text);
            accountNo = mapIter.next();
            textView.setText( accountNo +"         "+acntType+"        " +curMap.get(accountNo).get(0));

            // Add the text view to the parent layout
            parentLayout.addView(textView);
            //switch to savings when finished going through checking accounts
            System.out.println("whileloop");
            if(firstTime && !mapIter.hasNext()) {
                mapIter = savMap.keySet().iterator();
                curMap = savMap;
                acntType = "Savings   ";
                firstTime = false;
            }

        }

        btn_Withdraw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in =  new Intent(MainActivity.this,Withdraw.class);
                startActivity(in);
            }
        });

        btn_Deposit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent in =  new Intent(MainActivity.this,Deposit.class);
                startActivity(in);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public HashMap<String, ArrayList<String>> getParseAccountMap(boolean isSavings)
    {
        Parse.initialize(this, "tmvsMl1TNmP2aF93btAiBYVf7vyWGMFy84Eh5tL2", "m9XxV2k5JNRNZJPTpT5LDj1dJVCxJFNjXzvEYaVS");
        ParseUser userData= ParseUser.getCurrentUser();
        if(isSavings)
            return (HashMap<String, ArrayList<String>>)userData.get("Savings");
        else
            return (HashMap<String, ArrayList<String>>)userData.get("Checking");
    }
    public void getParseData()
    {
        ParseObject.registerSubclass(Account.class);
        Parse.initialize(this, "tmvsMl1TNmP2aF93btAiBYVf7vyWGMFy84Eh5tL2", "m9XxV2k5JNRNZJPTpT5LDj1dJVCxJFNjXzvEYaVS");
        ParseUser userData= ParseUser.getCurrentUser();
        CheckingAccMap = (HashMap<String, ArrayList<String>>)userData.get("Checking");
        SavingsAccMap = (HashMap<String, ArrayList<String>>)userData.get("Savings");
        for( String key : CheckingAccMap.keySet()) {
            checklist = CheckingAccMap.get(key);
            checkBal = 0.0;
            for(int i = 0; i < checklist.size(); i++) {
                //System.out.println((checklist.get(i)));
            }
        }

        for( String key: SavingsAccMap.keySet()){
            savingslist = SavingsAccMap.get(key);
            savingsBal = 0.0;
            for(int i = 0; i < savingslist.size(); i++){
                //System.out.println((savingslist.get(i)));
            }
        }

        //checkView = (TextView) findViewById(R.id.CheckingBal);
        //savingView = (TextView) findViewById(R.id.SavingsBal);
        String checkStr = Double.toString(checkBal);
        String saveStr = Double.toString(savingsBal);
        checkView.setText("$" + checkStr);
        savingView.setText("$" + saveStr);
    }



}
