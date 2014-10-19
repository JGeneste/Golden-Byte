import java.util.*;

public class DataBase
{

    private static Map<String, Map<String, Account>> userIdMap;
    private static Map<String, Account> passwordMap;
    
    // database constructor
    public DataBase()
    {
      //create a hash map of a hashmap. Sorted by user ID and then password
      userIdMap = new HashMap<String, Map<String, Account>>();
      passwordMap = new HashMap<String,Account>();

    }

    public void addAccount(String name, String password, Account acnt)
    {
      passwordMap.put(password, acnt);
      userIdMap.put(name, passwordMap);
    }
    public Account getAccount(String user, String password)
    {
      
      return userIdMap.get(user).get(password);
    }
}
