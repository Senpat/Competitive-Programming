//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, semi-t, not done
public class E600{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Ant[] array = new Ant[n+1];
      array[0] = new Ant(0,0);
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         array[k] = new Ant(a,b);
      }
      
      int[] dp = new int[m+1];
      dp[m+1] = 0;
      
      int aindex = n+1;
      
      for(int k = m-1; k >= 1; k--){
         
         dp[k] = m-k;
         
         if(k < array[n-1].x){
            aindex--;
         }
         
         for(int j = aindex; j <= n; j++){
            if(array[j].x+array[j].x + (array[j].x-array[j].s-k) > m){
               dp[k] = Math.min(dp[k], dp[array[j].x-array[j].s
      
      
      
      
      out.close();
   }
   
   public static class Ant{
      int x;
      int s;
      public Ant(int a, int b){
         x = a;
         s = b;
      }
   }
   
   
}