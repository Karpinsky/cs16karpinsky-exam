package json;

import java.awt.*;
import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private java.util.ArrayList<JsonPair> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList<>(3);
        // I don't understand why ArrayList can't just accept array as parameter.
        for (JsonPair jsonPair : jsonPairs)
        {
            this.jsonPairs.add(jsonPair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonStr = new StringBuilder(10 * this.jsonPairs.size());
        jsonStr.append("{");

        Iterator<JsonPair> pairsIterator = this.jsonPairs.iterator();
        while (pairsIterator.hasNext())
        {
            JsonPair jsonPair = pairsIterator.next();
            jsonStr.append(jsonPair.toJson());
            if (pairsIterator.hasNext())
            {
                jsonStr.append(", ");
            }
        }

        jsonStr.append("}");
        return jsonStr.toString();
    }

    public void add(JsonPair jsonPair) {
        if (this.contains(jsonPair))
        {
            this.jsonPairs.remove((JsonPair) this.findPair(jsonPair.key));
        }
        this.jsonPairs.add(jsonPair);
    }

    public boolean contains(JsonPair jsonPair)
    {
        return this.find(jsonPair.key) != null;
    }

    public Json find(String name) {
        Iterator<JsonPair> jsonPairIterator = this.jsonPairs.iterator();

        while (jsonPairIterator.hasNext())
        {
            JsonPair jsonPair = jsonPairIterator.next();
            if (jsonPair.key.equals(name))
            {
                return jsonPair.value;
            }
        }

        return null;
    }

    private Json findPair(String name) {
        Iterator<JsonPair> jsonPairIterator = this.jsonPairs.iterator();

        while (jsonPairIterator.hasNext())
        {
            JsonPair jsonPair = jsonPairIterator.next();
            if (jsonPair.key.equals(name))
            {
                return jsonPair;
            }
        }

        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();

        for (String name : names)
        {
            JsonPair jsonPair = (JsonPair) this.findPair(name);
            if (jsonPair != null) {
                jsonObject.add(jsonPair);
            }
        }

        return jsonObject;
    }
}
