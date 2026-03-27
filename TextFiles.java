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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Write (in this order): Name, Email, Graduation Year, and Username ");
        Scanner scan = new Scanner(System.in);
        String content = scan.nextLine();
        String filePath = "Contacts/contacts.txt";
        String outPath = "TextFiles/contacts.txt";
        String contentToWrite = content;
        
        
// Write to file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        writer.write(contentToWrite);
        System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.err.println("An error occurred while writing to the file: " +
        e.getMessage());
    }
// Read from file
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        System.out.println("\nReading from the file:");
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
    } catch (IOException e) {
        System.err.println("An error occurred while reading from the file: "
        + e.getMessage());
        }
    }
}