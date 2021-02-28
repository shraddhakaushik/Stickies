package persistence;

import org.json.JSONObject;

//TODO citation: code taken from Writable.java in JsonSerializationDemo
public interface Writable {
    //EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
