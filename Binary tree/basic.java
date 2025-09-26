// preorder-> root - left-right
// inorder -> left - root -right
// postorder-> left-right-root

// levelorder -> use queue structure  // iterative

// height of tree
// count no of nodes in tree


import java.util.*;

public class basic{

    public static class Node{
        public int data;
        public Node left;
        public Node right;

        Node(int data){
            this.data= data;
            this.left=null;
            this.right =null;
        }
    }

    static class BinaryTree{
         static Node head_root= null;
         static int idx = -1;

         public static Node buildTreePreOrder(int [] nodes  ){
            idx++;
            if(nodes[idx] == -1){
                return null;
            }

            Node newNode = new Node(nodes[idx]);

            newNode.left= buildTreePreOrder(nodes );
            newNode.right= buildTreePreOrder(nodes );

            return newNode;
         }

    }

// sum of all nodes in tree
    public static int nodeSum(Node root){
        if(root == null ) return 0;
        else if(root.left== null && root.right == null) return root.data;

        int leftSum = nodeSum(root.left);
        int rightSum= nodeSum(root.right);

        int totalSum = leftSum+rightSum+ root.data;
        return totalSum;
    }



// total no of nodes
    public static int count(Node root){
        if(root == null) return 0;
        else if(root.left == null && root.right == null) return 1;

        int leftCount = count(root.left);
        int rightCount= count(root.right);

        int totalCount = leftCount+rightCount+1;
        return totalCount;
    }

// find max hight or depth
    public static int height(Node head ){
        if(head== null){ // no node
            return 0;
        }
        else if(head.left==null && head.right == null){  // leaf node
            return 1;
        }

        int leftHeight = height(head.left);
        int rightHeight = height(head.right);

        int myHeight = Math.max(leftHeight , rightHeight) +1;
        return myHeight;

    }


    public static void printTreeLevelOrder(Node root){

        Queue<Node> que = new LinkedList<>();

        que.add(root);
        que.add(null);

        while(que.size() > 0){
            Node curr = que.remove();
            if(curr == null){
                if(que.size()== 0) break ;

                System.out.println();
                que.add(null);
                continue;
            }

            System.out.print(curr.data+" ");
            if(curr.left != null) que.add(curr.left);
            if(curr.right != null) que.add(curr.right);
            
        }
        


        
    }

    public static void printTreePreOrder(Node root){

        if(root == null){
            System.out.print( "null -");
            return;
        }

        System.out.print( root.data+" -");
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);
    }

    public static void printTreeInorder(Node root){

        if(root == null){
            System.out.print(" null -");
            return;
        }
        printTreeInorder(root.left);
        System.out.print(root.data+" -");
        printTreeInorder(root.right);
    }

    public static void printTreePostOrder(Node root){
        if(root == null){
            System.out.print(" null -");
            return;
        }
        printTreePostOrder(root.left);
        printTreePostOrder(root.right);
        System.out.print("- " + root.data);
    }

    public static void main(String args[]){

        int nodes[] = {1 , 2, 4 , -1 , -1 , 5 , -1 , -1 , 3 ,-1 , 6,  -1 , -1};

        BinaryTree tree = new BinaryTree();
        
        Node head = tree.buildTreePreOrder(nodes );

        // printTreePreOrder(head);
        // printTreeInorder(head);
        // printTreePostOrder(head);
        printTreeLevelOrder(head);
        System.out.println("\nheight of tree is-> "+ height(head)); 
        System.out.println("number of nodes -> "+ count(head));
        System.out.println("Sum of nodes -> "+ nodeSum(head));  
    }
}