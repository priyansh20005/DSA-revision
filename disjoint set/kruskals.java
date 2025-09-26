import java.util.*;

// krushkal algo for MST 
// by using dijoint union set

public class kruskals {

    public static class Edge implements Comparable<Edge>{
        int src , dest , wt;
        Edge(int s, int d, int w){
            src=s;
            dest=d;
            wt=w;
        }
        @Override
        public int compareTo(Edge e2){
            return this.wt-e2.wt;
        }
    }
    
    public static class Disjoint{
        // static ArrayList<Integer> parent = new ArrayList<>();
        // static ArrayList<Integer> 
        static public  int[] parent ;
        static public  int[] rank ;

        Disjoint(int n){
            parent = new int[n];
            rank = new int[n];
            Arrays.fill(rank,0);
            for(int i=0 ;i<n;i++)
                parent[i]=i;
        }

        public static int find(int x){
            if(parent[x]==x)    
                return x;
            else
               return  parent[x] = find(parent[x]);
        }

        public static void union(int x, int y){
            int p1 = find(x);
            int p2= find(y);
            int r1 = rank[p1];
            int r2 = rank[p2];
            if(p1==p2)
                return;
            if(r1==r2){
                parent[p1]=p2;
                rank[p2]++;
            }
            else if(r1 > r2){
                parent[p2]=p1;
            }else{
                parent[p1]=p2;
            }
        }
    }
    ////////////////////////////////////////////////////////////////MAIN///////////////
    public static void main(String args[]){

         int[][] edges = {
            {0, 1, 10},
            {0, 2, 6},
            {0, 3, 5},
            {1, 3, 15},
            {2, 3, 4}
        };
        int n = 4;
        System.out.println("MST cost = " + mst(edges, n));


    }
////////////////////////////////////////////////////////////////////////////////////////////////
    public static int mst(int[][] edges ,int n){

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int[] e : edges){
            pq.add(new Edge(e[0],e[1],e[2]));
        }

        Disjoint set1 = new Disjoint(n);

        int finalCost=0;
        int edgeCount=0;  // mst has (n-1 edges)
        while(edgeCount < n-1){
            Edge e = pq.remove();
            if(set1.find(e.src) == set1.find(e.dest)){
                // already they are connected (next connection cause cycle formation)
                continue;
            }
            else{
                // not connected by single path
                finalCost+=e.wt;
                set1.union(e.src,e.dest);
                edgeCount++;
            }

        }
        return finalCost;
    }

}
