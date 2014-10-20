/**
 * 
 */
//package makeAccount;

/**
 * @author Emerson
 *
 */
public class Account
{
  private static String name;
  private static String password;
  private static String address; //user address
  private static String type; //account type


  public Account(String n, String p, String type, String address)
  {
    this.name = n;
    this.password = p;
    this.type = type;
    this.address = address;

  }

  public String getName()
  {
    return this.name;
  }

  public String getPassword()
  {
    return this.password;
  }
  public String getType()
  {
    return this.type;
  }

  public String getAddress()
  {
    return this.address;
  }
  
}
