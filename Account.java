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

  public Account(String name, String password)
  {
    this.name = name;
    this.password = password;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPassword()
  {
    return this.password;
  }
  
}
