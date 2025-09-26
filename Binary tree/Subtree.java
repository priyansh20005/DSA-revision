

import java.util.*;

public class Subtree{

    public static boolean isSubtree(TreeNode root , TreeNode subroot){
        //base condition
        if(root == null) return false;

        // 1st check if present or not -> traversal
       if(root.val == subroot.val){
            if(isIdentical(root , subroot)){
            return true;
            }
       }

    //    boolean leftAns= isSubtree(root.left , subroot);
    //    boolean rightAns = isSubtree(root.right , subroot);

    //    return leftAns || rightAns ;

        return isSubtree(root.left , subroot) || isSubtree(root.right , subroot) ; 
        //better
    }

   public static boolean isIdentical(TreeNode root , TreeNode subroot){
       
        if(root == null && subroot == null) return true;

        else if((root ==null && subroot!= null )||(root!= null && subroot==null)) 
        return false;

        if(root.val != subroot.val) return false;
        boolean left = isIdentical(root.left , subroot.left);
        boolean right =isIdentical(root.right , subroot.right);

        return left && right;
   }



    public static void main(String args[]){

    }
}