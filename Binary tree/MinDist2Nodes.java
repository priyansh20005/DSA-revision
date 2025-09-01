import java.util.*;

//// k th ancestor 
/// min dis btw 2 nodes

public class MinDist2Nodes{

    public static int minDistance(Node root , Node n1 , Node n2){
        //edge cases
        if(root ==null || n1 == null || n2 == null)
            return -1;

        /// find lca
        Node lca = findLca(root , n1 , n2);

        // find dist from lca
        int dist1 = getDistfromLca(lca , n1);
        int dist2 = getDistfromLca(lca , n2);

        return dist1+dist2;

    }

    public static Node findLca(Node root , Node n1 , Node n2){
       
        if(root == null || root == n1 || root == n2)
            return root;

        Node leftLca = findLca(root.left , n1 , n2);
        Node rightLca = findLca(root.right , n1 , n2);

        if(leftLca != null && rightLca != null) 
            return root;
        
        else if(leftLca ==null)
            return rightLca;
        else 
            return leftLca;
    }

    public static int getDistfromLca(Node lca , Node n ){
        if(lca == null) 
            return -1;
        if( lca == n)
            return 0;

        int left = getDistfromLca(lca.left , n );
        int right= getDistfromLca(lca.right , n );
        
        if(left != -1){
            left++;
            return left;
        }
        else if (right != -1){
            right++;
            return right;
        }
        else 
            return -1;
    }
//////////////// k th anestor ////////////
    public static int getDistfromLca(Node lca , Node n  , int k , Node ances){
        if(lca == null) 
            return -1;
        if( lca == n)
            return 0;

        int left = getDistfromLca(lca.left , n , ances );
        int right= getDistfromLca(lca.right , n , ances);
        
        if(left != -1){
            left++;
                if(left == k)  ances = lca;
            return left;
        }
        else if (right != -1){
            right++;
                if(k == right)  ances = lca;
            return right;
        }
        else 
            return -1;
    }

    public static void main(String args[]){

    }
}