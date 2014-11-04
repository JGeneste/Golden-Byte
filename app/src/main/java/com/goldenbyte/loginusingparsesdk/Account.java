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

@ParseClassName("Account")
public class Account extends ParseObject{
    boolean isSavings = true; //default to savings account
    String name; //name of the account

    // flag for Internet connection status
    Boolean isInternetPresent = false;
    // Connection detector class
    ConnectionDetector cd;
    /*public Account(String n, boolean isS)
    {
        name = n;
        isSavings = isS;

    }*/
    public void setName(String n)
    {
        this.name=n;

    }
    public void setIsSavings(boolean tf)
    {
        this.isSavings = tf;

    }
    public String getName()
    {
        return this.name;
    }



}
