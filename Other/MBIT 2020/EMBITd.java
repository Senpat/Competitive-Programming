//make sure to make new file!
import java.io.*;
import java.util.*;

public class EMBITd{

   public static int[][] t;
   public static int MAX_N = 2505;
   
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
      
      
      t = new int[2][4*MAX_N];
      for(int k = 0; k < 2; k++){
         Arrays.fill(t[k],Integer.MAX_VALUE);      //or build
      }
      
      int[] answer = new int[m+1];
      
      for(int k = 1; k <= m; k++){
         answer[k] = Math.abs(k-points[0].x);
         update(1,1,m,k,answer[k],0);
      }
      
      
      for(int k = 1; k < n; k++){
         for(int j = 1; j <= m; j++){
            //get min in the spread
            //out.println(k + " " + j + " " + Math.max(1,j-spread[points[k].y-points[k-1].y]) + " " + (Math.min(m,j+spread[points[k].y-points[k-1].y])));
            //out.flush();
            int min = min(1,1,m,Math.max(1,j-spread[points[k].y-points[k-1].y]),Math.min(m,j+spread[points[k].y-points[k-1].y]),0);
            
            int newvalue = min + Math.abs(j-points[k].x);
            answer[j] = newvalue;
            update(1,1,m,j,answer[j],1);
         }
         
         for(int j = 0; j < t[0].length ; j++){
            t[0][j] = t[1][j];
            t[1][j] = Integer.MAX_VALUE;
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
   
   //to call: v=1, tl = 0, tr = n-1
   public static int min(int v, int tl, int tr, int l, long r,int i) {
      /*
      System.out.println(v + " " + tl + " " + tr + " " + l + " " + r + " " + i);
      System.out.flush();
      if(v < 0){
         System.exit(0);
      }*/
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
   
   
      
}