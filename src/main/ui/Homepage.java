package ui;

import com.sun.xml.internal.org.jvnet.fastinfoset.sax.FastInfosetReader;
import model.Exercise;
import model.Program;

import java.util.Scanner;

//Workout Program
public class Homepage {
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

    // EFFECTS: runs the program application
    public void programApp() {
        runProgram();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runProgram() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThere's always next year!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        Program program;
        if (command.equals("custom")) {
            createProgram();
        } else if (command.equals("pre-made")) {
            program = getProgram();
            program = adduserexercise(program);
            System.out.println("Which exercise do you want to change to a machine alternative?");
            String exercise = input.next();
            program.switchExercise(exercise);
            System.out.println(program.printExercises());
        } else {
            System.out.println("Selection not valid...");
        }
    }

    public Program getProgram() {
        System.out.println("What are your goals?");
        System.out.println("arms, legs, or abs?");
        String goal = input.next();

        if (goal == "arms") {
            return armprogram;
            System.out.println(armprogram.printExercises());
        } else if (goal == "legs") {
            return legprogram;
            System.out.println(legprogram.printExercises());
        } else if (goal == "abs") {
            return abprogram;
            System.out.println(abprogram.printExercises());
        } else {
            System.out.println("Choose Again\n"); // need this to restart
        }
    }

    public Program adduserexercise(Program currentporgram) {
        System.out.println("Do you want to add your own exercise?");
        System.out.println("yes or no");
        String response = input.next();

        if (response == "yes") {
            String exercise = input.next();
            String alternative = input.next();
            String muscle = input.next();
            int sets = input.nextInt();
            int reps = input.nextInt();
            Exercise additionexercise = new Exercise(exercise,alternative,muscle,sets,reps,false);

            currentporgram.addExercise(additionexercise);
            // need this to restart
            return currentporgram;
        }
        return currentporgram;

    }

    public void createProgram() {

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcustom");
        System.out.println("\tpre-made");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates exercises and programs
    private void init() {
        extension = new Exercise("Skullcrusher", "Press down", "tricep",
                3,8,false);
        curl = new Exercise("Barbell curl", "Band curl", "bicep",
                4,10,false);
        deadlift = new Exercise("Deadlift", "Cable dl", "hamstring",
                3,3,false);
        squat = new Exercise("Barbell squat", "Hack squat", "quad",
                4,8,false);
        core = new Exercise("Leg raise", "Cable hold", "core",
                3,8,false);
        abs = new Exercise("Situp", "Machine Crunch", "abs",
                5,10,false);

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
