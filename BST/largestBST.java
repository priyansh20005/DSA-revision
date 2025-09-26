
// largest bst in binary tree

import java.util.*;

public class largestBST{

    public static  class info{
        boolean isBST ;
        int size ;
        int min;
        int max;

        info(boolean isBST , int s , int mn , int mx){
            this.isBST = isBST;
            size=s;
            min=mn;
            max = mx;
        }
    }

    static int count=0;
    static Node bst_root=null;

    public static info findLargestBST(Node root){
        if(root == null){
            return new info(true , 0  , Integer.MAX_VALUE ,Integer.MIN_VALUE);
        }

        info left = findLargestBST(root.left);
        info right = findLargestBST(root.right);
        info self = new info(true , 0  , root.val ,root.val );

        // self info creation
        if(root.val <= left.max || root.val >= right.min){
            // false not a bst
            return new info(false , )
        }

        self.isBST = left.isBST && right.isBST ;
        ///include root in bst
        if(left.isBST && right.isBST){
            self.isBST = left.isBST && right.isBST ;
            self.size = left.size+right.size+1;
            self.min = Math.min(Math.min(left.min , right.min) , root.val);
            self.max = Math.max(Math.max(left.max , right.max) , root.val);
        }

        else if(left.isBST == false && right.isBST ==true){
            self.
        }

        if(count < self.size){
            bst_root = root;
            count = self.size;
        }

        return self;
    }


    public static void main(String args[]){

    }
}