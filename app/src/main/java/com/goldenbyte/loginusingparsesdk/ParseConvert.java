package com.goldenbyte.loginusingparsesdk;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Emerson on 11/10/14.
 */
public class ParseConvert implements Save{
    private ParseUser pUser;

    public ParseConvert(ParseUser pUser)
    {
      pUser = pUser;

    }
    public void putAccountMap(HashMap<String, ArrayList<String>> account, boolean isSavings) {
        if(isSavings)
            pUser.put("Savings", account);
        else
            pUser.put("Checking", account);

    }



    public User createUser()
    {
        HashMap<String, ArrayList<String>> chkMap = (HashMap<String, ArrayList<String>>)pUser.get("Checking");
        HashMap<String, ArrayList<String>> savMap = (HashMap<String, ArrayList<String>>)pUser.get("Savings");
        ArrayList<Account> aList = new ArrayList<Account>();
        ArrayList<String> transList;


        for(String key: chkMap.keySet())
        {
           transList = chkMap.get(key);
           aList.add(new Account(transList, key, false));

        }
        for(String key: savMap.keySet())
        {
            transList = savMap.get(key);
            aList.add(new Account(transList, key, true));

        }

        return new User(aList);


    }

    public boolean saveUserData(User user)
    {
        ArrayList<String> list;
        ArrayList<Account> accountList;
        String accountKey;
        boolean isSavings;
        HashMap<String, ArrayList<String>> chkMap = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> savMap = new HashMap<String, ArrayList<String>>();
        accountList = user.getAccountList();
        for(int i = 0; i < accountList.size(); i++) {


            list = accountList.get(i).getTransactionList();
            accountKey = accountList.get(i).getAccountNo();
            if(isSavings = accountList.get(i).getIsSavings())
            {
                savMap.put(accountKey, list);

            }
            else
                chkMap.put(accountKey, list);
        }
        putAccountMap(savMap, true);
        putAccountMap(chkMap, false);

        return true; //< temp


    }
}
