package ui;

import java.io.FileNotFoundException;

// Main runs the program
public class Main {
    public static void main(String[] args) {
        try {
            new HomePageGui();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        /*
        try {
            new HomepageApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        /*
         */
    }
}