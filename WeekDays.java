/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.weekdays;

/**
 *
 * @author AEscudero2026
 */
import java.util.Arrays;
import java.util.Random;

public class WeekDays {

    public static void main(String[] args) {

        // create the array list of all days
        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        // print out list
        System.out.println("All 7 Days:");
        printDays(weekDays);
        // resize the last to only the first 5 days
         weekDays = Arrays.copyOf(weekDays, 5);
         // print out new list
       System.out.println("\nWeekdays Only:");
        printDays(weekDays);

        // using the shuffle code below, shuffle the days
        shuffle(weekDays);
        // print out the new shuffled days
        System.out.println("\nWeekdays Shuffled:");
        printDays(weekDays);
    }

    // printing days each one on its own row
    static void printDays(String[] days) {
        for (String day : days) {
            System.out.println(day);
        }
    }

    // shuffling the cards
    static void shuffle(String[] array) {
        Random random = new Random();

        // going backwards to card 1
        for (int i = array.length - 1; i > 0; i--) {

            // randomizing it
            int j = random.nextInt(i + 1);

            // swap cards at positions i and j
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}


