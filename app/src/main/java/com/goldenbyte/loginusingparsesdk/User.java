package com.goldenbyte.loginusingparsesdk;

import java.util.ArrayList;

/**
 * Created by Emerson on 11/10/14.
 */
public class User {
    ArrayList<Account> accountList;
    public User(ArrayList<Account> al)
    {
        accountList = al;
    }
    public ArrayList<Account> getAccountList()
    {
        return this.accountList;
    }


}
