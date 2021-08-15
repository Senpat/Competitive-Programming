//make sure to make new file!
import java.io.*;
import java.util.*;

public class L{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      FastScanner f = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      //StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = f.nextInt();
      int m = f.nextInt();
      int d = f.nextInt();
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         //st = new StringTokenizer(f.readLine());
      
         int a = f.nextInt();
         int b = f.nextInt();
         long c = f.nextLong();
         
         adj.get(a).add(new Edge(b,c));
         adj.get(b).add(new Edge(a,c));
      }
      
      long[] array = f.nextLongs(d);
      shuffleArray(array);
      Arrays.sort(array);
      
      //parent[k] = -1 means no solution, parent[k] = -2 means multiple ways to get there
      int[] parent = new int[n+1];
      long[] dist = new long[n+1];
      boolean[] hasstate = new boolean[n+1];          //has state for that node at dist[node]
      Arrays.fill(dist,Long.MAX_VALUE);
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      int startindex = (array[0] == 0) ? 1 : 0;
      pq.add(new State(1,0L,startindex));
      dist[1] = 0L;
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dist[s.v] < s.time) 
            continue;
         
         for(Edge e : adj.get(s.v)){
            long newd = s.time + e.w;
            
            if(dist[e.to] < newd) 
               continue;
            
            
            int newindex = s.index;
            if(s.index < d){
               //this means that you haven't reached array[s.index]
               if(newd > array[s.index]){
                  if(parent[e.to] == 0){
                     parent[e.to] = -1;
                  }
                  //if not, there are other (legal) ways to get to e.to so don't change anything
                  newindex = findindex(newd,array);
                  //if(dist[e.to] > newd){
                     dist[e.to] = newd;
                     pq.add(new State(e.to,newd,newindex));
                  //}
                  continue;
               } else if(newd == array[s.index]){
                  newindex++;
               }
            }
                  
            if(dist[e.to] == newd){
               if(parent[s.v] == -1 && parent[e.to] == 0) parent[e.to] = -1;
               else if(parent[e.to] == -1 || parent[e.to] == 0) parent[e.to] = s.v;
               else parent[e.to] = -2;
               //pq.add(new State(e.to,newd,newindex));
               continue;
            }
            
            //dist[e.to] is > newd, new distance
            dist[e.to] = newd;
            if(parent[s.v] == -1) parent[e.to] = -1;
            else parent[e.to] = s.v;
            
            pq.add(new State(e.to,newd,newindex));
         }
      }
      
      if(dist[n] < array[d-1]){
         out.println(0);
         out.close();
         return;
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      int i = n;
      boolean mul = false;
      boolean fail = false;
      while(i != 1){
         int p = parent[i];
         if(p == -2){
            mul = true;
            break;
         }
         
         if(p == -1 || p == 0){
            fail = true;
            break;
         }
         answer.add(i);  
         i = p;
      }
      
      if(mul){
         out.println(1);
      } else if(fail){
         out.println(0);
      } else {
         answer.add(1);
         StringJoiner sj = new StringJoiner("\n");
         sj.add("" + answer.size());
         for(int k = answer.size()-1; k >= 0; k--){
            sj.add("" + answer.get(k));
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static int findindex(long x, long[] array){
      int l = 0;
      int r = array.length-1;
      int ans = array.length;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         if(x < array[mid]){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      return ans;
   }
   
   public static class State implements Comparable<State>{
      int v;
      long time;
      int index;                 //the last time that you hit
      
      public State(int a, long b, int c){
         v = a;
         time = b;
         index = c;
      }
      
      public int compareTo(State s){
         if(time == s.time) 
            return 0;
         else if(time < s.time) 
            return -1;
         return 1;
      }
   }
   
   public static class Edge{
      int to;
      long w;
      
      public Edge(int a, long b){
         to = a;
         w = b;
      }
   }
   
   public static void shuffleArray(long[] arr){
      int n = arr.length;
      Random rnd = new Random();
      for(int i=0; i<n; ++i){
         long tmp = arr[i];
         int randomPos = i + rnd.nextInt(n-i);
         arr[i] = arr[randomPos];
         arr[randomPos] = tmp;
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
         if (size == -1) 
            return NC;
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
      long[] res = new long[N];
      for (int i = 0; i < N; i++) {
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
      if (c > 32) 
         return true;
      while (true) {
         c = getChar();
         if (c == NC) 
            return false;
         else if (c > 32) 
            return true;
      }
   }
}

