package json;

import java.awt.*;
import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private HashMap<String, JsonPair> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new HashMap<>(jsonPairs.length);

        for (JsonPair jsonPair : jsonPairs)
        {
            this.jsonPairs.put(jsonPair.key, jsonPair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonStr = new StringBuilder(10 * this.jsonPairs.size());
        jsonStr.append("{");

        Iterator<String> pairsIterator = this.jsonPairs.keySet().iterator();
        while (pairsIterator.hasNext())
        {
            String jsonPair = pairsIterator.next();
            jsonStr.append(this.jsonPairs.get(jsonPair).toJson());
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
            JsonPair jsonPairToRemove = (JsonPair) this.findPair(jsonPair.key);
            this.jsonPairs.remove(jsonPairToRemove.key, jsonPairToRemove);
        }
        this.jsonPairs.put(jsonPair.key, jsonPair);
    }

    public boolean contains(JsonPair jsonPair)
    {
        return this.find(jsonPair.key) != null;
    }

    public Json find(String name) {
        JsonPair jsonPair = ((JsonPair)this.findPair(name));

        return jsonPair != null ? jsonPair.value : null;
    }

    private Json findPair(String name) {
        Iterator<String> jsonPairIterator = this.jsonPairs.keySet().iterator();

        while (jsonPairIterator.hasNext())
        {
            String jsonPair = jsonPairIterator.next();
            if (jsonPair.equals(name))
            {
                return this.jsonPairs.get(jsonPair);
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
