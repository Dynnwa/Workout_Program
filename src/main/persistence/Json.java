package persistence;

import org.json.JSONObject;

// This class turns things into Json
// Modelled after Writable from the sample application
public interface Json {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
