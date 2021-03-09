package persistence;

import model.Exercise;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheckExercise {

    // EFFECTS: Ensures that the exercise that is stored in e is the correct exercise that we want
    public void correctExercise(String exercise, String alternative,
                                String muscle, int sets, int reps, boolean ismachine, Exercise e) {
        assertEquals(exercise, e.getExercise());
        assertEquals(alternative, e.getAlternative());
        assertEquals(muscle, e.getMuscle());
        assertEquals(sets, e.getSets());
        assertEquals(reps, e.getReps());
        assertEquals(ismachine, e.isMachine());
    }
}
