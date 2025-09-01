

import java.util.*;


public class k_level_nodes{

    // recursive 
    public static void kLevelRec(Node root , int k , int currLevel, ArrayList<Integer> ans){

        if(root == null || k==0) 
            return ;
        else if(currLevel ==k){
            ans.add(root.data);
            return ;
        }

        kLevelRec(root.left , k , currLevel+1 , ans);
        kLevelRec(root.right , k , currLevel+1 , ans);
    }

    // iteratively - level order
    public static List<Integer> kLevel(Node root , int k){

        Queue<Node> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        if(root  == null || k==0 ) return ans;

        q.add(root);
        q.add(null);
        int level = 1;

        while(!q.isEmpty()){

            Node curr = q.remove();
            if(curr == null){
                if(q.isEmpty()) break;
                else{
                    level++;
                    q.add(null);
                }
            }
            else{
                if(level == k)
                    ans.add(curr.data);
                else if(level ==k+1)
                     break;
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
            
        }
        return ans;
    }

    public static void main(String args[]){

    }
}