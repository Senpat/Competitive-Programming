//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   public static int[][] spta,sptb;
   public static int MAXN = 100005;
   public static int K = 18;
   public static int[] log;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      //precompute log
      log = new int[MAXN+1];
      log[1] = 0;
      for (int i = 2; i <= MAXN; i++)
         log[i] = log[i/2] + 1;
      
      
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st.nextToken());
         }
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            b[k] = Integer.parseInt(st.nextToken());
         }
         
         spta = new int[MAXN][K+1];
         for (int i = 0; i < n; i++)
            spta[i][0] = a[i];
      
         for (int j = 1; j <= K; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
               spta[i][j] = Math.max(spta[i][j-1], spta[i + (1 << (j - 1))][j - 1]);
         
         sptb = new int[MAXN][K+1];
         for (int i = 0; i < n; i++)
            sptb[i][0] = b[i];
      
         for (int j = 1; j <= K; j++)
            for (int i = 0; i + (1 << j) <= n; i++)
               sptb[i][j] = Math.max(sptb[i][j-1], sptb[i + (1 << (j - 1))][j - 1]);
         
         
         int answer = 0;
         for(int l = 0; l < n; l++){
            for(int r = l; r < n; r++){
               //out.println(l + " " + r + " " + maxquerya(l,r) + " " + maxqueryb(l,r));
               if(Math.abs(maxquerya(l,r)-maxqueryb(l,r)) <= m){
                  answer++;
               }
            }
         }
         
         out.println("Case #" + q + ": " + answer);
      
      }
      
      
      
      
      out.close();
   }
   
   //sparse table
   public static int maxquerya(int l, int r){
      int j = log[r-l+1];
      int max = Math.max(spta[l][j], spta[r - (1 << j) + 1][j]);
      return max;
   }
   public static int maxqueryb(int l, int r){
      int j = log[r-l+1];
      int max = Math.max(sptb[l][j], sptb[r - (1 << j) + 1][j]);
      return max;
   }
      
}