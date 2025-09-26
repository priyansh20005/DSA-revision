import java.util.*;

public class mirrorBST{

    public static TreeNode mirror(TreeNode root){
        
        // leaf node
        if(root.left ==null && root.right ==null )
        return root;

        //non leaf
        TreeNode temp = root.right;

        if(root.left != null){
            root.right = mirror(root.left);
        }
        if(temp != null){
            root.left = mirror(temp);
        }
        return root;
        // TreeNode temp = root.right;
        // root.right = mirror(root.left);
        // root.left = mirror(temp);
    

    }


    public static void main(String args[]){

    }
}