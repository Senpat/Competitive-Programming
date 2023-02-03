/*
TASK: agrinet
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class agrinet{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("agrinet.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      StringBuilder sb = new StringBuilder();
      while(true){
         String s = f.readLine();
         if(s == null) break;
         sb.append(s+" ");
      }
      
      //System.out.println(sb.toString());
      
      StringTokenizer st = new StringTokenizer(sb.toString());
      
      int[][] adj = new int[n][n];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            adj[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      boolean[] seen = new boolean[n];
      int[] mine = new int[n];
      Arrays.fill(mine,Integer.MAX_VALUE);
      mine[0] = 0;
      
      int sum = 0;
      for(int k = 0; k < n; k++){
         int v = -1;
         for(int j = 0; j < n; j++){
            if(!seen[j] && (v == -1 || mine[j] < mine[v])){
               v = j;
            }
         }
         
         seen[v] = true;
         
         sum += mine[v];
         
         for(int j = 0; j < n; j++){
            if(!seen[j]){
               mine[j] = Math.min(mine[j],adj[v][j]);
            }
         }
      }
      
      
      out.println(sum);
        
      out.close();
   }
      
}