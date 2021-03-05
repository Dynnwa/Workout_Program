package model;

import org.json.JSONObject;
import persistence.Json;

// Exercise represents an individual exercise in the program
// an exercise will be repesented by the name of the exercise, the alternatives, the muscle, the sets and the reps
public class Exercise implements Json {
    private String exercise; // name of exercise
    private String alternative; // name of alternative exercise
    private String muscle; // the muscle worked
    private int sets; // sets
    private int reps; // reps
    private boolean ismachine; // is it a machine exercise or a free weight

    /*
    EFFECTS: exercisename is the name of the exercise, alternative is the alternative exercise
             sets and reps are the sets and reps
             is machine is whether or not this exercise is a machine exercise TRUE, or a
             free weight exercise FALSE.sdfdsfdsfdsfs
     */
    public Exercise(String exercise, String alternative, String muscle, int sets, int reps, boolean ismachine) {
        this.exercise = exercise;
        this.alternative = alternative;
        this.muscle = muscle;
        this.sets = sets;
        this.reps = reps;
        this.ismachine = ismachine;
    }

    // effect: gets the name of the exercise
    public String getExercise() {
        return exercise;
    }

    // effects: get the name of the machine alternative
    public String getAlternative() {
        return alternative;
    }

    // effects: gets the name of the muscle trained
    public String getMuscle() {
        return muscle;
    }

    // effects: gets the amount of sets
    public int getSets() {
        return sets;
    }

    // effects: gets the amount of reps
    public int getReps() {
        return reps;
    }

    // effects: true if it is a machine false othrwise
    public boolean isMachine() {
        return ismachine;
    }

    /*
    MODIFIES: this
    EFFECTS: changes the reps of the exercise to newreps
     */
    public void changeReps(int newreps) {
        this.reps = newreps;
    }

    /*
    MODIFIES: this
    EFFECTS: changes the sets of the exercise to newsets
     */
    public void changeSets(int newsets) {
        this.sets = newsets;
    }

    /*
    MODIFIES: this
    EFFECTS: changes the exercise to alternative and vis versa, also changes the machine to the opposite
     */
    public void swapOut() {
        String holder = this.getAlternative();
        this.alternative = this.exercise;
        this.exercise = holder;
        this.ismachine = !this.ismachine;
    }

    // EFECTS: changes an exercise to a JSONObject, modelled after the demo
    @Override
    public JSONObject toJson() {
        JSONObject jsoneercise = new JSONObject();
        jsoneercise.put("exercise", exercise);
        jsoneercise.put("alternative", alternative);
        jsoneercise.put("muscle", muscle);
        jsoneercise.put("sets", sets);
        jsoneercise.put("reps", reps);
        jsoneercise.put("Ismachine", ismachine);
        return jsoneercise;
    }

}
