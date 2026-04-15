/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package textfiles;

/**
 *
 * @author AEscudero2026
 */

import java.io.*;
import java.util.*;
 
public class TextFiles {
   // create array lists
   static ArrayList<Contact> contacts = new ArrayList<>();
 // set the filepath
   static String filePath = "Contacts/contacts.txt";
 
   public static void main(String[] args) {
       loadFromFile();
 // scan for users input
       Scanner scan = new Scanner(System.in);
       boolean valid = true;
 
       while (valid) {
           System.out.println("\nWhat would you like to do?");
           System.out.println("A - add a contact");
           System.out.println("E - order by email");
           System.out.println("Y - order by grad year");
           System.out.println("N - order by name");
           System.out.println("Q - quit");
 
           String action = scan.nextLine();
      // use if else statements to continue user input
           if (action.equalsIgnoreCase("A")) {
               System.out.println("Enter Name:");
               String name = scan.nextLine();
 
               System.out.println("Enter Email:");
               String email = scan.nextLine();
 
               int year = 0;
               boolean validYear = false;
               while (!validYear) {
                   System.out.println("Enter Graduation Year:");
                   try {
                       year = Integer.parseInt(scan.nextLine());
                       validYear = true;
                   } catch (NumberFormatException e) {
                       System.out.println("Invalid Input! Please Enter A Number!");
                   }
               }
 
               System.out.println("Enter Username:");
               String username = scan.nextLine();
 // with info create the new contact and add to array list
               Contact c = new Contact(name, email, year, username);
               contacts.add(c);
               saveToFile();
               System.out.println("Contact added.");
 // set up rest of if else 
           } else if (action.equalsIgnoreCase("E")) {
               contacts.sort((a, b) -> a.email.compareTo(b.email));
               printContacts();
 
           } else if (action.equalsIgnoreCase("Y")) {
               contacts.sort((a, b) -> a.gradYear - b.gradYear);
               printContacts();
 
           } else if (action.equalsIgnoreCase("N")) {
               contacts.sort((a, b) -> a.name.compareTo(b.name));
               printContacts();
 
           } else if (action.equalsIgnoreCase("Q")) {
               valid = false;
 
           } else {
               System.out.println("Not a valid option, try again.");
           }
       }
   }
 // load contacts from the file with the ability to create a new file if there is not already one
   static void loadFromFile() {
       try {
           File myFile = new File(filePath);
 
           if (!myFile.exists()) {
               if (myFile.getParentFile() != null) {
                   myFile.getParentFile().mkdirs();
               }
               myFile.createNewFile();
               System.out.println("Created new file: " + filePath);
               return;
           }
 // read the new file being created
           Scanner reader = new Scanner(myFile);
           while (reader.hasNextLine()) {
               String line = reader.nextLine();
               String[] data = line.split(",");
               if (data.length >= 4) {
                   Contact c = new Contact(
                       data[0].trim(),
                       data[1].trim(),
                       Integer.parseInt(data[2].trim()),
                       data[3].trim()
                   );
                   contacts.add(c);
               }
           }
           reader.close();
           System.out.println("Loaded " + contacts.size() + " contacts from file.");
 
       } catch (IOException e) {
           System.out.println("FileSystem Error: " + e.getMessage());
       }
   }
 // save new actions to the file created or the file alreayd in place
   static void saveToFile() {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
           for (Contact c : contacts) {
               writer.write(c.toString());
               writer.newLine();
           }
           System.out.println("Successfully saved contacts.");
       } catch (IOException e) {
           System.out.println("An error occurred while writing to the file: " + e.getMessage());
       }
   }
 // save the contacts and print them out in the order specified
   static void printContacts() {
       System.out.println("\n--- Contacts ---");
       for (Contact c : contacts) {
           System.out.println(c.name + " , " + c.email + " , " + c.gradYear + " , " + c.username);
       }
   }
}
// create the contact class
class Contact {
   String name;
   String email;
   int gradYear;
   String username;
 // assign the input values to their designated variables
   public Contact(String name, String email, int gradYear, String username) {
       this.name = name;
       this.email = email;
       this.gradYear = gradYear;
       this.username = username;
   }
 // create the toString
   public String toString() {
       return name + "," + email + "," + gradYear + "," + username;
   }
}
