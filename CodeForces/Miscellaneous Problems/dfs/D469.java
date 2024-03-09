//Two Sets
import java.io.*;
import java.util.*;
//java'd
//previous bug: didn't output values in right order (inputted numbers are not necessarily in order)
public class D469{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] color;
   
   public static int a2i,b2i;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int a = fs.nextInt();
      int b = fs.nextInt();
      //StringTokenizer st = new StringTokenizer(f.readLine());
      
      //int n = Integer.parseInt(st.nextToken());
      //int a = Integer.parseInt(st.nextToken());
      //int b = Integer.parseInt(st.nextToken());
      
      int[] array = fs.nextInts(n);       //edited to read in 1-indexed
      //st = new StringTokenizer(f.readLine());
      //int[] array = new int[n+1];
      Integer[] sorted = new Integer[n+1];
      sorted[0] = -1;
      for(int k = 1; k <= n; k++){
         //array[k] = Integer.parseInt(st.nextToken());
         sorted[k] = array[k];
      }
      
      Arrays.sort(sorted);
      
      HashMap<Integer,Integer> indexof = new HashMap<Integer,Integer>();
      for(int k = 1; k <= n; k++){
         indexof.put(sorted[k],k);
      }
      
      if(a == b){
         boolean fail = false;
         for(int i : indexof.keySet()){
            if(!indexof.containsKey(a-i)){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
            StringJoiner sj = new StringJoiner(" ");
            for(int k = 0; k < n; k++){
               sj.add("0");
            }
            out.println(sj.toString());
         }
         out.close();
         return;
      }
      
      a2i = -1;
      b2i = -1;
      
      if(a%2 == 0 && indexof.containsKey(a/2)) a2i = indexof.get(a/2);
      if(b%2 == 0 && indexof.containsKey(b/2)) b2i = indexof.get(b/2);
      
      for(int ia = 0; ia <= 1; ia++){
         for(int ib = 0; ib <= 1; ib++){
            if((n-ia-ib) % 2 != 0) continue;
            if(ia == 1 && !(a%2 == 0 && indexof.containsKey(a/2))) continue;
            if(ib == 1 && !(b%2 == 0 && indexof.containsKey(b/2))) continue; 
            
            int need = (n-ia-ib)/2;
            
            MaxFlow flow = genflow(n,indexof,a,b,ia==1,ib==1);
            
            long ret = flow.mfmc();
            //out.println(ia + " " + ib + " " + need + " " + ret);
            if(ret == need){
               int[] answer = new int[n+1];
               
               for(int k = 1; k <= n; k++){
                  if(ia == 1 && k == a2i) continue;
                  if(ib == 1 && k == b2i) continue;
                     
                  for(MaxFlow.Edge e : flow.edges[k]){
                     if(e.isres || e.flow == 0L || e.to > n) continue;
                     if(sorted[k] + sorted[e.to] == a){
                        answer[k] = 0;
                        answer[e.to] = 0;
                     } else {
                        answer[k] = 1;
                        answer[e.to] = 1;
                     }
                  
                  }
               }
               
               if(ia == 1) answer[a2i] = 0;
               if(ib == 1) answer[b2i] = 1;
               
               out.println("YES");
               StringJoiner sj = new StringJoiner(" ");
               for(int k = 1; k <= n; k++){
                  sj.add("" + answer[indexof.get(array[k])]);
               }
               out.println(sj.toString());
               out.close();
               return;
            }
         }
      }
      
      out.println("NO");
      
      out.close();
   }
   
   public static MaxFlow genflow(int n, HashMap<Integer,Integer> indexof, int a, int b, boolean a2, boolean b2){
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      
      for(int x : indexof.keySet()){
         int i = indexof.get(x);
         int othera = a-x;
         if(othera > x && indexof.containsKey(othera)){
            int other = indexof.get(othera);
            adj.get(i).add(other);
            adj.get(other).add(i);
         }
         
         int otherb = b-x;
         if(otherb > x && indexof.containsKey(otherb)){
            int other = indexof.get(otherb);
            adj.get(i).add(other);
            adj.get(other).add(i);
         }
      }
      
      //guaranteed to be bipartite
      //no odd cycles, since one edge has to be from a, and another edge from b
      //odd cycle would mean that you have 2 edges from a or b - contradiction
      color = new int[n+3];
      
      for(int k = 1; k <= n; k++){
         if(color[k] == 0){
            color[k] = 1;
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(k);
            while(!q.isEmpty()){
               int v = q.poll();
               
               if(a2 && v == a2i) continue;
               if(b2 && v == b2i) continue;
               
               for(int nei : adj.get(v)){
                  if(color[nei] == 0){
                     color[nei] = 3-color[v];
                     q.add(nei);
                  }
               }
            }
         }
      }
      
      int source = n+1;
      int sink = n+2;
      MaxFlow flow = new MaxFlow(n+2,source,sink);
      
      for(int k = 1; k <= n; k++){
         if(a2 && k == a2i) continue;
         if(b2 && k == b2i) continue;
      
         if(color[k] == 1){
            flow.addEdge(source,k,1L);
            for(int nei : adj.get(k)){
               flow.addEdge(k,nei,1L);
            }
            
         } else {
            flow.addEdge(k,sink,1L);
         }
      }
      
      return flow;
   }
   
   public static void getcolor(int v){
      
      for(int nei : adj.get(v)){
         if(color[nei] == 0){
            color[nei] = 3-color[v];
            getcolor(nei);
         }
      }
      
   }
      
}

//from ray
class MaxFlow
{
    //Dinic with optimizations (see magic array in dfs function)
    public int N, source, sink;
    public ArrayList<Edge>[] edges;
    private int[] depth;
 
    public MaxFlow(int n, int x, int y)
    {
        N = n;
        source = x;
        sink = y;
        edges = new ArrayList[N+1];
        for(int i=0; i <= N; i++)
            edges[i] = new ArrayList<Edge>();
        depth = new int[N+1];
    }
    public void addEdge(int from, int to, long cap)
    {
        Edge forward = new Edge(from, to, cap, false);
        Edge backward = new Edge(to, from, 0L, true);
        forward.residual = backward;
        backward.residual = forward;
        edges[from].add(forward);
        edges[to].add(backward);
    }
    public long mfmc()
    {
        long res = 0L;
        int[] magic = new int[N+1];
        while(assignDepths())
        {
            long flow = dfs(source, Long.MAX_VALUE/2, magic);
            while(flow > 0)
            {
                res += flow;
                flow = dfs(source, Long.MAX_VALUE/2, magic);
            }
            magic = new int[N+1];
        }
        return res;
    }
    private boolean assignDepths()
    {
        Arrays.fill(depth, -69);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(source);
        depth[source] = 0;
        while(q.size() > 0)
        {
            int curr = q.poll();
            for(Edge e: edges[curr])
                if(e.capacityLeft() > 0 && depth[e.to] == -69)
                {
                    depth[e.to] = depth[curr]+1;
                    q.add(e.to);
                }
        }
        return depth[sink] != -69;
    }
    private long dfs(int curr, long bottleneck, int[] magic)
    {
        if(curr == sink)
            return bottleneck;
        for(; magic[curr] < edges[curr].size(); magic[curr]++)
        {
            Edge e = edges[curr].get(magic[curr]);
            if(e.capacityLeft() > 0 && depth[e.to]-depth[curr] == 1)
            {
                long val = dfs(e.to, Math.min(bottleneck, e.capacityLeft()), magic);
                if(val > 0)
                {
                    e.augment(val);
                    return val;
                }
            }
        }
        return 0L;  //no flow
    }
    public class Edge
    {
        public int from, to;
        public long flow, capacity;
        public Edge residual;
        public boolean isres;
 
        public Edge(int f, int t, long cap, boolean isres_)
        {
            from = f;
            to = t;
            capacity = cap;
            isres = isres_;
        }
        public long capacityLeft()
        {
            return capacity-flow;
        }
        public void augment(long val)
        {
            flow += val;
            residual.flow -= val;
        }
    }
}


class FastScanner
{
    //I don't understand how this works lmao
    private int BS = 1 << 16;
    private char NC = (char) 0;
    private byte[] buf = new byte[BS];
    private int bId = 0, size = 0;
    private char c = NC;
    private double cnt = 1;
    private BufferedInputStream in;
 
    public FastScanner() {
        in = new BufferedInputStream(System.in, BS);
    }
 
    public FastScanner(String s) {
        try {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        } catch (Exception e) {
            in = new BufferedInputStream(System.in, BS);
        }
    }
 
    private char getChar() {
        while (bId == size) {
            try {
                size = in.read(buf);
            } catch (Exception e) {
                return NC;
            }
            if (size == -1) return NC;
            bId = 0;
        }
        return (char) buf[bId++];
    }
 
    public int nextInt() {
        return (int) nextLong();
    }
 
    public int[] nextInts(int N) {
        int[] res = new int[N+1];
        for (int i = 1; i <= N; i++) {
            res[i] = (int) nextLong();
        }
        return res;
    }
 
    public long[] nextLongs(int N) {
        long[] res = new long[N+1];
        for (int i = 1; i <= N; i++) {
            res[i] = nextLong();
        }
        return res;
    }
 
    public long nextLong() {
        cnt = 1;
        boolean neg = false;
        if (c == NC) c = getChar();
        for (; (c < '0' || c > '9'); c = getChar()) {
            if (c == '-') neg = true;
        }
        long res = 0;
        for (; c >= '0' && c <= '9'; c = getChar()) {
            res = (res << 3) + (res << 1) + c - '0';
            cnt *= 10;
        }
        return neg ? -res : res;
    }
 
    public double nextDouble() {
        double cur = nextLong();
        return c != '.' ? cur : cur + nextLong() / cnt;
    }
 
    public double[] nextDoubles(int N) {
        double[] res = new double[N];
        for (int i = 0; i < N; i++) {
            res[i] = nextDouble();
        }
        return res;
    }
 
    public String next() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c > 32) {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }
 
    public String nextLine() {
        StringBuilder res = new StringBuilder();
        while (c <= 32) c = getChar();
        while (c != '\n') {
            res.append(c);
            c = getChar();
        }
        return res.toString();
    }
 
    public boolean hasNext() {
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}