package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Json;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {
    Program program;
    Exercise curls1;
    Exercise curls2;
    Exercise squat1;
    Exercise squat2;
    Exercise benchpress;
    Exercise abs;

    @BeforeEach
    public void runBefore() {
        program = new Program();
        curls1 = new Exercise("Dumbell curl", "Cable curl", "bicep",
                3, 8, false);
        curls2 = new Exercise("Barbell curl", "Band curl", "bicep",
                4, 10, false);
        squat1 = new Exercise("Dumbell squat", "Leg press", "quad",
                3, 3, false);
        squat2 = new Exercise("Barbell squat", "Hack squat", "quad",
                4, 8, false);
        benchpress = new Exercise("Bench press", "Dumbell press", "chest",
                3, 8, false);
        abs = new Exercise("Situp", "Machine Crunch", "abs",
                5, 10, false);
    }

    @Test
    public void testConstructor() {
        Program program = new Program();

        assertEquals(0, program.programLength());
        assertFalse(program.containsExercise(curls1));
    }

    @Test
    public void testaddExercise() {
        program.addExercise(curls1);

        assertTrue(program.containsExercise(curls1));
        assertTrue(1 == program.programLength());

        program.addExercise(curls1);

        assertTrue(program.containsExercise(curls1));
        assertTrue(2 == program.programLength());

        program.addExercise(curls2);

        assertTrue(program.containsExercise(curls2));
        assertTrue(3 == program.programLength());
    }

    @Test
    public void testremoveExercise() {
        program.addExercise(curls1);
        program.addExercise(curls2);

        assertTrue(program.containsExercise(curls2));
        assertTrue(program.containsExercise(curls1));
        assertTrue(2 == program.programLength());

        program.removeExercise(curls1);

        assertTrue(program.containsExercise(curls2));
        assertFalse(program.containsExercise(curls1));
        assertTrue(1 == program.programLength());
    }

    /*
    @Test
    public void testcalculateVolumeEmpty() {
        assertEquals(0, program.volumeFormuscle("biceps"));
    }

    @Test
    public void testcalculateVolumesamemuscle() {
        program.addExercise(curls1);
        program.addExercise(curls2);

        assertEquals(curls1.getReps() * curls1.getSets()
                + curls2.getSets() * curls2.getReps(), program.volumeFormuscle("bicep"));
    }

    @Test
    public void testcalculateVolumediffmuscles() {
        program.addExercise(curls1);
        program.addExercise(curls2);
        program.addExercise(squat1);
        program.addExercise(squat2);
        program.addExercise(abs);

        assertEquals(curls1.getReps() * curls1.getSets()
                + curls2.getSets() * curls2.getReps(), program.volumeFormuscle("bicep"));
        assertEquals(squat1.getReps() * squat1.getSets()
                + squat2.getSets() * squat2.getReps(), program.volumeFormuscle("quad"));
        assertEquals(abs.getReps() * abs.getSets(), program.volumeFormuscle("abs"));
    }
     */

    @Test
    public void testprintMuscle() {
        List<String> list = new ArrayList<>();
        assertEquals(list, program.printExerciseforMuscle("quad"));
        assertEquals(list, program.printExerciseforMuscle("bicep"));
        program.addExercise(curls1);
        program.addExercise(curls2);
        list.add(curls1.getExercise());
        list.add(curls2.getExercise());
        assertEquals(list, program.printExerciseforMuscle("bicep"));
        program.addExercise(squat1);
        program.addExercise(squat2);
        list.remove(curls1.getExercise());
        list.remove(curls2.getExercise());
        list.add(squat1.getExercise());
        list.add(squat2.getExercise());
        assertEquals(list, program.printExerciseforMuscle("quad"));
    }

    @Test
    public void testPrintlist() {
        program.addExercise(curls1);
        program.addExercise(curls2);
        program.addExercise(squat1);
        List<String> exercises = new ArrayList<String>();
        exercises.add("Dumbell curl");
        exercises.add("Barbell curl");
        exercises.add("Dumbell squat");

        assertEquals(exercises, program.printExercises());
    }

    @Test
    public void testRemoveanExercisenone() {
        assertEquals(0, program.programLength());
        program.removeanExercise("Dumbell curl");

        assertEquals(0, program.programLength());
    }

    @Test
    public void testRemoveanExerciseone() {
        program.addExercise(curls1);
        program.removeanExercise("Dumbell curl");

        assertEquals(0, program.programLength());
    }

    @Test
    public void testRemoveanExercisemore() {
        program.addExercise(curls1);
        program.addExercise(curls2);
        program.addExercise(squat1);
        assertEquals(3, program.programLength());
        program.removeanExercise("Barbell curl");

        assertEquals(2, program.programLength());
        assertTrue(program.containsExercise(curls1));
        assertTrue(program.containsExercise(squat1));

        program.addExercise(curls2);
        program.removeanExercise("Barbell curl");
        program.removeanExercise("Dumbell squat");

        assertEquals(1, program.programLength());
        assertTrue(program.containsExercise(curls1));
    }

    @Test
    public void testSwitchExercise() {
        program.addExercise(curls1); //
        program.addExercise(squat1); //
        program.switchExercise("Dumbell squat");
        program.switchExercise("Barbell squat");
        List<String> exercises = new ArrayList<String>();
        exercises.add("Dumbell curl");
        exercises.add("Leg press");

        assertEquals(exercises, program.printExercises());

        List<String> exercises1 = new ArrayList<String>();
        exercises1.add("Dumbell curl");
        exercises1.add("Dumbell squat");
        program.switchExercise("Leg press");
        assertEquals(exercises1, program.printExercises());
    }

    @Test
    public void testToJSON() {
        program.addExercise(curls1);
        assertEquals(program.changetoJsonArray(program.getProgram().get("bicep")).toString()
                ,program.toJson().getJSONArray("bicep").toString());
    }

    @Test
    public void testToJSONArray() {
        program.addExercise(curls1);
        assertEquals(curls1.toJson().toString()
                ,program.changetoJsonArray(program.getProgram().get("bicep")).get(0).toString());
        program.addExercise(curls2);
        assertEquals(curls2.toJson().toString()
                ,program.changetoJsonArray(program.getProgram().get("bicep")).get(1).toString());
    }

}
