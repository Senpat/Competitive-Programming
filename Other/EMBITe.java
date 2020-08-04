//make sure to make new file!
import java.io.*;
import java.util.*;

public class EMBITe{

   //public static int[][] t;
   //public static int MAX_N = 2505;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      double d = Double.parseDouble(st.nextToken());
      
      Pair[] points = new Pair[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         points[k] = new Pair(a,b);
      }
      
      Arrays.sort(points);
         
      int[] spread = new int[1000];
      
      for(int k = 1; k < 1000; k++){
         spread[k] = (int)Math.floor(((double)k/d));
      }
      treeFrom = 1;
      length = m;
      int l;
      for (l = 0; (1 << l) < length; l++);
      val = new int[2][1 << (l + 1)];
      length = 1 << l;
      
      //t = new int[2][4*MAX_N];
      for(int k = 0; k < 2; k++){
         Arrays.fill(val[k],Integer.MAX_VALUE);      //or build
      }
      
      int[] answer = new int[m+1];
      
      for(int k = 1; k <= m; k++){
         answer[k] = Math.abs(k-points[0].x);
         update(k,answer[k],0);
      }
      
      
      for(int k = 1; k < n; k++){
         for(int j = 1; j <= m; j++){
            //get min in the spread
            //out.println(k + " " + j + " " + Math.max(1,j-spread[points[k].y-points[k-1].y]) + " " + (Math.min(m,j+spread[points[k].y-points[k-1].y])));
            //out.flush();
            int min = query(Math.max(1,j-spread[points[k].y-points[k-1].y]),Math.min(m,j+spread[points[k].y-points[k-1].y]),0);
            
            int newvalue = min + Math.abs(j-points[k].x);
            answer[j] = newvalue;
            update(j,answer[j],1);
         }
         
         for(int j = 0; j < val[0].length ; j++){
            val[0][j] = val[1][j];
            val[1][j] = Integer.MAX_VALUE;
         }
         
      }
      
      int min = Integer.MAX_VALUE;
      for(int k = 1; k <= m; k++){
         min = Math.min(min,answer[k]);
      }
      
      out.println(min);
      
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int x;
      int y;
      public Pair(int a, int b){
         x = a;
         y = b;
      }
      public int compareTo(Pair p){
         return y-p.y;
      }
   }
   
   public static int[][] val;
   public static int treeFrom;
   public static int length;
   
   public static void update(int index, int delta,int i) 
   {
          //replaces value
      int node = index - treeFrom + length;
      val[i][node] = delta;
      for (node >>= 1; node > 0; node >>= 1) 
         val[i][node] = comb(val[i][node << 1], val[i][(node << 1) + 1]);
   }
   public static int query(int from, int to,int i) 
   {
          //inclusive bounds
      if (to < from)
         return Integer.MAX_VALUE; //0 or 1?
      from += length - treeFrom;
      to += length - treeFrom + 1;
          //0 or 1?
      int res = 0;
      for (; from + (from & -from) <= to; from += from & -from) 
         res = comb(res, val[i][from / (from & -from)]);
      for (; to - (to & -to) >= from; to -= to & -to)
         res = comb(res, val[i][(to - (to & -to)) / (to & -to)]);
      return res;
   }
   public static int comb(int a, int b)
   {
          //change this
      return Math.min(a,b);
   }
   
   
   
   
   /*
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
   }*/
   /*
   //to call: v=1, tl = 0, tr = n-1
   public static int min(int v, int tl, int tr, int l, long r,int i) {
      if (l > r) 
         return Integer.MAX_VALUE;
      if (l == tl && r == tr) {
         return t[i][v];
      }
      int tm = (tl + tr) / 2;
      
      return Math.min(min(v*2, tl, tm, l, Math.min(r, tm),i),min(v*2+1, tm+1, tr, Math.max(l, tm+1), r,i));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, int new_val,int i) {
      if (tl == tr) {
         t[i][v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val,i);
         else
            update(v*2+1, tm+1, tr, pos, new_val,i);
         t[i][v] = Math.min(t[i][v*2],t[i][v*2+1]);
      }
   }
   
   */
      
}