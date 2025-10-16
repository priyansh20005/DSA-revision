import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Cleaner and more readable implementation for the "Secret Key" problem.
 * Behavior is unchanged from the previous implementation.
 */
public class secretKey {

	// Grid size and key length
	static int rows, cols, keyLength, clueCount;

	// grid of characters
	static char[][] grid;

	// allowedAtTime[t][r][c] = true iff the cell (r,c) can be the character at time (t+1)
	static boolean[][][] allowedAtTime;

	// visited cells during DFS
	static boolean[][] used;

	// the unique key found (if any) and a flag for ambiguity
	static String uniqueKey = null;
	static boolean ambiguous = false;

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);

		if (!in.hasNext()) return; // no input

		rows = in.nextInt();
		cols = in.nextInt();

		grid = new char[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				String s = in.next();
				grid[r][c] = s.charAt(0);
			}
		}

		keyLength = in.nextInt();
		clueCount = in.nextInt();

		// initialize all cells allowed for every time step
		allowedAtTime = new boolean[keyLength][rows][cols];
		for (int t = 0; t < keyLength; t++)
			for (int r = 0; r < rows; r++)
				Arrays.fill(allowedAtTime[t][r], true);

		// apply clues: each clue says at time t the letter is NOT inside the rectangle
		for (int k = 0; k < clueCount; k++) {
			int t = in.nextInt();
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();

			if (t < 1 || t > keyLength) continue; // safety
			int ti = t - 1; // zero-based time index

			// mark the rectangle as forbidden for time ti
			for (int r = x1 - 1; r <= x2 - 1; r++) {
				if (r < 0 || r >= rows) continue;
				for (int c = y1 - 1; c <= y2 - 1; c++) {
					if (c < 0 || c >= cols) continue;
					allowedAtTime[ti][r][c] = false;
				}
			}
		}

		// if any time step has no allowed cells, clues are insufficient/incorrect
		if (anyTimeFullyExcluded()) {
			System.out.println("Not enough clues");
			return;
		}

		used = new boolean[rows][cols];

		// start DFS from every allowed start cell at time 0
		for (int r = 0; r < rows && !ambiguous; r++) {
			for (int c = 0; c < cols && !ambiguous; c++) {
				if (allowedAtTime[0][r][c]) {
					dfs(r, c, 0, new StringBuilder());
				}
			}
		}

		if (uniqueKey != null && !ambiguous) System.out.println(uniqueKey);
		else System.out.println("Not enough clues");
	}

	// Check whether any time-step has all cells excluded
	static boolean anyTimeFullyExcluded() {
		for (int t = 0; t < keyLength; t++) {
			boolean any = false;
			for (int r = 0; r < rows && !any; r++)
				for (int c = 0; c < cols; c++) if (allowedAtTime[t][r][c]) { any = true; break; }
			if (!any) return true;
		}
		return false;
	}

	// 4-direction moves
	static final int[] DR = {-1, 1, 0, 0};
	static final int[] DC = {0, 0, -1, 1};

	// Depth-first search that builds the key along a path of length keyLength
	static void dfs(int r, int c, int timeIdx, StringBuilder sb) {
		if (ambiguous) return; // stop early when we know there's more than one key
		if (!allowedAtTime[timeIdx][r][c]) return;

		used[r][c] = true;
		sb.append(grid[r][c]);

		if (timeIdx == keyLength - 1) {
			// built a full-length key
			String key = sb.toString();
			if (uniqueKey == null) uniqueKey = key;
			else if (!uniqueKey.equals(key)) ambiguous = true;
		} else {
			int nextTime = timeIdx + 1;
			for (int k = 0; k < 4 && !ambiguous; k++) {
				int nr = r + DR[k];
				int nc = c + DC[k];
				if (inside(nr, nc) && !used[nr][nc] && allowedAtTime[nextTime][nr][nc]) {
					dfs(nr, nc, nextTime, sb);
				}
			}
		}

		// backtrack
		sb.deleteCharAt(sb.length() - 1);
		used[r][c] = false;
	}

	static boolean inside(int r, int c) {
		return r >= 0 && r < rows && c >= 0 && c < cols;
	}

	// Lightweight input reader (keeps same behavior but simpler name)
	static class InputReader {
		BufferedReader br;
		StringTokenizer st;
		InputReader(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) {
				String line = br.readLine();
				if (line == null) return null;
				st = new StringTokenizer(line);
			}
			return st.nextToken();
		}
		boolean hasNext() throws IOException {
			br.mark(1);
			int c = br.read();
			if (c == -1) return false;
			br.reset();
			return true;
		}
		int nextInt() throws IOException { return Integer.parseInt(next()); }
	}
}
