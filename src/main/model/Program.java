package model;

import java.util.*;

import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Json;

// Program is a list of exercises. Programs can modify an individual exercise in the list with various methods
public class Program implements Json {
    private Map<String, List<Exercise>> program;

    public Program() {
        program = new HashMap<>();
    }

    public Map<String, List<Exercise>> getProgram() {
        return this.program;
    }

    // effects: returns the exercise
    public Exercise getExercise(String exercise) {
        String name = null;
        for (String key: program.keySet()) {
            List<Exercise> exercises = program.get(key);
            for (Exercise e: exercises) {
                name = e.getExercise();
                if (name == exercise) {
                    return e;
                }
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Exercise e is added to the program if it's not
    // already in the program
    public void addExercise(Exercise e) {
        if (!program.containsKey(e.getMuscle())) {
            List<Exercise> elist = new LinkedList<>();
            elist.add(e);
            program.put(e.getMuscle(), elist);
        } else {
            List<Exercise> elist = program.get(e.getMuscle());
            elist.add(e);
        }
    }

    // REQUIRES: Exercise e is an element of the Program
    // MODIFIES: this
    // EFFECTS: Exercise e is removed from the Program
    public void removeExercise(Exercise e) {
        if (program.containsKey(e.getMuscle())) {
            List<Exercise> elist = program.get(e.getMuscle());
            elist.remove(e);
        }
    }

    // EFFECTS: Returns true if Exercise e is in the program
    // and false otherwise
    public boolean containsExercise(Exercise e) {
        if (program.containsKey(e.getMuscle())) {
            List<Exercise> elist = program.get(e.getMuscle());
            return elist.contains(e);
        } else {
            return false;
        }
    }

    // EFFECTS: Returns the number of exercises in the program
    public int programLength() {
        int numexecises = 0;
        for (String key: program.keySet()) {
            numexecises = numexecises + program.get(key).size();
        }
        return numexecises;
    }

    // effects: prints a list of all the exercises in the prorgam that works the given muscle
    public List<String> printExerciseforMuscle(String muscle) {
        List<String> exercises = new ArrayList<>();
        if (program.containsKey(muscle)) {
            for (Exercise e : program.get(muscle)) {
                exercises.add(e.getExercise());
            }
        }
        return exercises;
    }

    //modifies: this
    // effects: removes the exercise from the program
    public void removeanExercise(String exercise) {
        for (String key: program.keySet()) {
            List<Exercise> exercises = program.get(key);
            for (Exercise e: exercises) {
                if (e.getExercise() == exercise) {
                    exercises.remove(e);
                }
            }
        }
    }

    // effects: prints a list of all te exercises in the prorgam
    public List<String> printExercises() {
        List<String> exercisenames = new ArrayList<>();
        for (String key: program.keySet()) {
            List<Exercise> exercises = program.get(key);
            for (Exercise e: exercises) {
                exercisenames.add(e.getExercise());
            }
        }
        return exercisenames;
    }

    // modifies: this
    // effects: chanegs the given exercise to the alternative
    public void switchExercise(String exercise) {
        for (String key: program.keySet()) {
            List<Exercise> exercises = program.get(key);
            for (Exercise e: exercises) {
                if (e.getExercise() == exercise) {
                    e.swapOut();
                    break;
                }
            }
        }
    }

    // EFFECTS: changes a program into a JSONObject modelled after the demo
    @Override
    public JSONObject toJson() {
        JSONObject jsonprogram = new JSONObject();
        for (String key: program.keySet()) {
            jsonprogram.put(key, changetoJsonArray(program.get(key)));
        }
        return jsonprogram;
    }

    // Changes exercises in the program to a jason array
    public JSONArray changetoJsonArray(List<Exercise> exercises) {
        JSONArray jsonprogramarray = new JSONArray();
        for (Exercise e : exercises) {
            jsonprogramarray.put(e.toJson());
        }
        return jsonprogramarray;
    }
}
