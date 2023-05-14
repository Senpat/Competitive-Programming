//Mr. Kitayuta, the Treasure Hunter
import java.io.*;
import java.util.*;
//faster, doesn't use hashmap
public class C505b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 30000;
      int M = 500;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int[] array = new int[N+1];
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(f.readLine());
         array[i]++;
      }
      
      int[] mins = new int[N+1];
      int i = d;
      int dd = d;
      while(i <= N){
         mins[i] = dd;
         dd = Math.max(1,dd-1);
         i += dd;
      }
      
      for(int k = d; k <= N; k++){
         if(mins[k] == 0){
            mins[k] = mins[k-1];
         }
      }
      
      int[][] dp = new int[N+1][M];
      for(int k = 0; k <= N; k++) Arrays.fill(dp[k],-1);
      
      int answer = 0;
      dp[d][0] = array[d];
      
      for(int k = d; k <= N; k++){
         for(int j = 0; j < M; j++){
            if(dp[k][j] == -1) continue;
            //real value is mins[k]+j
            int v = mins[k]+j;
            answer = Math.max(answer,dp[k][j]);
         
            for(int di = -1; di <= 1; di++){
               if(k+v+di <= N && v+di >= 1){
                  dp[k+v+di][v+di-mins[k+v+di]] = Math.max(dp[k+v+di][v+di-mins[k+v+di]],dp[k][j]+array[k+v+di]);
               }
            }
         
         }
         
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   
      
}