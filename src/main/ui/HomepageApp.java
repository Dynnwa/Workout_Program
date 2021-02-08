package ui;

import model.Exercise;
import model.Program;

import java.util.Scanner;

//Workout Program
public class HomepageApp {
    private Program armprogram;
    private Program legprogram;
    private Program abprogram;
    private Exercise extension;
    private Exercise curl;
    private Exercise deadlift;
    private Exercise squat;
    private Exercise core;
    private Exercise abs;
    private Scanner input;
    private boolean keepgoing = true;

    // EFFECTS: runs the program application
    public HomepageApp() {
        runProgram();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runProgram() {
        keepgoing = true;
        String command = null;

        init();

        while (keepgoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepgoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nStart training!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        Program program = new Program();
        if (command.equals("start")) {
            System.out.println("Do you want to start with a pre-made?");
            System.out.println("yes or no");
            String response = input.next();
            if (response.equals("yes")) {
                program = getProgram();
            }
            program = adduserexercise(program);
            program = removeExercise(program); //
            program = changetoAlt(program); //
            System.out.println("Here is your program!");
            System.out.println(program.printExercises());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public Program getProgram() {
        Program program = new Program();
        while (keepgoing) {
            System.out.println("What are your goals?");
            System.out.println("arms, legs, or abs?");
            String goal = input.next();

            if (goal.equals("arms")) {
                program = armprogram;
                System.out.println(armprogram.printExercises());
                keepgoing = false;
            } else if (goal.equals("legs")) {
                program = legprogram;
                System.out.println(legprogram.printExercises());
                keepgoing = false;
            } else if (goal.equals("abs")) {
                program = abprogram;
                System.out.println(abprogram.printExercises());
                keepgoing = false;
            } else {
                System.out.println("Choose Again:\n"); // need this to restart
            }
        }
        return program;
    }

    public Program adduserexercise(Program currentporgram) {
        System.out.println("Do you want to add your own exercise?");
        System.out.println("yes or no");
        String response = input.next();
        Exercise additionexercise;
        //  boolean keepgoing = true;

        while (response.equals("yes")) {
            System.out.println("Add the exercise, a machine alternative, the muscle, sets and reps");
            String exercise = input.next();
            String alternative = input.next();
            String muscle = input.next();
            int sets = input.nextInt();
            int reps = input.nextInt();
            additionexercise = new Exercise(exercise, alternative, muscle, sets, reps, false);
            currentporgram.addExercise(additionexercise);
            System.out.println("Wanna add another?"); // how to make a loop
            response = input.next();
        }
        System.out.println(currentporgram.printExercises());
        return currentporgram;
    }

    public Program removeExercise(Program currentprogram) {
        System.out.println("Do you want to remove an exercise?");
        System.out.println("Yes or No");
        String response = input.next();

        while (response.equals("yes")) {
            System.out.println("Which exercise?");
            String name = input.next();
            currentprogram.removeanExercise(name);
            System.out.println("Wanna remove another?");
            response = input.next();
        }
        System.out.println(currentprogram.printExercises());
        return currentprogram;
    }

    public Program changetoAlt(Program currentprogram) {
        System.out.println("Do you wanna change any exercises to a machine alternative?");
        System.out.println("Yes or No");
        String response = input.next();

        while (response.equals("yes")) {
            System.out.println("Which exercise?");
            String name = input.next();
            currentprogram.switchExercise(name);
            System.out.println("Wanna change another?");
            response = input.next();
        }
        System.out.println(currentprogram.printExercises());
        return currentprogram;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tstart");
        System.out.println("\tquit");
    }

    // MODIFIES: this
    // EFFECTS: creates exercises and programs
    private void init() {
        extension = new Exercise("Skullcrusher", "Press down", "tricep",
                3, 8, false);
        curl = new Exercise("Barbell curl", "Band curl", "bicep",
                4, 10, false);
        deadlift = new Exercise("Deadlift", "Cable dl", "hamstring",
                3, 3, false);
        squat = new Exercise("Barbell squat", "Hack squat", "quad",
                4, 8, false);
        core = new Exercise("Leg raise", "Cable hold", "core",
                3, 8, false);
        abs = new Exercise("Situp", "Machine Crunch", "abs",
                5, 10, false);

        armprogram = new Program();
        armprogram.addExercise(extension);
        armprogram.addExercise(curl);

        legprogram = new Program();
        legprogram.addExercise(deadlift);
        legprogram.addExercise(squat);

        abprogram = new Program();
        abprogram.addExercise(core);
        abprogram.addExercise(abs);

        input = new Scanner(System.in);
    }

}
