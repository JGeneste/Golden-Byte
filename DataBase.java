import java.util.*;
import java.io.*;

public class DataBase
{

   // private static Map<String, Map<String, Account>> userIdMap;
   // private static Map<String, Account> passwordMap;
    private static Map<String, Account> accountMap;
    
    // database constructor
    public DataBase()
    {
      //create a hash map of a hashmap. Sorted by user ID and then password
     // userIdMap = new HashMap<String, Map<String, Account>>();
      //passwordMap = new HashMap<String,Account>();
      accountMap = new HashMap<String, Account>();

    }

    public void addAccount(String name, String password, Account acnt, FileWriter d)
    {
      //passwordMap.put(password, acnt);
      //userIdMap.put(name, passwordMap);
      accountMap.put(name+password, acnt);
      String tempStr = new String(name+" "+password+" "+ acnt.getType()+
              " "+acnt.getAddress());
      try{
        BufferedWriter out = new BufferedWriter(d);
        out.write(tempStr, 0, tempStr.length());
        out.newLine();
        out.close();
        System.out.println("finished writing to file");

      }
      catch(IOException e){
        System.out.println("There was a problem: " + e);
      }
    }
    public Account getAccount(String user, String password)
    {
      
      //return userIdMap.get(user).get(password);
      return accountMap.get(user+password);
    }
}
