import java.security.Key;
import java.util.ArrayList;
import java.util.Comparator;
//import java.util.HashMap; // import the HashMap class
import java.util.Iterator;

import javax.sql.rowset.spi.SyncResolver;
public class HashmapArray<K, V> implements MapSet<K, V> {
    // Hashmap constructor that starts with default size hash table
    private Comparator<K> incomp;
    // private LinkedList<KeyValuePair<K, V>>[] table;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int size = 0;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // final float loadFactor;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    private Object[] table;
    private int numCollisions;
    private int length_arrayLists;
    private int numFilledBuckets;

    public HashmapArray(Comparator<K> incomp) {
        this.incomp = incomp;
        createTable(DEFAULT_INITIAL_CAPACITY);
        numCollisions = 0;
    }

    // Hashmap constructor that starts with the suggested capacity hash table
    public HashmapArray(Comparator<K> incomp, int capacity) {
        // code here
        this.incomp = incomp;
        createTable(capacity);
        numCollisions = 0;
    }

    public int getNumber_collisions() {
        return numCollisions;
    }

    public int hash(K key) {
        // make sure hash is positive and fits in valid array bounds
        int temp = Math.abs(key.hashCode());
        // System.out.println(temp);
        return temp % table.length;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            ArrayList<KeyValuePair<K, V>> current_bucket = getBucketAtIndex(i);
            for (KeyValuePair<K, V> e : current_bucket) {
                sb.append("[");
                sb.append(e);
                sb.append(",");
                sb.append("]");
            }
        }

        return "{" + sb.toString() + "}";
    }

    @Override
    public void clear() {
        // this.table = new Object[getCapacity()];
        for (int i = 0; i < getCapacity(); i++) {
            getBucketAtIndex(i).clear();
        }
        numCollisions = 0;
    }

    @Override
    // take the key and compare it with the existing objects in the arrayList
    public boolean containsKey(K key) {
        return (get(key) != null);

    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {

        ArrayList<KeyValuePair<K, V>> entrySet = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            // System.out.println(table.getClass());
            ArrayList<KeyValuePair<K, V>> current_bucket = getBucketAtIndex(i);
            for (KeyValuePair<K, V> e : current_bucket) {
                entrySet.add(e);
            }
        }
        return entrySet;
    }

    @Override
    public V get(K key) {

        // selects which bucket to search for
        int bucket_index = hash(key);
        ArrayList<KeyValuePair<K, V>> existing = getBucketAtIndex(bucket_index);
        for (KeyValuePair<K, V> e : existing) {
            if (e.getKey().equals(key)) {
                return (V) e.getValue();
            }
        }
        return null;
    }

    private ArrayList<KeyValuePair<K, V>> getBucketAtIndex(int bucket_index) {
        return (ArrayList<KeyValuePair<K, V>>) table[bucket_index];
    }

    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keySet = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            ArrayList<KeyValuePair<K, V>> current_bucket = getBucketAtIndex(i);
            // current_bucket.toArrayList();
            for (KeyValuePair<K, V> e : current_bucket) {
                keySet.add((K) e.getKey());
            }
        }
        return keySet;
    }

    @Override
    // This method associates the specified value with the specified key in this map
    // and if the map previously contained a mapping for the key, the old value is
    // replaced
    public V put(K new_key, V new_value) {

        // When the average number of entries per location goes above some specified
        // value, then increase the size of the table. What the threshold should be is
        // something you should test.

        // map is the map
        // find the hash key
        // does it already exist. check if data at that hash key has a size greater than
        // 0. If so, then there is a chance that the key already exists
        // then we would want to look through the key value pairs to see if there is the
        // key that were looking for
        // if there is, then we would want to update the value, return the old value.

        // if it does not exist, create a new key value pair and add it to the linked
        // list.
        // Check to see if there is a collision, update the num_collision

        int bucket_index = hash(new_key);
        ArrayList<KeyValuePair<K, V>> existing = getBucketAtIndex(bucket_index);

        if (existing.size() == 0) {
            KeyValuePair<K, V> new_KVPair = new KeyValuePair<K, V>(new_key, new_value);
            existing.add(new_KVPair);
            numFilledBuckets += 1;
            size++;
        } else {
            // compare the keys see if key already exists
            for (KeyValuePair<K, V> e : existing) {
                if (e.getKey().equals(new_key)) {
                    // update the value
                    V oldV = e.getValue();
                    e.setValue(new_value);
                    return oldV;
                }
            }
            // if the hash isn't found in the bucket, then
            // append it to the bucket.
            existing.add(new KeyValuePair<K, V>(new_key, new_value));
            numCollisions++;
            size++;
        }

        // long average_number_entries = size / table.length;
        // long average_number_entries = size() / table.length;
        long meanEntriesPerBucket = size() / numFilledBuckets;
        // threshold: linked list to not have more than 5 items?
        // keep track of how many buckets have something in there. Then divide the size
        // by the number the buckets that are not null.
        if (meanEntriesPerBucket > 5) {
            resize();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ArrayList<V> values() {

        ArrayList<V> values = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            ArrayList<KeyValuePair<K, V>> current_bucket = getBucketAtIndex(i);
            for (KeyValuePair<K, V> e : current_bucket) {
                values.add((V) e.getValue());
            }
        }
        return values;
    }

    public void resize() {

        // RESIZE method
        size = 0;


        ArrayList<KeyValuePair<K, V>> entrySet = entrySet();
        createTable(getCapacity() * 2);
        for (KeyValuePair<K, V> e : entrySet) {
            put(e.getKey(), e.getValue());

        }
    }

    private int getCapacity() {
        return table.length;
    }

    private void createTable(int capacity) {
        table = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new ArrayList<KeyValuePair<K, V>>();
        }
    }

    public void result() {
        System.out.println("Unique Words: " + this.size());
        System.out.println("Num of Collisions: " + this.getNumber_collisions());
    }

    public static void main(String[] args) {

        // HashMap hashMap = new Hashmap<KeyValuePair<K, V>>(new AscendingString());
        HashmapArray<String, String> hashMap = new HashmapArray<>(new AscendingString());

        for (int i = 0; i < 30000; i++) {
            hashMap.put(Integer.toString(i), "one");
            // I changed the hashcode to 0 so it always puts it on the first bucket.
            // Should resize because there contains more than 5 items per bucket. (changed
            // the hashcode back to the right one when I finished testing)
        }

        hashMap.put("one", "one");
        hashMap.put("two", "two");
        System.out.println("num_collisions: " + hashMap.numCollisions);
        System.out.println("capacity: " + hashMap.getCapacity());
        System.out.println("in map?: " + hashMap.containsKey("one"));

        // hashMap.put("two", "three");
        // hashMap.put("three", "red");
        // hashMap.put("four", "three");
        // hashMap.put("five", "red");
        // hashMap.put("six", "three");
        // hashMap.put("seven", "red");
        // hashMap.put("eight", "three");
        // hashMap.put("nine", "red");
        // hashMap.put("ten", "three");
        // hashMap.put("eleven", "red");
        // hashMap.put("twelve", "three");
        // hashMap.put("thirteeh", "red");
        // hashMap.put("fourteen", "three");
        // hashMap.put("fifteen", "red");
        // hashMap.put("sixteen", "three");
        // hashMap.put("seventeen", "red");
        // hashMap.put("eighteen", "three");

        System.out.println(hashMap.entrySet());
        System.out.println(hashMap.get("car"));
        // System.out.println(hashMap.keySet());

        // System.out.println(hashMap.values());
    }
}
