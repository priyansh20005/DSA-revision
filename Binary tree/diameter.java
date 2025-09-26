// diameter - highest no of nodes beatween two leafs
// O(n^2)
//O(n)

public class diameter{

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


    public static class info{
        int height;
        int dia;
        info(int height , int dia){
            this.height= height;
            this.dia = dia;
        }
    }

    ////////////O(n)
    public static info calDiameter(Node root){
        if(root == null){
            return new info(0 , 0);
        }
        else if(root.left== null && root.right == null){
            return new info(1 , 1);
        }

        info left = calDiameter(root.left);
        info right = calDiameter(root.right);

        int height = Math.max(left.height , right.height)+1;
        int selfDia = left.height+ right.height+1;

        int finalDia = Math.max(Math.max( right.dia, left.dia ),selfDia );

        return new info(height , finalDia);

    }


//////////////O(n^2)//////////////////////////////////
    public static int height(Node root){
        if(root == null) return 0;
        if(root.right==null && root.left == null) return 1;

        int leftheight= height(root.left);
        int rightheight = height(root.right);

        return Math.max(leftheight , rightheight)+1;
    }

    public static int diameter(Node root){
        if(root ==null) return 0;
        if(root.right== null && root.left == null) return 1;

        int leftHeight=height(root.left);
        int rightHeight = height(root.right);

        int diaRoot = leftHeight+rightHeight+1;

        int leftDia = diameter(root.left);
        int rightDia = diameter(root.right);

        int temp=Math.max(leftDia , rightDia);
        
        return Math.max( temp, diaRoot);
    }



    public static void main(String args[]){

    }
}