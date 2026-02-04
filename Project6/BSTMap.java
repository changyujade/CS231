
/*
***Jade Chang***

Template for the BSTMap classes
Fall 2020
CS 231 Project 6
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
        // fields here
        private TNode root;
        private int size;
        private Comparator<K> comp;

        // constructor: takes in a Comparator object
        public BSTMap(Comparator<K> comp) {
                // initialize fields heres
                // TNode root = null;
                this.root = null;
                this.comp = comp;
        }

        // adds or updates a key-value pair
        // If there is already a pair with new_key in the map, then update
        // the pair's value to new_value.
        // If there is not already a pair with new_key, then
        // add pair with new_key and new_value.
        // returns the old value or null if no old value existed
        public V put(K key, V value) {
                // check for and handle the special case
                // call the root node's put method
                if (root == null) {
                        size++;
                        // System.out.println("root added");
                        root = (new TNode(key, value));
                        return null;
                } else {
                        return root.put(key, value, this.comp);
                }
        }

        // gets the value at the specified key or null
        public V get(K key) {
                // check for and handle the special case
                // call the root node's get method
                // stub code
                if (root == null) {
                        return null;
                } else {
                        return this.root.get(key, this.comp);
                }

        }

        // Write stubs (functions with no code) for the remaining
        // functions in the MapSet interface

        // entrySet notes: For the sake of the word-counting project, the
        // pairs should be added to the list by a pre-order traversal.

        // You can make this a nested class
        private class TNode {
                // need fields for the left and right children
                // need a KeyValuePair to hold the data at this node
                private TNode left;
                private TNode right;
                private KeyValuePair<K, V> KVPair;

                // constructor, given a key and a value
                public TNode(K k, V v) {
                        // initialize all of the TNode fields
                        this.KVPair = new KeyValuePair<>(k, v);
                        left = null;
                        right = null;
                }

                public String toString() {
                        return KVPair.toString();
                }

                // Takes in a key, a value, and a comparator and inserts
                // the TNode in the subtree rooted at this node

                // Returns the value associated with the key in the subtree
                // rooted at this node or null if the key does not already exist
                public V put(K key, V value, Comparator<K> comp) {
                        // implement the binary search tree put
                        // insert only if the key doesn't already exist
                        int compResult = comp.compare(key, KVPair.getKey());

                        if (compResult < 0) {
                                // do something on left
                                if (left != null) {
                                        // V oldValue = this.left.KVPair.getValue();
                                        // left.put(key, value, comp);
                                        return left.put(key, value, comp);
                                } else {
                                        size++;
                                        // System.out.println("node added");
                                        left = new TNode(key, value);
                                        return null;
                                }
                        } else if (compResult > 0) {
                                // do something on right
                                if (right != null) {
                                        return right.put(key, value, comp);
                                } else {
                                        size++;
                                        // System.out.println("node added");
                                        right = new TNode(key, value);
                                        return this.right.KVPair.getValue();
                                }
                        } else {
                                V oldValue = KVPair.getValue();
                                KVPair.setValue(value);
                                return oldValue;
                        }
                }

                // Takes in a key and a comparator
                // Returns the value associated with the key or null
                // FIXME: compare() might be wrong
                public V get(K key, Comparator<K> comp) {

                        int compResult = comp.compare(key, KVPair.getKey());

                        if (compResult < 0) {
                                // do something on left
                                if (left != null) {
                                        return left.get(key, comp);
                                }
                                return null;

                        } else if (compResult > 0) {
                                // do something on right
                                if (right != null) {
                                        return right.get(key, comp);
                                }
                                return null;

                        } else {
                                return this.KVPair.getValue();
                        }

                        // Any additional methods you want to add below, for
                        // example, for building ArrayLists

                        // end of TNode class
                }

        }

        @Override
        public boolean containsKey(K key) {
                // FIXME: how to search for existing key? seems done.
                
                return get(key)!=null;

                /*
                 * String user = null;
                 * K searchTerm = key;
                 * for (BSTMap<String, List<String>> entry : bst.entrySet()) {
                 * if (entry.getKey().contains(searchTerm)) {
                 * user = entry.getKey();
                 * break;
                 * }
                 * }
                 * return false;
                 * if (user == null) {
                 * throw new RuntimeException("No matching user for " + searchTerm);
                 * }
                 */
        }

        @Override
        // returns an ArrayList that contains all of the keys in the map. No order is
        // specified for the keys.
        public ArrayList<K> keySet() {
                ArrayList<K> list = new ArrayList<>();
                ArrayKeyCreate(root, list);
                return list;
        }

        public ArrayList<K> ArrayKeyCreate(TNode cur_node, ArrayList<K> list) {
                if (cur_node != null) {
                        ArrayKeyCreate(cur_node.right, list);
                        list.add(cur_node.KVPair.getKey());
                        ArrayKeyCreate(cur_node.left, list);
                }
                return list;
        }

        @Override
        // - returns an ArrayList that contains all of the values in the map. Their
        // order should match the order returned by keySet.
        public ArrayList<V> values() {
                ArrayList<V> listValues = new ArrayList<>();
                ArrayValueCreate(root, listValues);
                return listValues;
        }

        public ArrayList<V> ArrayValueCreate(TNode cur_node, ArrayList<V> listValues) {
                if (cur_node != null) {
                        ArrayValueCreate(cur_node.right, listValues);
                        listValues.add(cur_node.KVPair.getValue());
                        ArrayValueCreate(cur_node.left, listValues);
                }
                return listValues;
        }

        @Override
        // - returns an ArrayList of KeyValuePair objects. The pairs should be in a
        // pre-order traversal ordering.
        public ArrayList<KeyValuePair<K, V>> entrySet() {
                ArrayList<KeyValuePair<K, V>> listPairs = new ArrayList<>();
                ArrayKVPairCreate(root, listPairs);
                return listPairs;
        }

        public ArrayList<KeyValuePair<K, V>> ArrayKVPairCreate(TNode cur_node,
                        ArrayList<KeyValuePair<K, V>> listPairs) {
                /*
                 * if (cur_node != null) {
                 * ArrayKVPairCreate(cur_node.right, listPairs);
                 * listPairs.add(cur_node.KVPair);
                 * ArrayKVPairCreate(cur_node.left, listPairs);
                 * }
                 * return listPairs;
                 */
                if (cur_node != null) {
                        // visit node
                        listPairs.add(cur_node.KVPair);
                        // System.out.println(root.getElement());
                        // traverse left
                        ArrayKVPairCreate(cur_node.left, listPairs);
                        // traverse right
                        ArrayKVPairCreate(cur_node.right, listPairs);
                }
                return listPairs;
        }

        @Override
        // - returns the number of elements (keys) in the map.
        public int size() {
                return size;
        }

        @Override
        // - clears the map and sets it to the empty map.
        public void clear() {
                root = null;
        }

        // gives you an idea of the structure of your tree

        public String toString() {

                // String str = "root      ";
                // str += preorderRec(root,0,"root");
                return preorderRec(root,0,"root");
        }

        private String preorderRec(TNode cur, int depth,String direction) {
                String anotheString = "";

                if (cur==null){
                        return "";
                }else{
                        anotheString += direction +"\t"+" ".repeat(depth)+cur.KVPair.getKey()+":"+cur.KVPair.getValue()+"\n";
                        anotheString += preorderRec(cur.left,depth+1,"left");
                        anotheString += preorderRec(cur.right,depth+1,"right");

                }
                return anotheString;

                /*
                if (root != null) {
                        // System.out.println(root.KVPair.getKey());
                        // visit node
                        anotheString += root.toString();
                        // System.out.println("root added");
                        // traverse left
                        if (root.left != null) {
                                anotheString += "left   " + preorderRec(root.left);
                        }

                        // System.out.println("adding left");
                        // traverse right
                        if (root.right != null) {
                                anotheString += "right  " + preorderRec(root.right); 
                        }
                }
                */

                
        }

        // test function
        public static void main(String[] argv) {
                /**
                 * 20
                 * / \
                 * 11
                 * / \
                 * 5 6
                 * 
                 */

                // create a BSTMap
                BSTMap<String, Integer> bst = new BSTMap<String, Integer>(new AscendingString());

                bst.put("twenty", 20);
                // bst.put("twenty", 10);
                bst.put("eleven", 11);
                bst.put("five", 5);
                bst.put("six", 6);

                // System.out.println(bst.put("twenty", 5));
                // System.out.println(bst.put("twenty", 8));
                // System.out.println(bst.put("twenty", 9));

                // System.out.println(bst.get("eleven"));
                // System.out.println(bst.get("six"));

                // System.out.println(bst.keySet());
                // System.out.println(bst.values());

                // System.out.println(bst.entrySet());
                // System.out.println(bst.size());
                // System.out.println(bst.containsKey("eight"));

                // System.out.println(bst.toString());

                //bst.toString();
                // put more test code here

        }
}
