package persistence;

import model.Exercise;
import model.Program;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Test for json writer, modelled after the demo json app
public class TestJSONWriter extends TestCheckExercise {

    @Test
    void testWriterInvalidFile() {
        try {
            Program program = new Program();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.startWriter();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Program program = new Program();
            JsonWriter writer = new JsonWriter("./data/emptyProgram.json");
            writer.startWriter();
            writer.write(program);
            writer.stopWriter();

            JsonReader reader = new JsonReader("./data/emptyProgram.json");
            program = reader.read();
            assertEquals(0, program.programLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Exercise  curls1 = new Exercise("Dumbell curl", "Cable curl", "bicep",
                    3,8,false);
            Exercise curls2 = new Exercise("Barbell curl", "Band curl", "bicep",
                    4,10,false);
            Program program = new Program();
            program.addExercise(curls1);
            program.addExercise(curls2);
            JsonWriter writer = new JsonWriter("./data/twoExercises.json");
            writer.startWriter();
            writer.write(program);
            writer.stopWriter();

            JsonReader reader = new JsonReader("./data/twoExercises.json");
            program = reader.read();
            assertEquals(2, program.programLength());
            correctExercise("Dumbell curl", "Cable curl","bicep",
                    3,8,false, program.getExercise(0));
            correctExercise("Barbell curl", "Band curl","bicep",
                    4,10,false, program.getExercise(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
