
public class DataBase
{

    private Hashmap userIdMap;
    private Hashmap passwordMap;
    
    public void createDataBase(String user, String password, Account a)
    {
      //create a hash map of a hashmap. Sorted by user ID and then password
      Map<String, Map<String, Account>> userIdMap = new Hashmap<userID,
          Map<String, Account>> passwordMap = new HashMap<password,
          account>();

    }

    public Account getAccount(String user, String password)
    {
      
      return null;//<--temporary
    }
}
