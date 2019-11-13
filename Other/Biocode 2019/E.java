//copied directly from https://codeforces.com/contest/505/submission/9830006

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;
 
public class E {
 
  public static void main(String[] args) throws IOException {
    IOScanner scanner;
    if (new File("data.in").exists()) {
      scanner = new IOScanner("data.in", null);
    } else {
      scanner = new IOScanner();
    }
    while (scanner.hasNext()) {
      new Solver(scanner).solve();
    }
    scanner.close();
  }
}
 
class IOScanner {
  private final BufferedReader reader;
  private final PrintWriter writer;
  private StringTokenizer stringTokenizer;
 
  public IOScanner() {
    this(System.in, System.out);
  }
 
  public IOScanner(String input, String output) throws FileNotFoundException {
    this(input == null ? null : new File(input), output == null ? null : new File(output));
  }
 
  public IOScanner(File input, File output) throws FileNotFoundException {
    this(input == null ? System.in : new FileInputStream(input), output == null ? System.out
        : new FileOutputStream(output));
  }
 
  public IOScanner(InputStream inputStream, OutputStream outputStream) {
    reader = new BufferedReader(new InputStreamReader(inputStream));
    writer = new PrintWriter(outputStream);
  }
 
  private StringTokenizer read() {
    while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
      try {
        stringTokenizer = new StringTokenizer(reader.readLine());
      } catch (Exception e) {
        return null;
      }
    }
    return stringTokenizer;
  }
 
  public boolean hasNext() {
    StringTokenizer tokenizer = read();
    return tokenizer != null && tokenizer.hasMoreTokens();
  }
 
  public int nextInt() {
    return Integer.parseInt(next());
  }
 
  public long nextLong() {
    return Long.parseLong(next());
  }
 
  public double nextDouble() {
    return Double.parseDouble(next());
  }
 
  public int[] nextInts(int n) {
    int[] input = new int[n];
    for (int i = 0; i < n; i++) {
      input[i] = nextInt();
    }
    return input;
  }
 
  public int[][] nextInts(int n, int m) {
    int[][] input = new int[n][];
    for (int i = 0; i < n; i++) {
      input[i] = nextInts(m);
    }
    return input;
  }
 
  public double[] nextDoubles(int n) {
    double[] input = new double[n];
    for (int i = 0; i < n; i++) {
      input[i] = nextDouble();
    }
    return input;
  }
 
  public String[] nexts(int n) {
    String[] input = new String[n];
    for (int i = 0; i < n; i++) {
      input[i] = next();
    }
    return input;
  }
 
  public char[][] nextCharArrays(int n) {
    String[] input = nexts(n);
    char[][] result = new char[n][];
    for (int i = 0; i < n; i++) {
      result[i] = input[i].toCharArray();
    }
    return result;
  }
 
  public String next() {
    return read().nextToken();
  }
 
  public void println() {
    writer.println();
  }
 
  public void println(Iterable<?> iterable) {
    println(iterable.iterator());
  }
 
  public void println(Iterator<?> iterator) {
    boolean first = true;
    while (iterator.hasNext()) {
      if (first) {
        first = false;
      } else {
        print(' ');
      }
      print(iterator.next());
    }
    println();
  }
 
  public void println(Object token) {
    writer.println(token);
  }
 
  public void print(Object token) {
    writer.print(token);
  }
 
  public void printf(String format, Object... args) {
    writer.printf(format, args);
  }
 
  public void flush() {
    writer.flush();
  }
 
  public void close() throws IOException {
    reader.close();
    writer.close();
  }
}
 
class Edge {
  final int from, to;
  final Edge next;
 
  Edge(int from, int to, Edge next) {
    this.from = from;
    this.to = to;
    this.next = next;
  }
}
 
class Solver {
 
  private final IOScanner scanner;
 
  private Edge[] head;
  private int[] visited, stack, low, id;
  private int top, curT, curD, res;
 
  private int[] p;
 
  public Solver(IOScanner scanner) {
    this.scanner = scanner;
  }
 
  public void solve() {
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    head = new Edge[n];
    Arrays.fill(head, null);
    int[][] edges = scanner.nextInts(m, 2);
 
    for (int i = 0; i < m; i++) {
      edges[i][0]--;
      edges[i][1]--;
      addEdge(edges[i][0], edges[i][1]);
    }
 
    visited = new int[n];
    stack = new int[n];
    low = new int[n];
    id = new int[n];
    curT = top = curD = 0;
    Arrays.fill(visited, 0);
    for (int i = 0; i < n; i++) {
      if (visited[i] == 0) {
        dfs(i);
      }
    }
    p = new int[curD];
    for (int i = 0; i < curD; i++) {
      p[i] = i;
    }
    int[] count = new int[curD];
    boolean[] has = new boolean[curD];
    Arrays.fill(has, false);
    for (int i = 0; i < m; i++) {
      if (id[edges[i][0]] != id[edges[i][1]]) {
        p[find(id[edges[i][0]])] = find(id[edges[i][1]]);
      }
    }
    for (int i = 0; i < n; i++) {
      count[find(id[i])]++;
    }
    for (int i = 0; i < m; i++) {
      if (id[edges[i][0]] == id[edges[i][1]]) {
        has[find(id[edges[i][0]])] = true;
      }
    }
    res = n;
    for (int i = 0; i < curD; i++) {
      if (count[i] == 1 || count[i] > 1 && !has[i]) {
        res--;
      }
    }
    scanner.println(res);
  }
 
  private int find(int x) {
    return x == p[x] ? p[x] : (p[x] = find(p[x]));
  }
 
  private void dfs(int u) {
    int uid = ++curT;
    low[u] = curT;
    stack[top++] = u;
    visited[u] = 1;
    for (Edge e = head[u]; e != null; e = e.next) {
      int v = e.to;
      if (visited[v] == 0) {
        dfs(v);
        low[u] = Math.min(low[u], low[v]);
      } else if (visited[v] != -1) {
        low[u] = Math.min(low[u], low[v]);
      }
    }
    if (low[u] == uid) {
      int v, count = 0;
      do {
        v = stack[--top];
        visited[v] = -1;
        id[v] = curD;
        count++;
      } while (v != u);
      curD++;
      if (count > 1) {
        res += count;
      }
    }
  }
 
  private void addEdge(int a, int b) {
    Edge edge = new Edge(a, b, head[a]);
    head[a] = edge;
  }
}