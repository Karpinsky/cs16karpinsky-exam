package json;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Tuple<K, V> extends Json{
    public final K key;
    public final V value;

    public Tuple(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toJson() {
        throw new UnsupportedOperationException("toJson is not implemented yet");
    }
}
