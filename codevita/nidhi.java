import java.io.*;
import java.util.*;

public class nidhi {
	static class Cmd {
		int exist, neu;
		String dir;
		Cmd(int e, int n, String d) { exist = e; neu = n; dir = d; }
	}

	static class P {
		int x, y;
		P(int x, int y) { this.x = x; this.y = y; }
		@Override public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof P)) return false;
			P p = (P) o;
			return x == p.x && y == p.y;
		}
		@Override public int hashCode() { return x * 31 + y; }
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		line = br.readLine();
		if (line == null) return;
		line = line.trim();
		while (line.length() == 0) line = br.readLine().trim();
		int N = Integer.parseInt(line);
		List<Cmd> cmds = new ArrayList<>();
		for (int i = 0; i < N; ) {
			String s = br.readLine();
			if (s == null) break;
			s = s.trim();
			if (s.length() == 0) continue;
			String[] parts = s.split("\\s+");
			if (parts.length < 3) continue;
			int e = Integer.parseInt(parts[0]);
			int n = Integer.parseInt(parts[1]);
			String d = parts[2].toLowerCase();
			cmds.add(new Cmd(e, n, d));
			i++;
		}
		// read target cube
		int target = -1;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0) continue;
			target = Integer.parseInt(line);
			break;
		}

		// sort commands by existing then new
		Collections.sort(cmds, (a,b) -> {
			if (a.exist != b.exist) return Integer.compare(a.exist, b.exist);
			return Integer.compare(a.neu, b.neu);
		});

		Map<Integer, P> posById = new HashMap<>();
		Map<P, Integer> idByPos = new HashMap<>();

		for (Cmd c : cmds) {
			int e = c.exist, v = c.neu;
			String d = c.dir;
			// ensure existing cube is placed
			if (!posById.containsKey(e)) {
				// place at origin
				P origin = new P(0,0);
				// if origin occupied, find a free place? But per problem connectedness and sorting suffice.
				if (idByPos.containsKey(origin)) {
					// If occupied by some id, leave it; but prefer not to overwrite existing origin - instead place e next to it naively.
					// We'll place e at origin if that id equals e, otherwise place e at origin and remove existing (replacement rule).
					int prev = idByPos.get(origin);
					if (prev != e) {
						posById.remove(prev);
					}
				}
				posById.put(e, origin);
				idByPos.put(origin, e);
			}

			P pe = posById.get(e);
			int nx = pe.x, ny = pe.y;
			if (d.equals("top") || d.equals("up")) ny += 1;
			else if (d.equals("down") || d.equals("bottom")) ny -= 1;
			else if (d.equals("left")) nx -= 1;
			else if (d.equals("right")) nx += 1;
			else {
				// unknown direction - skip
			}

			P np = new P(nx, ny);

			// if some cube occupies new position, it is replaced
			if (idByPos.containsKey(np)) {
				int old = idByPos.get(np);
				posById.remove(old);
			}

			// if v was previously placed elsewhere, remove old occupancy
			if (posById.containsKey(v)) {
				P oldp = posById.get(v);
				idByPos.remove(oldp);
			}

			// place v
			posById.put(v, np);
			idByPos.put(np, v);
		}

		// now answer for target
		StringBuilder out = new StringBuilder();
		if (!posById.containsKey(target)) {
			out.append("-1 -1 -1 -1");
			System.out.println(out.toString());
			return;
		}
		P tpos = posById.get(target);
		P up = new P(tpos.x, tpos.y + 1);
		P down = new P(tpos.x, tpos.y - 1);
		P left = new P(tpos.x - 1, tpos.y);
		P right = new P(tpos.x + 1, tpos.y);

		out.append(idByPos.getOrDefault(up, -1)).append(' ')
		   .append(idByPos.getOrDefault(down, -1)).append(' ')
		   .append(idByPos.getOrDefault(left, -1)).append(' ')
		   .append(idByPos.getOrDefault(right, -1));

		System.out.println(out.toString());
	}
}
