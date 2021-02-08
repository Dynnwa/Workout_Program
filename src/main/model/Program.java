package model;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private List<Exercise> program;

    public Program() {
        program = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Exercise e is added to the program if it's not
    // already in the program
    public void addExercise(Exercise e) {
        if (!program.contains(e)) {
            program.add(e);
        }
    }

    // REQUIRES: Exercise e is an element of the Program
    // MODIFIES: this
    // EFFECTS: Exercise e is removed from the Program
    public void removeExercise(Exercise e) {
        program.remove(e);
    }

    // EFFECTS: Returns true if Exercise e is in the program
    // and false otherwise
    public boolean containsExercise(Exercise e) {
        return program.contains(e);
    }

    // EFFECTS: Returns the number of exercises in the set
    public int programLength() {
        return program.size();
    }

    public int volumeFormuscle(String muscle) {
        int volume = 0;
        for (int i = 0; i < this.programLength(); i++) {
            if (program.get(i).getMuscle().equals(muscle)) {
                volume = volume + (program.get(i).getReps() * program.get(i).getSets());
            }
            //return volume;
        }
        return volume;
    }

    public void changeSets(int newset, String exercise) {
        for (int i = 0; i < this.programLength(); i++) {
            if (program.get(i).getExercise().equals(exercise)) {
                program.get(i).changeSets(newset);
                break;
            }
        }
    }

    public void changeReps(int newrep, String exercise) {
        for (int i = 0; i < this.programLength(); i++) {
            if (program.get(i).getExercise().equals(exercise)) {
                program.get(i).changeReps(newrep);
                break;
            }
        }
    }

    public void removeanExercise(String exercise) {
        for (int i = 0; i < this.programLength(); i++) {
            if (program.get(i).getExercise().equals(exercise)) {
                this.removeExercise(program.get(i));
            }
        }
    }

    public List<String> printExercises() {
        List<String> exercises = new ArrayList<>();
        for (int i = 0; i < this.programLength(); i++) {
            exercises.add(program.get(i).getExercise());
        }
        return exercises;
    }

    public void switchExercise(String exercise) {
        for (int i = 0; i < this.programLength(); i++) {
            if (program.get(i).getExercise().equals(exercise)) {
                program.get(i).swapOut();
                break;
            }
        }
    }

    // public void progression(){}

    // public void currentProgress(){}

    // public void timer(){} // maybe we see where i go with it
}
