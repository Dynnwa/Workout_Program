package ui;

import model.Program;

import java.io.FileNotFoundException;

// Main runs the program
public class Main {
    public static void main(String[] args) {
        try {
            new HomePageGui(new Program());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}