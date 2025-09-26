///// topview and bottom view
/// // left view / right view

import java.util.HashMap;
import java.util.Queue;

public class Topview{

    static class info{
        Node node;
        int horizontalDistance;

        info(Node node ,int hd){
            this.node = node ;
            horizontalDistance=hd;
        }
    }
////////////////////////////top view function/////////////////////////
    public static void topView(Node root){
        Queue<info> q = new LinkedList<>();

        HashMap<Integer , Node> map = new HashMap<>();

        int min=0  , max=0;

        q.add(new info(root , 0));
        q.add(null);

        while(!q.isEmpty()){
            info curr = q.remove();
            if(curr==null){
                if(q.isEmpty()) break;
                else q.add(null);
            }
       else{
            if(map.containsKey(curr.horizontalDistance)==false){
                // first occurence of this distance
                map.put(curr.horizontalDistance , curr.node);
            }

            if(curr.node.left != null){
                q.add(new info(curr.node.left , curr.horizontalDistance -1));
                min = Math.min(min , curr.horizontalDistance-1);  
            }

            if(curr.node.right != null){
                q.add(new info(curr.node.right , curr.horizontalDistance+1));
                max = Math.max(max , curr.horizontalDistance+1);
            }
        }
    }

        for(int i =min ; i<= max; i++){
            Node curr = map.get(i);
            System.out.print(curr.data+" ");
        }

    }

//////////////////////////// bottom view function///////////////////////////

      public static void bottomView(Node root){
        Queue<info> q = new LinkedList<>();

        HashMap<Integer , Node> map = new HashMap<>();

        int min=0  , max=0;

        q.add(new info(root , 0));
        q.add(null);

        while(!q.isEmpty()){
            info curr = q.remove();
            if(curr==null){
                if(q.isEmpty()) break;
                else q.add(null);
            }
       else{
            // if(map.containsKey(curr.horizontalDistance)==false){
                // first occurence of this distance
                map.put(curr.horizontalDistance , curr.node);    //// just over ride till end 
            // }

            if(curr.node.left != null){
                q.add(new info(curr.node.left , curr.horizontalDistance -1));
                min = Math.min(min , curr.horizontalDistance-1);  
            }

            if(curr.node.right != null){
                q.add(new info(curr.node.right , curr.horizontalDistance+1));
                max = Math.max(max , curr.horizontalDistance+1);
            }
        }
    }
        for(int i =min ; i<= max; i++){
            Node curr = map.get(i);
            System.out.print(curr.data+" ");
        }

    }
//////////////////////// right view /////////////////////////////////
 public List<Integer> rightSideView(TreeNode root) {
       
        Queue<TreeNode> q =  new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        q.add(root);
        q.add(null);

        TreeNode curr, prev=root;

        while(!q.isEmpty()){
            curr = q.remove();
            if(curr == null){
                ans.add(prev.val);
                if(q.isEmpty()) break;
                else q.add(null);
            }
            else{
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right!= null){
                    q.add(curr.right);
                }
                prev = curr;
            }

        }
        return ans;
    }

    //////////////////////// left view /////////////////////////////////
 public List<Integer> leftSideView(TreeNode root) {
       
        Queue<TreeNode> q =  new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        q.add(root);
        q.add(null);
        ans.add(root.val);

        TreeNode curr, next;

        while(!q.isEmpty()){
            curr = q.remove();
            if(curr == null){
                // ans.add(prev.val);
                if(q.isEmpty()) break;
                else {
                    next = q.peek();
                    ans.add(next.val);
                    q.add(null); 
                }
            }
            else{
                if(curr.left != null){
                    q.add(curr.left);
                }
                if(curr.right!= null){
                    q.add(curr.right);
                }
            }

        }
        return ans;
    }

    public static void main(String args[]){

    }
}