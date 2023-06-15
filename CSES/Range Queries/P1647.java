//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1647{

   public static int[][] spt;
   public static int[] log;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      int M = 19;
      
      spt = new int[N][M];
      
      log = new int[N];
      log[1] = 0;
      for(int k = 2; k < N; k++){
         log[k] = log[k/2]+1;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         spt[k][0] = array[k];
      }
      
      for(int d = 1; d < M; d++){
         for(int k = 0; k + (1<<(d-1)) < n; k++){
            spt[k][d] = Math.min(spt[k][d-1],spt[k+(1<<(d-1))][d-1]);
         }
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         out.println(min(l,r));
         
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int min(int l, int r){
      int d = log[r-l+1];
      
      return Math.min(spt[l][d],spt[r-(1<<d)+1][d]);
   }
   
      
}