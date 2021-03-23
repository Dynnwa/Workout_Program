package persistence;

import model.Exercise;
import model.Program;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// MODELLED AFTER THE WORKROOM FILE
public class JsonReader {
    private String sourceFile;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.sourceFile = source;
    }

    // EFFECTS: reads program from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Program read() throws IOException {
        String jsonData = readFile(sourceFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProgram(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses program from JSON object and returns it
    private Program parseProgram(JSONObject jsonObject) {
        Program p = new Program();
        addExercises(p, jsonObject);
        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses exercises from JSON object and adds them to program
    private void addExercises(Program p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(p, nextExercise);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses exercise from JSON object and adds it to program
    private void addExercise(Program p, JSONObject jsonObject) {
        String exercise = jsonObject.getString("exercise");
        String alternative = jsonObject.getString("alternative");
        String muscle = jsonObject.getString("muscle");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        boolean ismachine = jsonObject.getBoolean("Ismachine");


        Exercise e = new Exercise(exercise, alternative, muscle, sets, reps, ismachine);
        p.addExercise(e);
    }
}
