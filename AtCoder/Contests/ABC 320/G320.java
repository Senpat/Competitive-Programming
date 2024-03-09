//make sure to make new file!
import java.io.*;
import java.util.*;
//editorial
public class G320{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      int[][][] occur = new int[10][n][n];         //gets the first n occurrances for each reel and each digit
      for(int k = 0; k < 10; k++){
         for(int j = 0; j < n; j++){
            Arrays.fill(occur[k][j],-1);
         }
      }
      
      for(int k = 0; k < n; k++){
         int[] i = new int[10];
         for(int j = 0; j < m; j++){
            int num = array[k].charAt(j)-'0';
            if(i[num] < n){
               occur[num][k][i[num]] = j;
               i[num]++;
            }
         }
         
         for(int num = 0; num < 10; num++){
            if(i[num] == 0) continue;
            int size = i[num];
            for(int h = i[num]; h < n; h++){
               occur[num][k][h] = occur[num][k][h-size] + m;
            }
         }
      }
      
      
      int minanswer = Integer.MAX_VALUE;
      
      int[] indexof = new int[10000001];
      
      for(int num = 0; num < 10; num++){
         //compress occurrances
         HashSet<Integer> hset = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               hset.add(occur[num][k][j]);
            }
         }
         
         //num doesn't appear in one of the reels
         if(hset.contains(-1)) continue;
         
         ArrayList<Integer> occurrances = new ArrayList<Integer>(hset);
         Collections.sort(occurrances);
         int o = occurrances.size();
         //make 1-indexed
         occurrances.add(0,-1);
         for(int k = 1; k <= o; k++){
            indexof[occurrances.get(k)] = k;
         }
      
         //binary search to find min time to make all reels equal to num
         
         int l = 1;
         int r = o;
         int ans = Integer.MAX_VALUE;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            //make flow graph with times <= mid
            //[1-mid] -> times, [mid+1,mid+n] -> reels, mid+n+1 source, mid+n+2 sink
            int source = mid+n+1;
            int sink = mid+n+2;
            MaxFlow flow = new MaxFlow(mid+n+2,source,sink);
            
            for(int reel = 0; reel < n; reel++){
               int v = mid+1+reel;
               flow.addEdge(v,sink,1L);
               
               for(int oi = 0; oi < n; oi++){
                  int tv = indexof[occur[num][reel][oi]];
                  if(tv > mid) break;
                  
                  flow.addEdge(tv,v,1L);
               }
            }
            
            for(int k = 1; k <= mid; k++){
               flow.addEdge(source,k,1L);
            }
            
            long ret = flow.mfmc();
            
            if(ret == n){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
            
         }
         
         if(ans != Integer.MAX_VALUE){
            minanswer = Math.min(minanswer,occurrances.get(ans));
         }
         
      }
      
      if(minanswer == Integer.MAX_VALUE){
         out.println(-1);
      } else {
         out.println(minanswer);
      }
      
      
      
      
      
      
      
      
      out.close();
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
        Edge forward = new Edge(from, to, cap);
        Edge backward = new Edge(to, from, 0L);
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
    private class Edge
    {
        public int from, to;
        public long flow, capacity;
        public Edge residual;
 
        public Edge(int f, int t, long cap)
        {
            from = f;
            to = t;
            capacity = cap;
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
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
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