package com.goldenbyte.loginusingparsesdk;

import android.widget.TextView;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.util.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import com.parse.*;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.goldenbyte.loginusingparsesdk.R;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Emerson on 11/6/14.
 */
public class AdminActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin);
        //btn_Checking = (Button) findViewById(R.id.btn_Checking);
        //getParseData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void acceptTransactions()
    {
        Parse.initialize(this, "9nnstGAafsTLcaYzPl95Yzyh7dXf2ifgtNGwVdUe", "PlcXwWtuqnQRDDBMOTxm1OJs4JnFMaQU4CzgLmdV");
        ParseUser userData= ParseUser.getCurrentUser();
        HashMap accMap = (HashMap<String, ArrayList<Double>>)userData.get("admin");
        /*for( String key : accMap.keySet())
        {


        }

        TextView nextAccount = (TextView) findViewById(R.id.accountNo);
        nextAccount.setText(userData.get()); */
    }


}
