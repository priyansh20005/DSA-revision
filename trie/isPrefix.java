// given a prefix find if any word in arr has a word with that prefix ;


public class isPrefix{

    public static class Node{
        boolean eow = false;
        Node[] child = new Node[26];

        Node(){
            for(int i=0;i<26;i++)
                child[i]=null;
        }
    }

    public static Node root= new Node();

    public static void insert(String word){
        Node curr = root;
        for(int i=0 ;i<word.length();i++){
            int idx = word.charAt(i)-'a';
            if(curr.child[idx]==null)
                curr.child[idx] = new Node();
            
            curr = curr.child[idx];
        }
        curr.eow=true;
    }

    public static boolean isPrefixPresent(String prefix , Node root){
        Node curr = root;
        for(int i=0 ; i<prefix.length() ; i++){
            int idx = prefix.charAt(i)-'a';
            if(curr.child[idx]==null)
                    return false;
                
            curr= curr.child[idx];
        }
        return true;
    }

    public static void main(String args[]){
        String[] arr ={"dove" , "zebra","dog", "duck"};

        for(String word :arr){
            insert(word);
        }

        System.out.println(isPrefixPresent("dogi" ,root)+" ");
    }
}

