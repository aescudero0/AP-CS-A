/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recursionprogramming;

/**
 *
 * @author AEscudero2026
 */
import java.util.Scanner;

public class RecursionProgramming {
    
    public static void main(String[] args) {
        // scan for desired row
        Scanner scan = new Scanner(System.in);
        
        System.out.print("What row would you like to find (0 based)? : ");
        int n = scan.nextInt();
        // build desired row with buildRow code
        int[] result = buildRow(n);
        // print out the row # that was desired
        System.out.print("Row " + n + " is: ");
        for (int num : result) {
            // print row
            System.out.print(num + "  ");
        } 
    }
    
    // build desired row recursviely
    static int[] buildRow(int n) {
        // row 0 will always just start with 1
        if (n == 0) {
            return new int[]{1};
        }
        
        // grab the previous row first
        int[] previous = buildRow(n - 1);
        int[] current = new int[n + 1];
        
        // the first and last digits of each row are 1
        current[0] = 1;
        current[n] = 1;
        
        // # on new row equals the sum of the two digits above it
        for (int i = 1; i < n; i++) {
            current[i] = previous[i - 1] + previous[i];
        }
        // return current row
        return current;
    }
}

