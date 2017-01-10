package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    protected Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject[] jsons = new JsonObject[this.exams.length];

        for (int i = 0; i < jsons.length; ++i)
        {
            jsons[i] = new JsonObject();
            jsons[i].add(new JsonPair("course", new JsonString(this.exams[i].key)));
            jsons[i].add(new JsonPair("mark", new JsonNumber(this.exams[i].value)));
            jsons[i].add(new JsonPair("passed", new JsonBoolean(this.exams[i].value > 2)));
        }

        return new JsonObject(new JsonPair("name", new JsonString(this.name)),
                new JsonPair("surname", new JsonString(this.surname)),
                new JsonPair("year", new JsonNumber(this.year)),
                new JsonPair("exams", new JsonArray(jsons))
        );
    }
}