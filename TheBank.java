import java.util.*;
import java.io.*;

public class TheBank
{
    public static int option; //which option the user chooses
    private static DataBase bankData;
    private static FileWriter dataFile; //where the data is writen

  public static void main(String[] args)
  {
    try{

      dataFile = new FileWriter("data.txt", true);
    }
    catch(IOException e){
      System.out.println("There is a problem: "+e);
    }
    bankData = new DataBase(); //create the bank database
    System.out.println("Welcome to the Bank");
    System.out.println("Please enter the number for the desired choice");
    System.out.println("\n1: Make a new account\n2: Login to an account");
    System.out.println("3: Exit");
    Scanner scan = new Scanner(System.in);
    option = scan.nextInt();

    switch(option)
    {
      case 1: createAccount(); 
              //System.out.println("creating new account");
            break;
      case 2: System.out.println("Logging in");
            break;
      case 3: System.out.println("Exiting");
            break;
      default: System.out.println("unknown entry, please try again");
    }
    scan.close();
  }

  

    public static void createAccount()
    {
  
      System.out.println("\nWhat type of account would you like to create?");
      Scanner scan = new Scanner(System.in);
      System.out.println("\n1: Savings\n2:Checking\n");
      
      option = scan.nextInt();
     
      System.out.println("Please enter account name without spaces");
      String name = scan.next();
      System.out.println("Please enter a password");
      String password = scan.next();
       if(option == 1)
       {
         System.out.println("Creating Savings Account");
         System.out.println("user name = "+ name +" password = "+ password);
         //create new savings account if doesn't exist
         Account savings = new Savings(name, password);
         bankData.addAccount(name, password, savings, dataFile);
         
       }
       else if(option == 2)
       {
        //create checking account
       }
       else
       System.out.println("unknown entry");
       
        //Temp test
        Account temp = bankData.getAccount(name, password);
        System.out.println("returned name = " + temp.getName());
        System.out.println("returned password = " + temp.getPassword());

    }

}
