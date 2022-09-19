//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong
public class FLEX{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      //precomp edges to next biggest element and segtree
      t = new long[4*MAX_N];
      Arrays.fill(t,Long.MAX_VALUE);      //or build
      
      
      
      
      
      
      
      
      out.close();
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