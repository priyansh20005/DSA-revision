//dijkistra algo banana tree

import java.util.*;

public class temp {

    public static class Edge{
        int src;
        int dest;
        int wt;
        HashSet<Integer> treeNo;
        Edge(int s, int d , int w , int treeNo){
            this.src =s;
            this.dest=d;
            this.wt=w;
            this.treeNo.add(treeNo);
        }
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());
        ArrayList<ArrayList<ArrayList<Integer>>> superTree = new ArrayList<>() ;
        // tree of tree
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

        ArrayList<Integer> branch = new ArrayList<>();
        HashSet<Integer> tree1 = new HashSet<>();
        ArrayList<HashSet<Integer>> allTreesSet = new ArrayList<>();

        while(N>0){
            N--;
            String line = sc.nextLine().trim();

            if(line.equals("break")){
                //end of curr tree
                if(!tree.isEmpty()){
                    superTree.add(new ArrayList<>(tree));
                    tree.clear();
                    allTreesSet.add(new HashSet<>(tree1));
                    tree1.clear();
                }
            }else{
                String[] parts = line.split("\\s+");
                for(String num: parts){
                    branch.add(Integer.parseInt(num));
                    tree1.add(Integer.parseInt(num));
                }
                tree.add(new ArrayList<>(branch));
                branch.clear();
            }
        }

        if(!tree.isEmpty()){
            superTree.add(new ArrayList<>(tree));
            tree.clear();
        }

        // taking src n dest input
        String line = sc.nextLine().trim();
        String[] parts = line.split("\\s+");
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        
        System.out.println("successful run");

        HashMap<Integer,ArrayList<Edge>> graph = new ArrayList<>();
        ArrayList<Edge> neigh = new ArrayList<>();

        int treeNo=0;
        for(ArrayList<ArrayList<Integer>> tempTree: superTree){
            treeNo++;
            for(ArrayList<Integer> tempBranch: tempTree){
                int child = tempBranch.get(0);
                graph.set(child,new ArrayList<>());
                for(i=1;i<tempBranch.size();i++){
                    int dest= tempBranch.get(i);
                    
                    // Edge temp = new Edge(child , dest , 1 ,treeNo );
                    // neigh.add(new Edge(child , dest , 1 ,treeNo));
                    graph.set(dest,new ArrayList<>());
                    graph.get(child).add(new Edge(child , dest , 1 ,treeNo));
                    graph.get(dest).add(new Edge(dest,child ,0, treeNo));
                }
            }
        }
    }
}
