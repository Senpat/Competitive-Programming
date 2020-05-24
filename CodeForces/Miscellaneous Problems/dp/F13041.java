//make sure to make new file!
import java.io.*;
import java.util.*;
//1304/F1
public class F13041{

   public static long[][] t;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      
      long[][] matrix = new long[n][m];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            matrix[k][j] = Long.parseLong(st.nextToken());
         }
      }
      
      long[][] psums = new long[n][m+1];
      for(int k = 0; k < n; k++){
         psums[k][0] = 0L;
         for(int j = 1; j <= m; j++){
            psums[k][j] = psums[k][j-1]+matrix[k][j-1];
         }
      }
      
      
      
      t = new long[n][4*20002];
      
      long[][] dp = new long[n][m];
      //initialize
      for(int k = 0; k <= m-w; k++){
         if(n == 1){
            dp[0][k] = psums[0][k+w]-psums[0][k];
         } else {
            dp[0][k] = psums[0][k+w]-psums[0][k]+psums[1][k+w]-psums[1][k];
         }
         
         update(1,0,m-1,k,dp[0][k],0);
      }
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j <= m-w; j++){
            long i1 = psums[k][j+w]-psums[k][j];
            if( k < n-1){
               i1 += psums[k+1][j+w]-psums[k+1][j];
            }
            
            long max = 0L;
            if(j-w >= 0){
               max = Math.max(max,max(1,0,m-1,0,j-w,k-1));
            }
            if(j <= m-w-1){
               max = Math.max(max,max(1,0,m-1,j+w,m-1,k-1));
            }
            
            for(int h = Math.max(0,j-w+1); h < j+w; h++){
               long sub;
               if(h < j){
                  sub = psums[k][h+w]-psums[k][j];
               } else {
                  sub = psums[k][j+w]-psums[k][h];
               }
               
               max = Math.max(max,dp[k-1][h]-sub);
            }
            
            dp[k][j] = i1+max;
            update(1,0,m-1,j,dp[k][j],k);
         }
      }
      
      long answer = max(1,0,m-1,0,m-1,n-1);
      out.println(answer);
            
            
            
      
      out.close();
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
   public static long max(int v, int tl, int tr, int l, long r, int i) {
      if (l > r) 
         return 0;
      if (l == tl && r == tr) {
         return t[i][v];
      }
      int tm = (tl + tr) / 2;
      return Math.max(max(v*2, tl, tm, l, Math.min(r, tm),i),max(v*2+1, tm+1, tr, Math.max(l, tm+1), r,i));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, long new_val, int i) {
      if (tl == tr) {
         t[i][v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val,i);
         else
            update(v*2+1, tm+1, tr, pos, new_val,i);
         t[i][v] = Math.max(t[i][v*2],t[i][v*2+1]);
      }
   }
   
      
}