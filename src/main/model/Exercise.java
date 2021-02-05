package model;

public class Exercise {
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
             free weight exercise FALSE.
     */
    public Exercise(String exercise, String alternative, String muscle, int sets, int reps, boolean ismachine) {
        this.exercise = exercise;
        this.alternative = alternative;
        this.muscle = muscle;
        this.sets = sets;
        this.reps = reps;
        this.ismachine = ismachine;
    }

    // just getter methods
    public String getExercise() {
        return exercise;
    }

    public String getAlternative() {
        return alternative;
    }

    public String getMuscle() {
        return muscle;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

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

        /*
    public void doneSet() {
        this.sets = sets--;
    }

    public void doneRep() {
        this.reps = reps--;
    }
    */

    // public void demo(){} // video demonstrations

    // public void descriptionExercise(){}

    // public void timer(){} // maybe we see where i go with it



}
