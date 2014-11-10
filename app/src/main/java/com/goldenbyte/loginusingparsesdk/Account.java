package com.goldenbyte.loginusingparsesdk;

/**
 * Created by Emerson on 11/2/14.
 */
import java.util.*;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.*;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseClassName;

@ParseClassName("Account")
public class Account{
    boolean isSavings = true; //default to savings account
    String accountNo = "default"; //name of the account
    ArrayList<String> transList;

    public Account(ArrayList<String> list, String acntNo, boolean isSavings)
    {
        transList = list;
        accountNo = acntNo;
        this.isSavings = isSavings;
    }

    /*
    // flag for Internet connection status
    Boolean isInternetPresent = false;
    // Connection detector class
    ConnectionDetector cd;
    */
    /*public Account(String n, boolean isS)
    {
        name = n;
        isSavings = isS;

    }*/
    


    public String getAccountNo()
    {

        return accountNo;
    }
    public ArrayList<String> getTransactionList()
    {
       return transList;

    }
    public boolean getIsSavings()
    {
        return this.isSavings;
    }
}



