package com.goldenbyte.loginusingparsesdk;

import android.util.*;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.parse.*;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import com.goldenbyte.loginusingparsesdk.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity{
    Button btn_Checking = null;
    HashMap<String, ArrayList<Double>> AccMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
        btn_Checking = (Button) findViewById(R.id.btn_Checking);
        getParseData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void getParseData()
    {
        //Parse.initialize(this, "9nnstGAafsTLcaYzPl95Yzyh7dXf2ifgtNGwVdUe", "PlcXwWtuqnQRDDBMOTxm1OJs4JnFMaQU4CzgLmdV");
        ParseUser userData= ParseUser.getCurrentUser();
        System.out.println(userData.getEmail());
        AccMap = (HashMap<String, ArrayList<Double>>)userData.get("Savings2");
        for( String key : AccMap.keySet()) {
            ArrayList<Double> list = AccMap.get(key);
            System.out.print(key);
            System.out.println(list.size());
            for(int i = 0; i < list.size(); i++) {
               System.out.println(list.get(i));
            }

        }

    }



}
