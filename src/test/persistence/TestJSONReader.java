package persistence;

import model.Program;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Test for json reader, modelled after the demo json app
public class TestJSONReader extends TestCheckExercise {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/doesnotexist.json");
        try {
            Program program = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyProgram() {
        JsonReader reader = new JsonReader("./data/emptyProgram.json");
        try {
            Program program = reader.read();
            assertEquals(0, program.programLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralProgram() {
        JsonReader reader = new JsonReader("./data/twoExercises.json");
        try {
            Program program = reader.read();
            assertEquals(2, program.programLength());
            correctExercise("Dumbell curl", "Cable curl", "bicep",
                    3, 8, false, program.getExercise(0));
            correctExercise("Barbell curl", "Band curl", "bicep",
                    4, 10, false, program.getExercise(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
