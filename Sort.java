/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sort;

/**
 *
 * @author AEscudero2026
 */
public class Sort {
    public static void main(String[] args)
    {
        // contacts from example code
        Contact[] friends = new Contact[9];
        friends[0] = new Contact("610-555-7384", "Smith", "John");
        friends[1] = new Contact("215-555-3827", "Barnes", "Sarah");
        friends[2] = new Contact("733-555-2969", "Riley", "Aaron");
        friends[3] = new Contact("663-555-3984", "Ramone", "Laura");
        friends[4] = new Contact("464-555-3489", "Smith", "Larry");
        friends[5] = new Contact("322-555-2284", "Phelps", "Frank");
        friends[6] = new Contact("804-555-9066", "Guzman", "Mario");
        friends[7] = new Contact("464-555-3489", "Grant", "Marsha");
        friends[8] = new Contact("464-555-3489", "Ramone", "Joey");
        // print out the selection sort method and the insertion sort method 
        Sorting.selectionSort(friends);
        System.out.println("---- Selection Sort ----");
        for (Contact friend : friends)
            System.out.println(friend);
        
        Sorting.insertionSort(friends);
        System.out.println("\n---- Insertion Sort ----");
        for (Contact friend : friends)
            System.out.println(friend);
    }
}
// selection sort from example code
class Sorting{
    public static void selectionSort(Comparable[] list)
    {
        int min;
        Comparable temp;
        for (int index = 0; index < list.length-1; index++)
        {
            min = index;
            for (int scan = index+1; scan < list.length; scan++)
                if (list[scan].compareTo(list[min]) < 0){
                    min = scan;
                }
            temp = list[min];
            list[min] = list[index];
            list[index] = temp;
        }
    }
    // insertion sort fron example code (had to change the +'s to -'s because there was a execution error)
    public static void insertionSort(Comparable[] list)
    {
        for (int index = 1; index < list.length; index++)
        {
            Comparable key = list[index];
            int position = index;
            while (position > 0 && key.compareTo(list[position-1]) < 0)
            {
                list[position] = list[position-1];
                position--;
            }
            list[position] = key;
        }
    }
}

class Contact implements Comparable
{
    private String phone, firstName, lastName;
   // set the contact variables
    public Contact(String telephone, String last, String first)
    {
        phone = telephone;
        lastName = last;
        firstName = first;
    }
    // create toString for desired output
    public String toString()
    {
        return phone + "\t" + lastName + ", " + firstName;
    }
   // uses last and first names for ordering
    public boolean equals(Object other)
    {
        return (lastName.equals(((Contact)other).getLastName()) &&
        firstName.equals(((Contact)other).getFirstName()));
    }
    
    public int compareTo(Object other)
    {
        // compare phone #'s and last names
        int result;
        String otherPhone = ((Contact)other).getPhone();
        String otherLast = ((Contact)other).getLastName();
        
        // sort by phone number first
        if (phone.equals(otherPhone))
        {
            // sort by last name after phone
                result = lastName.compareTo(otherLast);
        }
        else
            result = phone.compareTo(otherPhone);
        // return the result
        return result;
    }
    // getters for each variable
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getPhone()
    {
        return phone;
    }
}

