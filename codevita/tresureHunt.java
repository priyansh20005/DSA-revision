
public class tresureHunt {

	static int N, M, K;
	static char[][] grid;
	static int[] vals = new int[4]; // pearl($), platinum(*), gold(%), diamond(+)
	static int[][][] memo; // memo[r][c][rem]

	static int getVal(char ch) {
		if (ch == '$') return vals[0];
		if (ch == '*') return vals[1];
		if (ch == '%') return vals[2];
		if (ch == '+') return vals[3];
		return 0;
	}

	// f(r,c,rem): max additional value collectible starting from standing at (r,c) with rem steps remaining
	static int dfs(int r, int c, int rem) {
		if (rem < 0) return Integer.MIN_VALUE/4;
		int stored = memo[r][c][rem];
		if (stored != Integer.MIN_VALUE) return stored;

		int best = Integer.MIN_VALUE/4;
		// can we stop here? allowed only if not in last row
		if (r != N-1) best = 0; // stop and collect nothing more

		// Move left/right
		for (int dc : new int[]{-1, 1}) {
			int nc = c + dc;
			if (nc < 0 || nc >= M) continue;
			if (grid[r][nc] == '#') continue; // cannot stand on rock by moving horizontally
			int cost = 1; // moving into adjacent treasure costs 1
			int nr = r;
			int collected = getVal(grid[nr][nc]);
			// slide if unstable
			while (true) {
				if (nr == N-1) break; // bottom row stable
				if (grid[nr+1][nc] == '#') break; // stable above rock
				// slide down one
				nr++;
				cost++;
				collected += getVal(grid[nr][nc]);
			}
			if (cost <= rem) {
				int sub = dfs(nr, nc, rem - cost);
				if (sub > Integer.MIN_VALUE/8) best = Math.max(best, collected + sub);
			}
		}

		// Climb upward using rocks: repeated pattern rock,treasure
		int curR = r;
		int climbCost = 0;
		int climbCollected = 0;
		while (true) {
			// need rock above and treasure above that
			if (curR - 1 < 0) break;
			if (grid[curR - 1][c] != '#') break;
			if (curR - 2 < 0) break;
			if (grid[curR - 2][c] == '#') break; // cannot land on rock

			// perform one climb to the treasure two rows above
			curR -= 2;
			climbCost += 1; // climbing onto treasure counts 1
			climbCollected += getVal(grid[curR][c]);

			// after landing, you may slide if unstable
			int nr = curR;
			int slideCost = 0;
			int slideCollected = 0;
			while (true) {
				if (nr == N-1) break;
				if (grid[nr+1][c] == '#') break;
				nr++;
				slideCost++;
				slideCollected += getVal(grid[nr][c]);
			}

			int totalCost = climbCost + slideCost;
			if (totalCost <= rem) {
				int sub = dfs(nr, c, rem - totalCost);
				if (sub > Integer.MIN_VALUE/8) best = Math.max(best, climbCollected + slideCollected + sub);
			}

			// continue climbing further if possible
		}

		memo[r][c][rem] = best;
		return best;
	}

	public static void main(String[] args) throws Exception {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		if (!sc.hasNextInt()) return;
		N = sc.nextInt();
		M = sc.nextInt();
		grid = new char[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				String tok = sc.next();
				grid[i][j] = tok.charAt(0);
			}
		}
		int sx = sc.nextInt();
		int sy = sc.nextInt();
		// values: pearl, platinum, gold, diamond
		vals[0] = sc.nextInt();
		vals[1] = sc.nextInt();
		vals[2] = sc.nextInt();
		vals[3] = sc.nextInt();
		K = sc.nextInt();

		// starting cell is stable per statement; we collect its value at start
		int startVal = getVal(grid[sx][sy]);

		// memo init
		memo = new int[N][M][K + 1];
		for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) for (int k = 0; k <= K; k++) memo[i][j][k] = Integer.MIN_VALUE;

		int bestAdd = dfs(sx, sy, K);
		if (bestAdd < Integer.MIN_VALUE/8) bestAdd = Integer.MIN_VALUE/4; // no valid
		int result = startVal + Math.max(0, bestAdd);
		// But if staying in start cell (no moves) is not allowed when start in last row? They said start is stable, assume valid
		System.out.println(result);
	}

}

