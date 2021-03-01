package ui;

import model.Exercise;
import model.Program;
import org.json.JSONArray;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Workout Program
public class HomepageApp {
    private static final String JSON_FILE = "./data/program.json";
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
    private Program program = new Program();
    private boolean keepgoing = true;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the program application
    public HomepageApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_FILE);
        jsonReader = new JsonReader(JSON_FILE);
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

            if (command.equals("q")) {
                keepgoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println(program.printExercises());
        System.out.println("\nStart training!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("p")) {
            getProgram();
        } else if (command.equals("a")) {
            addUserExercise();
        } else if (command.equals("r")) {
            removeExercise();
        } else if (command.equals("s")) {
            changetoAlt();
        } else if (command.equals("save")) {
            saveProgram();
        } else if (command.equals("load")) {
            loadProgram();
        } else {
            System.out.println("Selection not valid...");
        }
        System.out.println(program.printExercises());
    }

    // EFFECTS: saves the program to the prorgam.json file
    // MODELLED AFTER THE JSON PROJECT
    private void saveProgram() {
        try {
            jsonWriter.startWriter();
            jsonWriter.write(program);
            jsonWriter.stopWriter();
            System.out.println("Saved prorgram to " + JSON_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE);
        }
    }

    // EFFECTS: loads the old program from the program.json file
    // MODELLED AFTER THE JSON PROJECT
    private void loadProgram() {
        try {
            program = jsonReader.read();
            System.out.println("Loaded program from " + JSON_FILE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE);
        }
    }

    // modifies: this
    // effects: returns a premade program that trains the users input muscle group
    public void getProgram() {
        System.out.println("What are your goals?");
        System.out.println("arms, legs, or abs?");
        String goal = input.next();

        if (goal.equals("arms")) {
            program = armprogram;
            System.out.println(armprogram.printExercises());
        } else if (goal.equals("legs")) {
            program = legprogram;
            System.out.println(legprogram.printExercises());
        } else if (goal.equals("abs")) {
            program = abprogram;
            System.out.println(abprogram.printExercises());
        } else {
            System.out.println("Invalid Selection\n");
        }

    }

    // modies: this
    // effects: returns the new porgram after user has added their own exercise
    public void addUserExercise() {
        Exercise additionexercise;

        System.out.println("Add the exercise:");
        input.nextLine();
        String exercise = input.nextLine();
        System.out.println("Add the muscle:");
        String muscle = input.next();
        System.out.println("Add the alternative:");
        input.nextLine();
        String alternative = input.nextLine();
        System.out.println("Add the sets:");
        int sets = input.nextInt();
        System.out.println("Add the reps:");
        int reps = input.nextInt();
        additionexercise = new Exercise(exercise, alternative, muscle, sets, reps, false);
        program.addExercise(additionexercise);

        System.out.println(program.printExercises());
    }

    // modifies: this
    // effects: returns the new porgram after user has removed the given exercise
    public void removeExercise() {
        System.out.print("Which exercise:");
        input.nextLine();
        String name = input.nextLine();
        program.removeanExercise(name);
        System.out.println("Wanna remove another?");

        System.out.println(program.printExercises());
    }

    // modifies: this
    // effects: changes one of the user input to the alternative machine exexrcise
    public void changetoAlt() {
        System.out.println("Which exercise?");
        input.nextLine();
        String name = input.nextLine();
        program.switchExercise(name);
        System.out.println("Wanna change another?");

        System.out.println(program.printExercises());
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> start with a pre made program");
        System.out.println("\ta -> add your own exercise to the program");
        System.out.println("\tr -> romove and exercise from the program");
        System.out.println("\ts -> switch and exercise to an alternative");
        System.out.println("\tsave -> save program");
        System.out.println("\tload -> load previous program");
        System.out.println("\tq -> quit");
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
