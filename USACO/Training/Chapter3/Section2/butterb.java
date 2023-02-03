/*
TASK: butter
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class butterb{
   
   public static int[] cows;
   public static int[][] adj;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("butter.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int c = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      cows = new int[n+1];
      for(int k = 0; k < c; k++){
         int i = Integer.parseInt(f.readLine());
         cows[i]++;
      }
      
      adj = new int[n+1][n+1];
      for(int k = 0; k <= n; k++){
         Arrays.fill(adj[k],Integer.MAX_VALUE);
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         adj[a][b] = w;
         adj[b][a] = w;
         
      }
      
      int[][] dist = new int[n+1][n+1];
      for(int k = 0; k <= n; k++){
         for(int j = 0; j <= n; j++){
            dist[k][j] = adj[k][j];
         }
         dist[k][k] = 0;
      }
      
      //floyd warshall
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            for(int h = 1; h <= n; h++){
               if(dist[j][k] == Integer.MAX_VALUE || dist[k][h] == Integer.MAX_VALUE) continue;
               dist[j][h] = Math.min(dist[j][h],dist[j][k] + dist[k][h]);     
            }
         }
      }
        
      int min = Integer.MAX_VALUE;
      for(int k = 1; k <= n; k++){
         int sum = 0;
         for(int j = 1; j <= n; j++){
            sum += cows[j] * dist[k][j];
         }
         min = Math.min(min,sum);
      }
      
      out.println(min);
        
      out.close();
   }
   
        
}