package persistence;

import model.Exercise;
import model.Program;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test for json reader, modelled after the demo json app
public class JSONReader_test extends CheckExercise {

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
            correctExercise("Deadlift", "Cable dl", "hamstring",
                    3, 3, false, program.getExercise(0));
            correctExercise("leg curl", "rdl", "hamstring",
                    3, 10, false, program.getExercise(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
