package main;
import java.util.Scanner;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.nio.*;
class NegativeFloatException extends RuntimeException {}
public class BankDriver {
  public static void main(String ... args) {
    float amount = 0;
    while(true) {
      Console c = System.console();
      String username = "";
      char[] password = null;
      System.out.println("1. First time login");
      System.out.println("2. Login");
      System.out.println("3. exit");
      int select = Integer.parseInt(c.readLine());
      switch (select) {
        case 1: 
                do {
                  username = Bank.createUsername(Bank.CREATE_USR);
                } while(Bank.usernameTaken(username, Bank.USR_TAKEN));
                password = Bank.createPassword(Bank.CREATE_PWD);

                try {
                  File file = new File(username);
                  file.createNewFile();
                  BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                  writer.write("username: " + username.toString() + '\n');
                  writer.write("password: ");
                  writer.write(password);
                  writer.write('\n');
                  writer.write("balance: " + "0" + '\n');
                  writer.close();
                }
                catch(FileNotFoundException e) {
                  e.printStackTrace();
                }
                catch(Exception e) {
                  e.printStackTrace();
                }
                break;
        case 2:
               username = Bank.getUsername(Bank.GET_USR); 
               password = Bank.getPassword(Bank.GET_PWD); 
               // username = "jbki";

               // System.out.println("");
                //catch bad login attempts
                if(!(Bank.verify(username, password))) {
                  System.out.println("login failed");
                  continue; //go back to main menu if wrong stuff
                }

                //logged in, show menu 
                  boolean loggedin = true;
                  File file = new File(username); //retrieve file of the username
                                      
                  while(loggedin) {
                    Bank.showTransactionsMenu();
                    try {
                      select = Integer.parseInt(c.readLine());  //getting input
                      switch (select) {
                        case 1: 
                          amount = Float.parseFloat(c.readLine(Bank.DEPOSIT_AMT));
                          Bank.deposit(file, amount);
                          break;
                        case 2:
                          amount = Float.parseFloat(c.readLine(Bank.WITHDRAW_AMT));
                          Bank.withdraw(file, amount);
                          break;
                        case 3:
                          Bank.view(file);
                          break;
                        case 4:
                          Bank.logout(file);
                          loggedin = false;
                          break;
                        default:
                         System.out.println("don't know that option"); 
                      }
                    }
                    catch(NumberFormatException e) {
                      System.out.println("bad input");
                      continue;
                    }
                    catch(IOException e) {
                      e.printStackTrace();
                    }
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



}
