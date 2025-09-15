/// implementation of trie /prefix tree / retrival tree / digital search tree

import java.util.*;

public class basic{

    public static class TrieNode{

        TrieNode children[] = new TrieNode[26] ;
        boolean eow = false;

        TrieNode(){
            for(int i=0;i<26 ;i++){
                children[i] = null;
            }
        }
    }

    public static TrieNode root = new TrieNode();

    public static void add(String str){

        TrieNode curr = root;
        // int maxLevel = str.length();
        // int currLevel = 0;
    //     char ch= str.charAt(0);
    //     int idx = (int)ch-'a';

    //     while(currLevel < maxLevel){
    //         ch= str.charAt(currLevel);
    //         idx = (int)ch-'a';

    //     if(currLevel+1 == maxLevel)
    //         curr.eow = true;

    //     if(curr.children[idx] == null){
    //         // not present
    //         curr.children[idx]  = new TrieNode();
    //         curr = curr.children[idx];
    //     }
    //     else{
    //         //already exist
    //         curr=curr.children[idx];
    //     }
    //     currLevel++;
    // }

    for(int level=0; level<str.length(); level++){
        int idxx = (int) str.charAt(level)-'a';
        if(curr.children[idxx] ==null){
            curr.children[idxx] = new TrieNode();
        }
        curr =curr.children[idxx];
    }
        curr.eow= true;
    }

    public static boolean isContains(String key){
        // String ans="";
        TrieNode curr = root;

        for(int level=0; level<key.length();level++){
            int idx= key.charAt(level)-'a';
            if(curr.children[idx] == null)
                return false;
            curr = curr.children[idx];
        }

        if(curr.eow == false)
            return false;
        else
            return true;
    }

    public static ArrayList<String> extractAll(TrieNode root){

        ArrayList<String> words = new ArrayList<>();
        String word = "";
        getWord(root, words , word);

        return words ;
    }

    public static void getWord(TrieNode root , ArrayList<String> words , String word){

        if(root.eow == true)
            words.add(word);
        
        for(int i=0 ; i<26;i++){
            if(root.children[i] !=null){
                getWord(root.children[i],words , word+(char)(i+'a'));
            }
        }
        return ;
    }

    public static void main(String args[]){

        String words[]={"the", "a" ,"app","their" , "thee" ,"how"};

        for(String word: words){
            add(word);
        }

        ArrayList<String> ans = extractAll(root);
        for(String s : ans){
            System.out.println(s);
        }
    }
}