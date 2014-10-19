import java.util.*;
public class TheBank
{
    public static int option; //which option the user chooses


  public static void main(String[] args)
  {
  //  DataBase bankData = new DataBase(); //create the bank database
    System.out.println("Welcome to the Bank");
    System.out.println("Please enter the number for the desired choice");
    System.out.println("\n1: Make a new account\n2: Login to an account");
    System.out.println("3: Exit");
    Scanner scan = new Scanner(System.in);
    option = scan.nextInt();

    switch(option)
    {
      case 1: System.out.println("creating new account");
            break;
      case 2: System.out.println("Logging in");
            break;
      case 3: System.out.println("Exiting");
            break;
      default: System.out.println("unknown entry, please try again");
    }


  }




}
