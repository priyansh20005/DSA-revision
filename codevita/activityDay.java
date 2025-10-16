import java.util.*;
import java.io.*;

/**
 * Simpler and more readable solution for the "Activity Day" problem.
 * Approach in plain words:
 * 1. Collect all x and y coordinates that partition the NxM grid (including borders).
 * 2. These coordinates define a small-cell grid. Each small cell is the rectangle between consecutive xs and ys.
 * 3. Mark which small-cell edges are walls using the given partition segments.
 * 4. Use union-find to join adjacent small cells that are not separated by walls -> each component is a box.
 * 5. Compute boxes, assign strings left-to-right then bottom-to-top, rank boxes, and output ASCII-sum of Raghu's box string.
 */
public class activityDay {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		if (!sc.hasNext()) return;

		int N = sc.nextInt(); // width (x from 0..N)
		int M = sc.nextInt(); // height (y from 0..M)
		int L = sc.nextInt(); // number of partition segments

		// Collect all partition segments and endpoints
		List<int[]> verticalSegments = new ArrayList<>();   // each: {x, y1, y2}
		List<int[]> horizontalSegments = new ArrayList<>(); // each: {y, x1, x2}

		TreeSet<Integer> xsSet = new TreeSet<>(); xsSet.add(0); xsSet.add(N);
		TreeSet<Integer> ysSet = new TreeSet<>(); ysSet.add(0); ysSet.add(M);

		for (int i = 0; i < L; i++) {
			int x1 = sc.nextInt(), y1 = sc.nextInt(), x2 = sc.nextInt(), y2 = sc.nextInt();
			if (x1 == x2) {
				int a = Math.min(y1, y2), b = Math.max(y1, y2);
				verticalSegments.add(new int[]{x1, a, b});
				xsSet.add(x1); ysSet.add(a); ysSet.add(b);
			} else {
				// horizontal
				int a = Math.min(x1, x2), b = Math.max(x1, x2);
				horizontalSegments.add(new int[]{y1, a, b});
				ysSet.add(y1); xsSet.add(a); xsSet.add(b);
			}
		}

		int R = sc.nextInt(); // Raghu's rank

		List<String> strings = new ArrayList<>();
		while (sc.hasNext()) strings.add(sc.next());

		List<Integer> xs = new ArrayList<>(xsSet);
		List<Integer> ys = new ArrayList<>(ysSet);

		Collections.sort(xs); Collections.sort(ys);

		int cols = xs.size() - 1; // number of small-cell columns
		int rows = ys.size() - 1; // number of small-cell rows

		// For each possible vertical edge (located at some x in xs), mark which y-segments are walls.
		// verticalWall[k][j] corresponds to edge at x = xs[k] between ys[j]..ys[j+1]
		boolean[][] verticalWall = new boolean[xs.size()][rows];
		boolean[][] horizontalWall = new boolean[ys.size()][cols];

		// Mark vertical walls from segments
		Map<Integer,Integer> xToIndex = new HashMap<>();
		for (int i = 0; i < xs.size(); i++) xToIndex.put(xs.get(i), i);
		Map<Integer,Integer> yToIndex = new HashMap<>();
		for (int i = 0; i < ys.size(); i++) yToIndex.put(ys.get(i), i);

		for (int[] seg : verticalSegments) {
			int x = seg[0], a = seg[1], b = seg[2];
			Integer xi = xToIndex.get(x);
			if (xi == null) continue;
			// mark all small y-intervals fully inside [a,b]
			for (int j = 0; j < rows; j++) {
				int yB = ys.get(j), yT = ys.get(j+1);
				if (a <= yB && yT <= b) verticalWall[xi][j] = true;
			}
		}

		for (int[] seg : horizontalSegments) {
			int y = seg[0], a = seg[1], b = seg[2];
			Integer yi = yToIndex.get(y);
			if (yi == null) continue;
			for (int i = 0; i < cols; i++) {
				int xL = xs.get(i), xR = xs.get(i+1);
				if (a <= xL && xR <= b) horizontalWall[yi][i] = true;
			}
		}

		// Borders are walls
		for (int j = 0; j < rows; j++) { // left and right borders
			verticalWall[0][j] = true; // x=0
			verticalWall[xs.size()-1][j] = true; // x=N
		}
		for (int i = 0; i < cols; i++) { // bottom and top borders
			horizontalWall[0][i] = true; // y=0
			horizontalWall[ys.size()-1][i] = true; // y=M
		}

		// Union-Find for small cells
		int total = rows * cols;
		DSU dsu = new DSU(total);
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int id = r * cols + c;
				// right neighbor exists if no vertical wall at x = xs[c+1] for this y-segment
				int vxIndex = c+1; // vertical edge between column c and c+1
				if (c+1 < cols && !verticalWall[vxIndex][r]) {
					dsu.union(id, r * cols + (c+1));
				}
				// top neighbor exists if no horizontal wall at y = ys[r+1] for this x-segment
				int hyIndex = r+1;
				if (r+1 < rows && !horizontalWall[hyIndex][c]) {
					dsu.union(id, (r+1) * cols + c);
				}
			}
		}

		// Build boxes (components): compute bounding rectangle for each component
		Map<Integer, Box> compToBox = new LinkedHashMap<>();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int id = r * cols + c;
				int p = dsu.find(id);
				int xL = xs.get(c), xR = xs.get(c+1);
				int yB = ys.get(r), yT = ys.get(r+1);
				Box Bbox = compToBox.get(p);
				if (Bbox == null) compToBox.put(p, new Box(xL,yB,xR,yT));
				else {
					Bbox.minX = Math.min(Bbox.minX, xL);
					Bbox.minY = Math.min(Bbox.minY, yB);
					Bbox.maxX = Math.max(Bbox.maxX, xR);
					Bbox.maxY = Math.max(Bbox.maxY, yT);
				}
			}
		}

		List<Box> boxes = new ArrayList<>(compToBox.values());

		// Assignment order: left-to-right then bottom-to-top -> meaning sort by y (asc) then x (asc)
		boxes.sort(Comparator.comparingInt((Box b) -> b.minY).thenComparingInt(b -> b.minX));

		// Map strings to boxes in that order
		Map<Box, String> boxString = new HashMap<>();
		for (int i = 0; i < boxes.size() && i < strings.size(); i++) boxString.put(boxes.get(i), strings.get(i));

		// Ranking: area desc, x asc, y asc
		List<Box> ranked = new ArrayList<>(boxes);
		ranked.sort((b1, b2) -> {
			long a1 = (long)(b1.maxX - b1.minX)*(b1.maxY - b1.minY);
			long a2 = (long)(b2.maxX - b2.minX)*(b2.maxY - b2.minY);
			if (a1 != a2) return Long.compare(a2, a1);
			if (b1.minX != b2.minX) return Integer.compare(b1.minX, b2.minX);
			return Integer.compare(b1.minY, b2.minY);
		});

		int idx = R - 1;
		if (idx < 0 || idx >= ranked.size()) {
			System.out.println(0);
			return;
		}

		Box target = ranked.get(idx);
		String ansStr = boxString.getOrDefault(target, "");
		int sum = 0; for (char ch : ansStr.toCharArray()) sum += (int)ch;
		System.out.println(sum);
	}

	static class Box {
		int minX, minY, maxX, maxY;
		Box(int a, int b, int c, int d){ minX=a; minY=b; maxX=c; maxY=d; }
	}

	static class DSU {
		int[] p;
		DSU(int n){ p = new int[n]; for (int i=0;i<n;i++) p[i]=i; }
		int find(int a){ return p[a]==a?a:(p[a]=find(p[a])); }
		void union(int a,int b){ a=find(a); b=find(b); if(a!=b) p[b]=a; }
	}
}
