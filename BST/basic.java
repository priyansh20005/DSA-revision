// build a binary Search tree


import java.util.*;

public class basic{

    public static class Node {
        int val ;
        Node right , left;

        Node(int val){
            this.val= val;
            this.left=null;
            this.right = null;
        }
    }

    

    static class BST{

        static Node root =null;

        // public static Node buildBst(int[] arr , int idx){
        //     if(idx == 0 || root == null) {
        //         root = new Node(arr[idx]);
        //         buildBst(arr , idx+1);
        //         return root;
        //     }
            
        //     if(root.val > arr[idx]){
        //         buildBst(root.right)
        //     }

            
            
        // }

        public static Node buildBST(int[] arr ){
            if(arr.length == 0) return null;

            int val = arr[0];
            root = new Node(val);

            for(int i=1 ; i<arr.length ; i++){
                val = arr[i];
                addNode(root ,val );
            }

            return root;
        }

        public static void addNode(Node root , int val){
            if(root.val > val){
                // insert in left  
                if(root.left == null) 
                    root.left = new Node(val);
                else 
                    addNode(root.left , val);
            }
            else{
                // insert in right 
                if(root.right == null)
                    root.right = new Node(val);
                else
                    addNode(root.right ,val);
            }
        }

    }

    public static void printBST(Node root){
        if(root == null) return ;
        Queue<Node> q = new LinkedList<>();
        
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            Node curr = q.remove();

            if(curr == null){
                System.out.println();
                if(q.isEmpty())
                    break;
                else 
                    q.add(null);
            }
            else{
                System.out.print(curr.val +"  ");

                if(curr.left != null)
                        q.add(curr.left);
                if(curr.right != null)
                        q.add(curr.right);
            }
        }

        System.out.println("-------------------------------------------------");
    }

    public static void printInorder(Node root){
        if(root == null) return ;

        printInorder(root.left);
        System.out.print(root.val+" ");
        printInorder(root.right);
        
    }

    public static void main(String args[]){
        int [] arr1 = {5 , 4 , 3, 2 , 1};
        int [] arr2 = {5 , 2 , 7, 9 , 1};
        int [] arr3 = {5 , 6 , 7, 9 , 1};

        BST bst1 = new BST();
        BST bst2 = new BST();
        BST bst3 = new BST();

        Node root1 = bst1.buildBST(arr1);
        Node root2 = bst2.buildBST(arr2);
        Node root3 = bst3.buildBST(arr3);


        printBST(root1);
        printBST(root2);
        printBST(root3);

        printInorder(root1);
        System.out.println();
        printInorder(root2);
        System.out.println();
        printInorder(root3);
        System.out.println();
    }
}