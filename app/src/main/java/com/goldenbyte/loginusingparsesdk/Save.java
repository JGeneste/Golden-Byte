package com.goldenbyte.loginusingparsesdk;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Emerson on 11/10/14.
 */
public interface Save {
    void putAccountMap(HashMap<String, ArrayList<String>> account, boolean isSavings);
    User createUser();
    boolean saveUserData(User user);


}
