


public class PrefixProblem{

  
    public static class Node{
            int freq ;
           boolean eow=false;
            Node[] child= new Node[26];

            Node(){ 
                freq=1;
                for(int i=0; i<26 ;i++){
                    this.child[i] =null;
                }
            } 
    }

public static Node root  = new Node();

public static void insert(String word){
        Node curr=root;

        for(int i=0;i<word.length() ;i++){
            int idx = (int)word.charAt(i)-'a';
            if(curr.child[idx]==null){
                curr.child[idx] = new Node();
            }else{
                curr.child[idx].freq++;
            }
            curr = curr.child[idx];
        }
    }

    public static void prefix(Node root , String ans ){
        if(root==null){
            return;
        }
        if(root.freq ==1){
            System.out.println(ans);
            return;
                }
        for(int i=0 ;i<root.child.length;i++){
            if(root.child[i]!=null){
               prefix(root.child[i], ans+(char)(i+'a'));
            }
        }
    }

    public static void main(String args[]){

        String[] arr ={"dove" , "zebra","dog", "duck"};

        for(String word :arr){
            insert(word);
        }
        root.freq=-1;

        prefix(root,"");
    }
}