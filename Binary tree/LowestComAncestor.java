import java.util.*;


// first or lowest common ancestor of two nodes in the same tree

// 1st find path p1 , p2 from root to nodes
// comare p1,p2 for common 

// 2 approaches 

public class LowestComAncestor{


/// best no extra space and single go ///////////////
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // base case
        if(root == p || root == q) return root;
        else if(root == null) return null;

        TreeNode leftLCA = lowestCommonAncestor(root.left , p , q);
        TreeNode rightLCA = lowestCommonAncestor(root.right , p, q);

        if(leftLCA != null && rightLCA != null) 
            return root;

        else if(leftLCA != null && rightLCA == null){
            return leftLCA;
        }
        else { //(rightLCA != null && leftLCA == null)
            return rightLCA; 
        }

    }


    /////////////////  tc-o(n)  SC- o(n)   not optimized ///////////////////
    public static Node lowComAncestor(Node root , Node n1 , Node n2){

        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        // path1 root to n1
        boolean p1 = findPath(root , n1 , path1);

        //path2 root to n2
        boolean p2 = findPath(root , n2 , path2);

        if (!(p1 && p2)) return null ;// not got path 

        int i = 0;
        while(i < path1.size() && i<path2.size()){
            if(path1.get(i) != path2.get(i)) 
                break;  //   path changed at this point
            else
                i++;
        }

        // so the last common node is at (i-1) idx

        return path1.get(i-1);

        // for(int i = path1.size()-1 ; i>= 0 ; i--)  {
        //     for(int j = path2.size()-1 ;j>=0 ; j--){
        //         if(path1.get(i) == path2.get(j)){
        //             return path2.get(j);
        //         }
        //     }
        // }

    }


    // find path
    public static boolean findPath(Node root , Node req , ArrayList<Node> path ){
        if(root == null)
            return false;

        path.add(root);
         if(req == root ) 
            return true ;
        

        if(findPath(root.left , req , path)|| findPath(root.right , req, path))
            return true;

        else 
            path.remove(path.size()-1);
       return false;

    }


    public static void main(String args[]){

    }
}