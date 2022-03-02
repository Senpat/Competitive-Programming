//make sure to make new file!
import java.io.*;
import java.util.*;
//post contest test, uses fast scanner
public class E102b{
   
   public static void main(String[] args)throws IOException{
      FastScanner f = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = f.nextInt();
      int m = f.nextInt();
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         int a = f.nextInt();
         int b = f.nextInt();
         long w = f.nextLong();
      
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
      }
      
      long[][][] dists = new long[n+1][2][2];
      for(int k = 0; k <= n; k++){
         for(int j = 0; j < 2; j++){
            for(int h = 0; h < 2; h++){
               dists[k][j][h] = Long.MAX_VALUE;
            }
         }
      }
      
      dists[1][0][0] = 0;
      PriorityQueue<State> pq = new PriorityQueue<State>();
      pq.add(new State(1,0,0,0));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dists[s.v][s.maxnadded][s.minadded] != s.d) continue;
         
         for(Edge e : adj.get(s.v)){
            //neither
            long newd1 = dists[s.v][s.maxnadded][s.minadded]+e.w;
            if(newd1 < dists[e.to][s.maxnadded][s.minadded]){
               dists[e.to][s.maxnadded][s.minadded] = newd1;
               pq.add(new State(e.to,s.maxnadded,s.minadded,newd1));
            }
            //don't add (max)
            if(s.maxnadded == 0){
               long newd2 = dists[s.v][s.maxnadded][s.minadded];
               if(newd2 < dists[e.to][1][s.minadded]){
                  dists[e.to][1][s.minadded] = newd2;
                  pq.add(new State(e.to,1,s.minadded,newd2));
               }
            }
            //add (min)
            if(s.minadded == 0){
               long newd3 = dists[s.v][s.maxnadded][s.minadded] + 2*e.w;
               if(newd3 < dists[e.to][s.maxnadded][1]){
                  dists[e.to][s.maxnadded][1] = newd3;
                  pq.add(new State(e.to,s.maxnadded,1,newd3));
               }
            }
            //both (add and subtract, same as adding it once)
            if(s.maxnadded == 0 && s.minadded == 0){
               long newd4 = dists[s.v][s.maxnadded][s.minadded] + e.w;
               if(newd4 < dists[e.to][1][1]){
                  dists[e.to][1][1] = newd4;
                  pq.add(new State(e.to,1,1,newd4));
               }
            }
         }
         
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 2; k <= n; k++){
         sj.add("" + dists[k][1][1]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      int maxnadded;             //max not added
      int minadded;              //min added
      long d;
      public State(int a, int b, int c, long dd){
         v = a;
         maxnadded = b;
         minadded = c;
         d = dd;
      }
      public int compareTo(State s){
         if(d > s.d) return 1;
         if(d < s.d) return -1;
         return 0;
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
   
   public static class FastScanner {
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
    
        char getChar() {
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
    
        int nextInt() {
            return (int) nextLong();
        }
    
        long nextLong() {
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
    
        double nextDouble() {
            boolean neg = false;
            if (c == NC) c = getChar();
            for (; (c < '0' || c > '9'); c = getChar()) {
                if (c == '-') neg = true;
            }
            double cur = nextLong();
            if (c != '.') {
                return neg ? -cur : cur;
            } else {
                double frac = nextLong() / cnt;
                return neg ? -cur - frac : cur + frac;
            }
        }
    
        String next() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c > 32) {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }
    
        String nextLine() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c != '\n') {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }
    
        boolean hasNext() {
            if (c > 32) return true;
            while (true) {
                c = getChar();
                if (c == NC) return false;
                else if (c > 32) return true;
            }
        }
    }
}