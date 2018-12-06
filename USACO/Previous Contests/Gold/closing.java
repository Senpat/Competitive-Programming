/*
TASK: closing
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class closing{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] parents,sizes;
   public static boolean[] open,seen;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("closing.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      int[] queries = new int[n];
      
      for(int k = 0; k < n; k++){
         queries[k] = Integer.parseInt(f.readLine());
      }
      
      String[] answer = new String[n];
      
      parents = new int[n+1];
      
      for(int k = 1; k <= n; k++) parents[k] = k;
      
      sizes = new int[n+1];
      Arrays.fill(sizes,1);
      
      open = new boolean[n+1];
      
      for(int k = n-1; k >= 0; k--){
         open[queries[k]] = true;
         for(int nei : adj.get(queries[k])){
            if(open[nei]){
               union(nei,queries[k]);
            }
         }
         if(sizes[find(queries[k])] == n-k){
            answer[k] = "YES";
         } else {
            answer[k] = "NO";
         }
      }
      
      for(int k = 0; k < n; k++){
         //System.out.println(answer[k]);
         out.println(answer[k]);
      }
      
      
        
      out.close();
   }
   
   public static int find(int v){
      if(parents[v] == v) return v;
      else{
         parents[v] = find(parents[v]);
         return parents[v];
      }
   }
   
   public static void union(int u, int v){
      int findu = find(u);
      int findv = find(v);
      if(findu==findv) return;
      sizes[findv] += sizes[findu];
      parents[findu] = findv;
   }
      
      
      
      
}