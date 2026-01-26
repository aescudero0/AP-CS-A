/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.radiuscalc;

/**
 *
 * @author AEscudero2026
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class RadiusCalc {

    /**
     * @param args the command line arguments
     */
    //carry out calculations or circular items
    //such as a circle, a sphere, a cone, a column
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("This program calculates round item numbers such as \n" +
                "1 - Area of a circle (pi r^2)\n" +
                "2 - Volume of a sphere (4/3 pi r^3)\n" +
                "3 - Volume of a cone (pi r^2 h/3)\n" +
                "4 - Volume of a column (pi r^2 h)");
        boolean isvalid = false;
 do {
    try {
        System.out.println("Type the number for which one you want to calculate (1-4):");
        int i = s.nextInt();
        if (i == 1) {
            isvalid = true;
            aCalc();
        } else if (i == 2) {
            isvalid = true;
            vCalc();
        } else if (i == 3) {
            isvalid = true;
            cCalc2();
        } else if (i == 4) {
            isvalid = true;
            cCalc3();
        } else {
            System.out.println("Input is not valid! Enter a number 1 - 4!");
        }
    } catch (InputMismatchException e) {
        System.out.println("Input is not valid! Enter a number 1 - 4!");
        s.next();
    }
} while (!isvalid); 
    }
    public static void cCalc3(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your cone, then height of your cone");
        double r = s.nextDouble();
        double h = s.nextDouble();
        double result = Math.PI * Math.pow(r, 2) * h;
        System.out.println("Volume = " + result);
    }
    
    public static void cCalc2(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your cone, then height of your cone");
        double r = s.nextDouble();
        double h = s.nextDouble();
        double result = (Math.PI * Math.pow(r, 2) * h)/3;
        System.out.println("Volume = " + result);
    }
    
    public static void aCalc(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your circle");
        double r = s.nextDouble();
        double result = Math.PI * Math.pow(r, 2);
        System.out.println("Area = " + result);
    }
    
    public static void vCalc(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the radius of your circle");
        double r = s.nextDouble();
        double result = (4 * Math.PI * Math.pow(r, 3))/3;
        System.out.println("Volume = " + result);
    }
}