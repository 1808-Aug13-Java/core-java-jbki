package main;
import java.util.Scanner;
import java.util.*;
import java.io.Console;
import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.nio.file.*;
import java.nio.*;
import java.nio.file.StandardOpenOption;
class NegativeFloatException extends RuntimeException {}
public class BankDriver {
  static final String CREATE_USR = "create username: ";
  static final String CREATE_PWD = "create password: ";
  static final String USR_TAKEN = "username taken";
  static final String GET_USR = "enter username: "; 
  static final String GET_PWD = "enter password: ";
  static final String DEPOSIT_AMT = "enter deposit amount: ";
  static final String WITHDRAW_AMT = "enter withdrawal amount: ";
  static final String BALANCE = "balance";
  static final String DELIMIT = " "; //delimiter used by scanner to parse user files
  public static void main(String ... args) {
    float amount = 0;
    while(true) {
      Console c = System.console();
      String username = "";
      String password = "";
      System.out.println("1. First time login");
      System.out.println("2. Login");
      System.out.println("3. exit");
      int select = Integer.parseInt(c.readLine());
      switch (select) {
        case 1: 
                do {
                  username = createUsername(CREATE_USR);
                } while(usernameTaken(username, USR_TAKEN));
                password = createPassword(CREATE_PWD);
                
                try {
                  File file = new File(username);
                  file.createNewFile();
                  BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                  writer.write("username: " + username + '\n');
                  writer.write("password: " + password + '\n');
                  writer.write("balance: " + password + '\n');
                  writer.close();
                }
                catch(FileNotFoundException e) {
                  e.printStackTrace();
                }
                catch(IOException e) {
                  //do something
                }
                catch(Exception e) {
                  //do smething
                }
                break;
        case 2:
                //TODO Uncomment
                //username = getUsername(GET_USR); 
                //password = getPassword(GET_PWD); 
                username = "jbki";
                System.out.println("");
                //catch bad login attempts
                if(!(verify(username, password))) {
                  System.out.println("login failed");
                }

                //logged in, show menu 
                try {
                  boolean loggedin = true;
                  while(loggedin) {
                    File file = new File(username); //retrieve file of the username
                    showTransactionsMenu();
                    select = Integer.parseInt(c.readLine()); 
                    switch (select) {
                      case 1: 
                        amount = Float.parseFloat(c.readLine(DEPOSIT_AMT));
                        deposit(file, amount);
                        break;
                      case 2:
                        do {
                          amount = Float.parseFloat(c.readLine(WITHDRAW_AMT));
                        } while(!withdraw(file, amount));
                        break;
                      case 3:
                        view(file);
                        break;
                      case 4:
                        logout(file);
                        loggedin = false;
                        break;
                      default:
                       System.out.println("don't know that option"); 
                    }
                  }
                }
                catch(IOException e) {
                  e.printStackTrace();
                }
                finally {
                  System.out.println("");
                }
                break;
        case 3: 
          System.out.println("bye");
          System.exit(0);
          break;
        default: System.out.println("I don't know what you want");
      }
    }
  }

  public static boolean verify(String u, String p) {
    return true;
  }
  protected static String createUsername(String msg) {
    Console c = System.console();
    return c.readLine(msg);
  }
//TODO throw username taken error
  protected static String createPassword(String msg) {
    Console c = System.console();
    System.out.println(msg);
    return c.readPassword().toString();
  }
  protected static boolean usernameTaken(String username, String msg) {
    boolean taken = false;
    if(taken) {
      System.out.print(msg);
      return taken; //taken = true
    }
    return taken; //taken = false
  }

  protected static String getUsername(String msg) {
    Console c = System.console();
    return c.readLine(msg);
  }
  protected static String getPassword(String msg) {
    Console c = System.console();
    System.out.println(msg);
    return c.readPassword().toString();
  }

  protected static void showTransactionsMenu() {
    System.out.println("1. deposit");
    System.out.println("2. withdraw");
    System.out.println("3. view");
    System.out.println("4. logout");
  }
  protected static Scanner getScanLine(Scanner sc, String target) {
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
  protected static void deposit(File f, float amount) throws IOException {
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

  protected static boolean withdraw(File f, float amount) throws IOException, NegativeFloatException {
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

  protected static void view(File f) throws IOException {
    BufferedReader reader = Files.newBufferedReader(f.toPath());
    Scanner sc = new Scanner(reader);
    String BALANCE = "balance:"; //scanner returns token string, which is searched for with contains
    while(sc.hasNext())
    {
      try {
        if(sc.next().contains(BALANCE)) 
        {
          int balance = sc.nextInt();
          System.out.println("balance: " + balance);
        }
        else continue;
      }
      catch(NoSuchElementException e) {
        e.printStackTrace();
        break;
      }
    }
    reader.close();
  }

  protected static void logout(File f) {
  }


}
