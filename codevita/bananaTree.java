//dijkistra algo

import java.util.*;

public class bananaTree {

    // Simple edge representation
    static class Edge {
        int to;
        int cost;
        Edge(int to, int cost) { this.to = to; this.cost = cost; }
    }

    // State = a hanging spot (node) located on a particular tree index
    static class State {
        int node;
        int treeIndex;
        State(int node, int treeIndex) { this.node = node; this.treeIndex = treeIndex; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State s = (State)o;
            return node == s.node && treeIndex == s.treeIndex;
        }
        @Override public int hashCode() { return Objects.hash(node, treeIndex); }
        @Override public String toString() { return node + "@" + treeIndex; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Read integer N (skip blank lines until a valid integer line found)
        Integer N = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            try {
                N = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                // If first non-empty line isn't an integer, abort
                System.out.println("-1");
                return;
            }
            break;
        }
        if (N == null) {
            System.out.println("-1");
            return;
        }

        // 2) Read next N non-empty description lines (ignore blank lines)
        List<List<List<Integer>>> trees = new ArrayList<>(); // list of trees; each tree = list of branches
        List<List<Integer>> currentTree = new ArrayList<>();

        int readCount = 0;
        while (readCount < N && sc.hasNextLine()) {
            String raw = sc.nextLine();
            if (raw == null) break;
            String line = raw.trim();
            if (line.isEmpty()) continue; // do not count blank lines toward N

            readCount++;

            if (line.equalsIgnoreCase("break")) {
                // finish current tree
                trees.add(new ArrayList<>(currentTree));
                currentTree.clear();
            } else {
                // parse integers from the line
                String[] parts = line.split("\\s+");
                List<Integer> branch = new ArrayList<>();
                for (String p : parts) {
                    if (p.isEmpty()) continue;
                    try {
                        branch.add(Integer.parseInt(p));
                    } catch (NumberFormatException ex) {
                        // ignore invalid token
                    }
                }
                if (!branch.isEmpty()) currentTree.add(branch);
            }
        }
        // add last tree if not yet closed by break
        if (!currentTree.isEmpty()) trees.add(new ArrayList<>(currentTree));

        // 3) Read remaining tokens and find two integers for start and destination
        Integer start = null, dest = null;
        while ((start == null || dest == null) && sc.hasNext()) {
            String tok = sc.next();
            if (tok == null) break;
            tok = tok.trim();
            if (tok.isEmpty()) continue;
            try {
                int v = Integer.parseInt(tok);
                if (start == null) start = v;
                else if (dest == null) dest = v;
            } catch (NumberFormatException ex) {
                // ignore
            }
        }
        if (start == null || dest == null) {
            System.out.println("-1");
            return;
        }

        // 4) Build graph: each (node, treeIndex) is a separate vertex
    Map<State, Integer> stateId = new HashMap<>();
    List<State> idToState = new ArrayList<>();
    Map<Integer, Set<Integer>> nodeToTrees = new HashMap<>(); // node -> set of treeIndices where it appears

        // helper to get/create id
        java.util.function.BiFunction<Integer, Integer, Integer> getId = (node, tIdx) -> {
            State s = new State(node, tIdx);
            Integer id = stateId.get(s);
            if (id != null) return id;
            int nid = idToState.size();
            stateId.put(s, nid);
            idToState.add(s);
            nodeToTrees.computeIfAbsent(node, k -> new HashSet<>()).add(tIdx);
            return nid;
        };

        // add nodes and edges for each tree
        List<List<Edge>> adj = new ArrayList<>();

        for (int t = 0; t < trees.size(); t++) {
            List<List<Integer>> tree = trees.get(t);
            // register all nodes in this tree
            for (List<Integer> branch : tree) {
                for (int x : branch) getId.apply(x, t);
            }
            // add edges for each branch: branch[0] is lower, others are above it
            for (List<Integer> branch : tree) {
                if (branch.size() < 2) continue;
                int lower = branch.get(0);
                int lowerId = getId.apply(lower, t);
                // ensure adjacency list size
                while (adj.size() < idToState.size()) adj.add(new ArrayList<>());
                for (int i = 1; i < branch.size(); i++) {
                    int upper = branch.get(i);
                    int upperId = getId.apply(upper, t);
                    // ensure adjacency list size again after adding
                    while (adj.size() < idToState.size()) adj.add(new ArrayList<>());
                    // climb up costs 1, climb down costs 0
                    adj.get(lowerId).add(new Edge(upperId, 1));
                    adj.get(upperId).add(new Edge(lowerId, 0));
                }
            }
        }
        // ensure adj sized for any nodes without edges
    while (adj.size() < idToState.size()) adj.add(new ArrayList<>());

        // add switching edges between same node across different trees (cost 1)
        for (Map.Entry<Integer, Set<Integer>> e : nodeToTrees.entrySet()) {
            Integer node = e.getKey();
            List<Integer> lists = new ArrayList<>(e.getValue());
            for (int i = 0; i < lists.size(); i++) {
                for (int j = i + 1; j < lists.size(); j++) {
                    int t1 = lists.get(i), t2 = lists.get(j);
                    Integer id1 = stateId.get(new State(node, t1));
                    Integer id2 = stateId.get(new State(node, t2));
                    if (id1 == null || id2 == null) continue; // defensive
                    adj.get(id1).add(new Edge(id2, 1));
                    adj.get(id2).add(new Edge(id1, 1));
                }
            }
        }

        // 5) Dijkstra from all start occurrences (start node may appear in multiple trees in input mess)
        List<Integer> startTrees = new ArrayList<>(nodeToTrees.getOrDefault(start, Collections.emptySet()));
        if (startTrees.isEmpty()) {
            System.out.println("-1");
            return;
        }
        // Use 0-1 BFS (Deque) because edge weights are 0 or 1
        final int V = idToState.size();
        final int INF_INT = Integer.MAX_VALUE / 4;
        int[] dist = new int[V];
        Arrays.fill(dist, INF_INT);
        Deque<Integer> dq = new ArrayDeque<>();

        for (int tIdx : startTrees) {
            Integer sid = stateId.get(new State(start, tIdx));
            if (sid == null) continue;
            dist[sid] = 0;
            dq.addFirst(sid);
        }

        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            for (Edge e : adj.get(u)) {
                int v = e.to;
                int nd = dist[u] + e.cost;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    if (e.cost == 0) dq.addFirst(v);
                    else dq.addLast(v);
                }
            }
        }

        // find best among all destination occurrences
        int answer = INF_INT;
        for (int tIdx : nodeToTrees.getOrDefault(dest, Collections.emptySet())) {
            Integer id = stateId.get(new State(dest, tIdx));
            if (id == null) continue;
            if (id >= 0 && id < dist.length) answer = Math.min(answer, dist[id]);
        }

        if (answer == INF_INT) System.out.println("-1");
        else System.out.println(answer);
    }

}
