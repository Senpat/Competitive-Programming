//make sure to make new file!
import java.io.*;
import java.util.*;

public class minsegtree{

   public static long[] t;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      
      t = new long[4*MAX_N];
      Arrays.fill(t,Long.MAX_VALUE);      //or build
   
      
      
      
      
      
      out.close();
   }
   
   //build currently unconfirmed working
   public static void build(int[] a, int v, int tl, int tr) {
      if (tl == tr) {
         t[v] = a[tl];
      } else {
         int tm = (tl + tr) / 2;
         build(a, v*2, tl, tm);
         build(a, v*2+1, tm+1, tr);
         t[v] = Math.min(t[v*2],t[v*2+1]);
      }
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static long min(int v, int tl, int tr, int l, long r) {
      if (l > r) 
         return Long.MAX_VALUE;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return Math.min(min(v*2, tl, tm, l, Math.min(r, tm)),min(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, long new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = Math.min(t[v*2],t[v*2+1]);
      }
   }
   
     
   
      
}