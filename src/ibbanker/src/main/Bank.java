package main;
import java.io.Console;
import java.util.*;
import java.io.*;


public class Bank {
  public static final String CREATE_USR = "create username: ";
  static final String CREATE_PWD = "create password: ";
  static final String USR_TAKEN = "username taken";
  static final String GET_USR = "enter username: "; 
  static final String GET_PWD = "enter password: ";
  static final String DEPOSIT_AMT = "enter deposit amount: ";
  static final String WITHDRAW_AMT = "enter withdrawal amount: ";
  static final String DELIMIT = " "; //delimiter used by scanner to parse user files
  static final String BALANCE = "balance";
  public static void print(Object o) {
    System.out.println(o);
  }
  static void view(File f) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(f));
    //BufferedReader reader = Files.newBufferedReader(f.toPath());
    Scanner sc = new Scanner(reader);
    //String BALANCE = "balance:"; //scanner returns token string, which is searched for with contains
    while(sc.hasNext())
    {
      try {
        String temp = sc.next();
        //System.out.print("temp="+temp);
        if(temp.contains(BALANCE)) 
        {
          float balance = sc.nextFloat();
          System.out.println("balance: " + balance);
        }
      }
      catch(InputMismatchException e) {
        continue;
        //System.out.println("HEYO");
        //e.printStackTrace();
      }
      catch(NoSuchElementException e) {
        e.printStackTrace();
        break;
      }
    }
    reader.close();
  }
  public static boolean verify(String u, String p) {
    
    return true;
  }
  static String createUsername(String msg) {
    Console c = System.console();
    return c.readLine(msg);
  }
//TODO throw username taken error
  static String createPassword(String msg) {
    Console c = System.console();
    System.out.println(msg);
    return c.readPassword().toString();
  }
  static boolean usernameTaken(String username, String msg) {
    boolean taken = false;
    if(taken) {
      System.out.print(msg);
      return taken; //taken = true
    }
    return taken; //taken = false
  }

  static String getUsername(String msg) {
    Console c = System.console();
    return c.readLine(msg);
  }
  static String getPassword(String msg) {
    Console c = System.console();
    System.out.println(msg);
    return c.readPassword().toString();
  }

  static void showTransactionsMenu() {
    System.out.println("1. deposit");
    System.out.println("2. withdraw");
    System.out.println("3. view");
    System.out.println("4. logout");
  }
  static Scanner getScanLine(Scanner sc, String target) {
    while(sc.hasNext())
    {
      try {
        if(sc.next().contains(target)) 
        {
          return sc;
        }
        else continue;
      }
      catch(NoSuchElementException e) {
        e.printStackTrace();
        break;
      }
    }
    return sc;
  }
  static void deposit(File f, float amount) throws IOException {
    String contents = "";
    BufferedReader reader = new BufferedReader(new FileReader(f));
    BufferedWriter writer = null; 
    String line = "";
    Scanner sc = new Scanner(reader);

    while((line = reader.readLine()) != null) {
      if(line.contains(BALANCE)) {
        String[] lines = line.split(DELIMIT);
        float curBalance = Float.parseFloat(lines[1]);
        float newBalance = curBalance + amount; 
        line = lines[0] + DELIMIT + Float.toString(newBalance); 
      }
      contents += line + "\n";
    }
    reader.close();
    sc.close();
     
    
    writer = new BufferedWriter(new FileWriter(f));
    writer.write(contents);
    //TODO put in finally 
    writer.close();
  }

  static boolean withdraw(File f, float amount) throws IOException, NegativeFloatException {
    String contents = "";
    BufferedReader reader = new BufferedReader(new FileReader(f));
    BufferedWriter writer = null; 
    String line = "";
    Scanner sc = new Scanner(reader);

    try {
      while((line = reader.readLine()) != null) {
        if(line.contains(BALANCE)) {
          String[] lines = line.split(DELIMIT);
          float curBalance = Float.parseFloat(lines[1]);
          float newBalance = curBalance - amount; 
          if(curBalance < amount) {
            throw new NegativeFloatException();
          }
                        
          line = lines[0] + DELIMIT + Float.toString(newBalance); 
        }
        contents += line + "\n";
      }
    }
    catch(NegativeFloatException e) {
      System.out.println("You don't have enough balance");
      return false;
    }
    finally {
      reader.close();
      sc.close();
    }
     
    
    writer = new BufferedWriter(new FileWriter(f));
    writer.write(contents);
    //TODO put in finally 
    writer.close();
    return true;
  }


  static void logout(File f) {
  }
}


