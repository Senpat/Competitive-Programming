//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int INF = 1000000;
      int[][] costs = new int[26][26];
      for(int k = 0; k < 26; k++){
         for(int j = 0; j < 26; j++){
            costs[k][j] = INF;
         }
      }
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         String s = st.nextToken();
         int cost = Integer.parseInt(st.nextToken());
         
         int a = s.charAt(0)-'a';
         int b = s.charAt(1)-'a';
         
         costs[a][b] = Math.min(costs[a][b],cost);
      }
      
      int answer = INF;
      //check same
      for(int k = 0; k < 26; k++){
         if(costs[k][k] == INF) continue;
         answer = Math.min(answer,costs[k][k]*(m-1));
      }
      
      //check difs
      //m == 2 is a special case
      if((m > 2) && (m%2 == 0)){
         //even
         for(int k = 0; k < 26; k++){
            for(int j = k+1; j < 26; j++){
               if(costs[k][j] == INF || costs[j][k] == INF) continue;
               
               if(costs[k][k] != INF){
                  answer = Math.min(answer, (m/2 - 1)*(costs[k][j] + costs[j][k]) + costs[k][k]);
               }
               
               if(costs[j][j] != INF){
                  answer = Math.min(answer, (m/2 - 1)*(costs[k][j] + costs[j][k]) + costs[j][j]);
               }
            }
         }
         
         
      } else if (m%2 == 1){
         //odd
         for(int k = 0; k < 26; k++){
            for(int j = k+1; j < 26; j++){
               if(costs[k][j] == INF || costs[j][k] == INF) continue;
               answer = Math.min(answer,(m-1)/2 * (costs[k][j] + costs[j][k]));
            }
         }
      }
      
      
      if(answer == INF){
         out.println(-1);
      } else {
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}