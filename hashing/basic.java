import java.util.*;
/// full implementation of hashMap from scratch 

public class basic{
    static class HashMap<K,V> {  // generic
        private class Node{
            K key ;
            V value;
            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }
        private int size; // n-> no of nodes
        private LinkedList<Node> buckets[]; // N-> no of bukets

        @SuppressWarnings("unchecked")
        public HashMap(){
            this.size = 4;
            this.buckets = new LinkedList[4];
            for(int i =0 ;i<4 ;i++){
                this.buckets[i] = new LinkedList<>();
            }
        }

        public int hashFunction(K key){
            int hc = key.hashCode();
            return Math.abs(hc) % buckets.length;  // buckets.length
        }

        public int SearchInLL(K key , int bucketIdx){
            LinkedList<Node> ll = buckets[bucketIdx];

            int dataIdx=0;

            for(int i=0 ; i<ll.size();i++){
                Node temp = ll.get(i);
                if(temp.key == key){
                    return dataIdx;
                }
                dataIdx++;
            }

            return -1; // not found
        }

        public void put(K key , V value){
            int bucketIdx = hashFunction(key);
            int dataIdx = SearchInLL(key , bucketIdx);

            if(dataIdx != -1){
                Node temp = buckets[bucketIdx].get(dataIdx);
                temp.value = value;
            }
            else{
                buckets[bucketIdx].add(new Node(key , value));
                size++;
            }

            double lambda = (double)size / buckets.length;
            if(lambda > 3.0){// greater then threshold 
                rehash();
            }
        }

        @SuppressWarnings("unchecked")
        public void rehash(){
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[buckets.length*2];
            for(int i =0 ; i<buckets.length ; i++){
                buckets[i] = new LinkedList<>();
            }

            for(int i =0; i<oldBucket.length ; i++){
                LinkedList<Node> ll = oldBucket[i];
                for(int j=0 ; j<ll.size();j++){
                    Node temp = ll.remove();
                    put(temp.key , temp.value);
                }
            }
        }

        public boolean containsKey(K key){
            int bucketIdx = hashFunction(key);
            int dataIdx = SearchInLL(key , bucketIdx);

            if(dataIdx==-1) 
                return false;
            else 
                return true;
        }

        public boolean remove(K key){
            if(containsKey(key) == false){
                /// not present , invalid remove
                return false;
            }
            else{
            int bucketIdx = hashFunction(key);
            int dataIdx = SearchInLL(key , bucketIdx);
            LinkedList<Node> ll = buckets[bucketIdx];
            ll.remove(dataIdx);
            size--;
            return true;
             }
        }

        public V get(K key){
            if(containsKey(key) == false)
                return null;
            else{
            int bucketIdx = hashFunction(key);
            int dataIdx = SearchInLL(key , bucketIdx);
            LinkedList<Node> ll = buckets[bucketIdx];

            return ll.get(dataIdx).value;
             }
        }

        public ArrayList<K> keySet(){
           ArrayList<K> keys = new ArrayList<>() ;

           for(int i=0 ; i<buckets.length ;i++){
            LinkedList<Node> ll = buckets[i];
            for(Node n :ll){
                keys.add(n.key);
            }
           }
           return keys;
        }

        public boolean isEmpty(){
            return size== 0 ;
        }
    }

    public static void main(String args[]){

        HashMap<String ,Integer> map1  = new HashMap<>();

        map1.put("india" , 150);
        map1.put("china" , 140);

        ArrayList<String> keys1 = map1.keySet();
        System.out.println(keys1);
    }
}