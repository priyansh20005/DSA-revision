import java.io.*;
import java.util.*;

public class maxWorth {

	// Patterns for characters represented as 3x3 blocks (each string is concatenation of 3 rows of length 3)
	static final LinkedHashMap<String, Character> PATTERN = new LinkedHashMap<>();

	static {
		// digits 0-9 (rows concatenated: row0 + row1 + row2)
		PATTERN.put(" _ | ||_|", '0'); // " _ ","| |","|_|"
		PATTERN.put("   |  |", '1');   // "   ","  |","  |"
		PATTERN.put(" _  _||_ ", '2'); // " _ "," _|","|_ "
		PATTERN.put(" _  _| _|", '3'); // " _ "," _|"," _|"
		PATTERN.put("   |_|  |", '4'); // "   ","|_|","  |"
		PATTERN.put(" _ |_  _|", '5'); // " _ ","|_ "," _|"
		PATTERN.put(" _ |_ |_|", '6'); // " _ ","|_ ","|_|"
		PATTERN.put(" _   |  |", '7'); // " _ ","  |","  |"
		PATTERN.put(" _ |_||_|", '8'); // " _ ","|_|","|_|"
		PATTERN.put(" _ |_| _|", '9'); // " _ ","|_|"," _|"

		// operators and brackets (common 3x3 approximations)
		PATTERN.put("   _ |_|", '+'); // "   "," _ ","|_|"
		PATTERN.put("   _   ", '-'); // "   "," _ ","   "
		PATTERN.put("  \n \n ", '?'); // placeholder - not used, kept for alignment
		// We'll also insert '*' and '/' and brackets with the best-effort patterns
		PATTERN.put("  _  _ ", '/'); // approximate
		PATTERN.put("   _|_  ", '*'); // approximate
		PATTERN.put("|  |_  _", '['); // approximate
		PATTERN.put("  _  _| ", ']'); // approximate
	}

	// Convert 3 strings (each length 3) into canonical concatenated form
	static String join3(String a, String b, String c) {
		return a + b + c;
	}

	// Convert a 3x3 block into a 9-bit mask (bit i set if char != ' ')
	static int maskOf(String s) {
		int m = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') m |= (1 << i);
		}
		return m;
	}

	static Map<Character, Integer> charMask = new HashMap<>();
	static Map<Integer, Character> maskChar = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null) return;
		int N = Integer.parseInt(line.trim());

		String row0 = br.readLine();
		String row1 = br.readLine();
		String row2 = br.readLine();
		String prec = br.readLine();
		if (prec == null) prec = "+-*/";

		// normalize rows to length N*3 by padding with spaces if necessary
		int needed = N * 3;
		row0 = padRight(row0, needed);
		row1 = padRight(row1, needed);
		row2 = padRight(row2, needed);

		// initialize masks from PATTERN
		for (Map.Entry<String, Character> e : PATTERN.entrySet()) {
			String key = e.getKey();
			// ensure key length 9
			String k = padRight(key, 9);
			int m = maskOf(k);
			charMask.put(e.getValue(), m);
			maskChar.put(m, e.getValue());
		}

		// For safety, ensure digits masks exist for all 0-9 (they do above)

		// Extract N blocks
		char[] origChars = new char[N];
		int[] origMasks = new int[N];
		for (int i = 0; i < N; i++) {
			String a = row0.substring(i * 3, i * 3 + 3);
			String b = row1.substring(i * 3, i * 3 + 3);
			String c = row2.substring(i * 3, i * 3 + 3);
			String joined = join3(a, b, c);
			int m = maskOf(joined);
			origMasks[i] = m;
			Character ch = maskChar.get(m);
			if (ch == null) {
				// if no exact match, leave placeholder '?'
				origChars[i] = '?';
			} else origChars[i] = ch;
		}

		// Build list of possible replacements by one-bit toggle for each position
		Map<Integer, List<Character>> toggles = new HashMap<>();
		// Collect all known masks
		List<Integer> knownMasks = new ArrayList<>(maskChar.keySet());

		for (int i = 0; i < N; i++) {
			int m0 = origMasks[i];
			List<Character> list = new ArrayList<>();
			// include original char if recognized
			if (maskChar.containsKey(m0)) list.add(maskChar.get(m0));
			// try toggling each of 9 bits
			for (int bit = 0; bit < 9; bit++) {
				int m = m0 ^ (1 << bit);
				if (maskChar.containsKey(m)) {
					Character c = maskChar.get(m);
					if (!list.contains(c)) list.add(c);
				}
			}
			toggles.put(i, list);
		}

		// Create set of candidate expressions (as char arrays)
		Set<String> candidateExprs = new HashSet<>();
		// include original if fully recognized
		String orig = new String(origChars);
		if (!orig.contains("?")) candidateExprs.add(orig);

		// for each position, for each alternative (other than original char), create new string
		for (int i = 0; i < N; i++) {
			List<Character> list = toggles.get(i);
			for (Character c : list) {
				if (maskChar.get(origMasks[i]) != null && maskChar.get(origMasks[i]).equals(c)) continue; // same
				char[] arr = new char[N];
				for (int k = 0; k < N; k++) arr[k] = maskChar.getOrDefault(origMasks[k], '?');
				arr[i] = c;
				String s = new String(arr);
				if (!s.contains("?")) candidateExprs.add(s);
			}
		}

		long sum = 0L;
		for (String exprChars : candidateExprs) {
			try {
				List<String> tokens = tokenize(exprChars);
				if (!isValidTokenSequence(tokens)) continue;
				long val = evalTokens(tokens, prec);
				sum += val;
			} catch (ArithmeticException ae) {
				// division by zero or other arithmetic error -> skip
			} catch (Exception ex) {
				// invalid expression -> skip
			}
		}

		System.out.println(sum);
	}

	static String padRight(String s, int n) {
		if (s == null) s = "";
		if (s.length() >= n) return s.substring(0, n);
		StringBuilder sb = new StringBuilder(s);
		while (sb.length() < n) sb.append(' ');
		return sb.toString();
	}

	// Tokenize char sequence (each char is one grid char mapped to a symbol or digit)
	static List<String> tokenize(String s) {
		List<String> t = new ArrayList<>();
		int i = 0;
		int n = s.length();
		while (i < n) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				int j = i;
				while (j < n && Character.isDigit(s.charAt(j))) j++;
				t.add(s.substring(i, j));
				i = j;
			} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '[' || ch == ']') {
				t.add(String.valueOf(ch));
				i++;
			} else {
				// unknown symbol
				throw new IllegalArgumentException("Unknown token char: " + ch);
			}
		}
		return t;
	}

	static boolean isValidTokenSequence(List<String> tokens) {
		if (tokens.isEmpty()) return false;
		// basic validation: parentheses match and tokens alternate appropriately
		int balance = 0;
		boolean expectOperand = true;
		for (String tok : tokens) {
			if (tok.equals("[")) {
				balance++; // after '[' we still expect an operand
			} else if (tok.equals("]")) {
				balance--;
				if (balance < 0) return false;
			}

			if (tok.equals("[") || tok.equals("]")) {
				// brackets can appear in operand positions too, but to be simple we'll allow
				// continue
				// do nothing
			} else if (isOperator(tok)) {
				if (expectOperand) return false;
				expectOperand = true;
			} else { // number
				if (!expectOperand) return false;
				expectOperand = false;
			}
		}
		if (balance != 0) return false;
		if (expectOperand) return false; // expression ended expecting an operand
		return true;
	}

	static boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}

	static long evalTokens(List<String> tokens, String precOrder) {
		// Shunting-yard to postfix using given precedence order
		Map<String, Integer> prec = new HashMap<>();
		// higher number -> higher precedence
		for (int i = 0; i < precOrder.length(); i++) {
			prec.put(String.valueOf(precOrder.charAt(i)), precOrder.length() - i);
		}

		List<String> out = new ArrayList<>();
		Deque<String> ops = new ArrayDeque<>();

		for (String tok : tokens) {
			if (tok.equals("[(")) {
				// not used
			}
			if (tok.equals("[")) {
				ops.push(tok);
			} else if (tok.equals("]")) {
				while (!ops.isEmpty() && !ops.peek().equals("[")) out.add(ops.pop());
				if (ops.isEmpty() || !ops.peek().equals("[")) throw new IllegalArgumentException("Mismatched brackets");
				ops.pop();
			} else if (isOperator(tok)) {
				while (!ops.isEmpty() && isOperator(ops.peek())) {
					int p1 = prec.getOrDefault(ops.peek(), 0);
					int p2 = prec.getOrDefault(tok, 0);
					if (p1 > p2 || (p1 == p2)) { // left-associative
						out.add(ops.pop());
					} else break;
				}
				ops.push(tok);
			} else { // number
				out.add(tok);
			}
		}

		while (!ops.isEmpty()) {
			String o = ops.pop();
			if (o.equals("[") || o.equals("]")) throw new IllegalArgumentException("Mismatched brackets");
			out.add(o);
		}

		// evaluate postfix
		Deque<Long> st = new ArrayDeque<>();
		for (String t : out) {
			if (isOperator(t)) {
				if (st.size() < 2) throw new IllegalArgumentException("Invalid expression");
				long b = st.pop();
				long a = st.pop();
				long r;
				switch (t) {
					case "+": r = a + b; break;
					case "-": r = a - b; break;
					case "*": r = a * b; break;
					case "/":
						if (b == 0) throw new ArithmeticException("div0");
						r = a / b; break;
					default: throw new IllegalArgumentException("Unknown op");
				}
				st.push(r);
			} else {
				st.push(Long.parseLong(t));
			}
		}
		if (st.size() != 1) throw new IllegalArgumentException("Invalid final stack");
		return st.pop();
	}
}
