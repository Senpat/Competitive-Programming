//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
      
         long H = fs.nextLong();
         long H2 = 2L*H;
         
         ArrayList<Pair> points = new ArrayList<Pair>();
         int w = fs.nextInt();
         for(int k = 0; k < w; k++){
            points.add(new Pair(fs.nextLong(),fs.nextLong(),1));
         }
         
         int c = fs.nextInt();
         for(int k = 0; k < c; k++){
            points.add(new Pair(fs.nextLong(),fs.nextLong(),0));
         }
         
         Collections.sort(points);
         
         Mapl up = new Mapl();
         Mapl down = new Mapl();
         
         for(int k = points.size()-1; k >= 0; k--){
            Pair p = points.get(k);
            if(p.type == 0){
               //coin
               
               //up
               long upmod = ((p.y-p.x)%H2 + H2)%H2;
               up.incr(upmod,1);
               
               //down        
               long downmod = ((p.y+p.x)%H2 + H2)%H2;
               down.incr(downmod,1);
            
            } else {
               //wood
            
               //up
               long upmod = ((p.y-p.x)%H2 + H2)%H2;
               long updmod = ((p.x-p.y)%H2 + H2)%H2;                 //move to the top, newx = x + (H-y), newy = H, newx+newy = x-y+2*H = x-y  
               long upval = up.get0(upmod) + down.get0(updmod);
               
               //down
               long downmod = ((p.y+p.x)%H2 + H2)%H2;
               long downumod = ((-p.y-p.x)%H2 + H2)%H2;              //move to the bottom, newx = x+y, newy = 0, newy-newx = -y-x
               long downval = down.get0(downmod) + up.get0(downumod);
               
               long max = Math.max(upval,downval);
               
               up.put(downumod,0L);
               down.put(updmod,0L);
               
               up.put(upmod,max);
               down.put(downmod,max);
               
               
            }
         }
         
         long answer = up.get0(0) + down.get0(0);
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static class Mapl extends HashMap<Long,Long>{
      
      public Mapl(){
         super();
      }
      
      public void incr(long key, long x){
         put(key,get0(key)+x);
      }
      
      public long get0(long key){
         if(containsKey(key)) 
            return get(key);
         else 
            return 0L;
      }
      
      public String toString(){
         String ret = "";
         for(long key : keySet()){
            ret += key + " -> " + get(key) + "\n";
         }
         return ret;
      }
   }
      
   
   public static class Pair implements Comparable<Pair>{
      long x;
      long y;
      int type;                           //type == 0 is a coin, type == 1 is a wood
      
      public Pair(long a, long b, int c){
         x = a;
         y = b;
         type = c;
      }
      
      public int compareTo(Pair p){
         //compare based on x
         if(x > p.x) 
            return 1;
         else if(x < p.x) 
            return -1;
         return 0;
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