package model;

import model.Exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExerciseTest {
    Exercise curls1;
    Exercise curls2;
    Exercise squat1;
    Exercise squat2;
    Exercise benchpress;
    Exercise abs;

    @BeforeEach
    public void runBefore() {
        curls1 = new Exercise("Dumbell curl", "Cable curl", "bicep",
         3,8,false);
        curls2 = new Exercise("Barbell curl", "Band curl", "bicep",
                4,10,false);
        squat1 = new Exercise("Dumbell squat", "Leg press", "quad",
                3,3,false);
        squat2 = new Exercise("Barbell squat", "Hack squat", "quad",
                4,8,false);
        benchpress = new Exercise("Bench press", "Dumbell press", "chest",
                3,8,false);
        abs = new Exercise("Situp", "Machine Crunch", "abs",
                5,10,false);
    }

    @Test
    public void testcontructor() {
        assertEquals("Dumbell curl", curls1.getExercise());
        assertEquals("Cable curl", curls1.getAlternative());
        assertEquals("bicep", curls1.getMuscle());
        assertEquals(3, curls1.getSets());
        assertEquals(8, curls1.getReps());
        assertFalse(curls1.isMachine());
    }

    @Test
    public void testchangeReps() {
        curls1.changeReps(8);
        assertEquals(8, curls1.getReps());
        curls1.changeReps(10);
        assertEquals(10, curls1.getReps());
    }

    @Test
    public void testchangeSets() {
        curls1.changeSets(3);
        assertEquals(3, curls1.getSets());
        curls1.changeSets(5);
        assertEquals(5, curls1.getSets());
    }

    @Test
    public void testSwapout() {
        curls1.swapOut();
        assertEquals("Cable curl", curls1.getExercise());
        assertEquals("Dumbell curl", curls1.getAlternative());
        assertTrue(curls1.isMachine());
    }




}