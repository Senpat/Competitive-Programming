//make sure to make new file!
import java.io.*;
import java.util.*;

public class E766{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
      
         int n = fs.nextInt();
         int m = fs.nextInt();
         int l = fs.nextInt();
         
         long[] x = fs.nextLongs(n);               //1-indexed
         
         Ladder[] ladders = new Ladder[l];
         ArrayList<ArrayList<Integer>> ladderstart = new ArrayList<ArrayList<Integer>>(n+1);
         ArrayList<ArrayList<Integer>> ladderend = new ArrayList<ArrayList<Integer>>(n+1);
         
         for(int k = 0; k <= n; k++){
            ladderstart.add(new ArrayList<Integer>());
            ladderend.add(new ArrayList<Integer>());
         }
         
         for(int k = 0; k < l; k++){
            ladders[k] = new Ladder(fs.nextInt(),fs.nextInt(),fs.nextInt(),fs.nextInt(),fs.nextLong(),k);
            ladderstart.get(ladders[k].a).add(k);
            ladderend.get(ladders[k].c).add(k);
            
         }
         
         long[] dist = new long[l];
         Arrays.fill(dist,Long.MAX_VALUE);
         
         for(int i : ladderstart.get(1)){
            dist[i] = x[1] * (long)(ladders[i].b-1) - ladders[i].h;
         }
         
         for(int k = 2; k < n; k++){
            int endsize = ladderend.get(k).size();
            int startsize = ladderstart.get(k).size();
            
            if(startsize == 0 || endsize == 0) 
               continue;
            
            //sort by horizontal coord
            Collections.sort(ladderstart.get(k), (a, b) -> (ladders[a].b-ladders[b].b));
            Collections.sort(ladderend.get(k), (a, b) -> (ladders[a].d-ladders[b].d));
            
            //check left
            int bestleft = ladderend.get(k).get(0);
            int endi = 0;
            int starti = 0;
            while(starti < startsize && ladders[ladderstart.get(k).get(starti)].b < ladders[bestleft].d) starti++;
            
            while(endi < endsize || starti < startsize){
               if(starti >= startsize) 
                  break;
               
               Ladder startladder = ladders[ladderstart.get(k).get(starti)];
               
               if(endi >= endsize){
                  //use bestleft
                  if(dist[bestleft] != Long.MAX_VALUE)
                     dist[startladder.index] = Math.min(dist[startladder.index], dist[bestleft] + x[k] * (long)(startladder.b-ladders[bestleft].d) - startladder.h);
                  starti++;
                  continue;
               }
               
               Ladder endladder = ladders[ladderend.get(k).get(endi)];
               
               if(endladder.d <= startladder.b){
                  //update bestleft
                  if(dist[bestleft] == Long.MAX_VALUE || (dist[bestleft] + x[k] * (long)(endladder.d-ladders[bestleft].d) > dist[endladder.index])){
                     bestleft = endladder.index;
                  }
                  endi++;
               } else {
                  //use bestleft
                  if(dist[bestleft] != Long.MAX_VALUE)
                     dist[startladder.index] = Math.min(dist[startladder.index], dist[bestleft] + x[k] * (long)(startladder.b-ladders[bestleft].d) - startladder.h);
                  starti++;
               }
            }
            
            //check right
            int bestright = ladderend.get(k).get(endsize-1);
            endi = endsize-1;
            starti = startsize-1;
            while(starti >= 0 && ladders[ladderstart.get(k).get(starti)].b > ladders[bestright].d) starti--;
            
            while(endi >= 0 || starti >= 0){
               if(starti < 0) 
                  break;
                  
               Ladder startladder = ladders[ladderstart.get(k).get(starti)];
               
               if(endi < 0){
                  //use bestright
                  if(dist[bestright] != Long.MAX_VALUE)
                     dist[startladder.index] = Math.min(dist[startladder.index], dist[bestright] + x[k] * (long)(ladders[bestright].d-startladder.b) - startladder.h);
                  starti--;
                  continue;
               }
               
               Ladder endladder = ladders[ladderend.get(k).get(endi)];
               
               if(endladder.d >= startladder.b){
                  //update bestright
                  if(dist[bestright] == Long.MAX_VALUE || (dist[bestright] + x[k] * (long)(ladders[bestright].d-endladder.d) > dist[endladder.index])){
                     bestright = endladder.index;
                  }
                  endi--;
               } else {
                  //use bestright
                  if(dist[bestright] != Long.MAX_VALUE)
                     dist[startladder.index] = Math.min(dist[startladder.index], dist[bestright] + x[k] * (long)(ladders[bestright].d-startladder.b) - startladder.h);
                  starti--;
               }
            }
            
            
            
         }
         
         long answer = Long.MAX_VALUE;
         
         for(int endi : ladderend.get(n)){
            if(dist[endi] == Long.MAX_VALUE) 
               continue;
            answer = Math.min(answer,dist[endi] + x[n]*(long)(m-ladders[endi].d));
         }
         
         if(answer == Long.MAX_VALUE){
            out.println("NO ESCAPE");
         } else {
            out.println(answer);
         }
         
            
      }
      
      
      
      
      out.close();
   }
   
   public static class Ladder{
      int a;
      int b;
      int c;
      int d;
      long h;
      int index;
      
      public Ladder(int q, int w, int e, int r, long t, int i){
         a = q;
         b = w;
         c = e;
         d = r;
         h = t;
         index = i;
      }
   }
   
   static class FastScanner
   {
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

      
}