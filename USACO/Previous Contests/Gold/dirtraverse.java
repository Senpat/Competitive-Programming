/*
TASK: dirtraverse
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class dirtraverse{
   
   public static ArrayList<ArrayList<Edge>> adj;
   public static Edge[] parents;
   public static long[] leaves;
   public static long total;
   public static long max;
   public static boolean[] seen;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("dirtraverse.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
       
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>()); 
      
      parents = new Edge[n+1];
      
      long[] sizes = new long[n+1];
      String[] input = new String[n+1];
      
      HashSet<Integer> leafset = new HashSet<Integer>();
      
      for(int k = 1; k <= n; k++){
         String line = f.readLine();
         input[k] = line;
         
         StringTokenizer st = new StringTokenizer(line);
         sizes[k] = (long)st.nextToken().length();
         if(Integer.parseInt(st.nextToken()) == 0){
            leafset.add(k);
         }
      }
      
      for(int k = 1; k <= n; k++){
         StringTokenizer st = new StringTokenizer(input[k]);
         String name = st.nextToken();
         
         
         int num = Integer.parseInt(st.nextToken());
         for(int j = 0; j < num; j++){
            int i = Integer.parseInt(st.nextToken());
            if(leafset.contains(i)){
               parents[i] = new Edge(k,sizes[i]);
               adj.get(k).add(new Edge(i,sizes[i]));
               adj.get(i).add(new Edge(k,3));
            } else {
               parents[i] = new Edge(k,sizes[i]+1);
               adj.get(k).add(new Edge(i,sizes[i]+1));
               adj.get(i).add(new Edge(k,3));
            }
            
         }
      }
      parents[1] = new Edge(-1,0);
      leaves = new long[n+1];
      seen = new boolean[n+1];
      findleaves(1);
      
      //for(int k = 1; k <= n; k++) System.out.print(leaves[k] + " ");
         
      total = 0L;
      findtotal(1,0L);
      
      max = 0L;
      //dfs(1);
      for(int v = 2; v <= n; v++){
         if(adj.get(v).size() > 1) max = Math.max(max,parents[v].w * leaves[v] - 3*(leaves[1]-leaves[v]));
      }
      
      System.out.println(total + " " + max);
      long answer = total-max;
      
      System.out.println(answer);
      out.println(answer);
      
        
      out.close();
   }
   
   public static void dfs(int v){
      if(v != 1 && adj.get(v).size() > 1){
         max = Math.max(parents[v].w * leaves[v] - 3*(leaves[1]-leaves[v]),max);
         //System.out.println(v + " " + max);
      } else {
         for(Edge nei : adj.get(v)){
            if(nei.v != parents[v].v){
               dfs(nei.v);
            }
         }
      }
   }
   
   public static void findtotal(int v,long t){
      if(adj.get(v).size() == 1){
         total += t;
         return;
      }
      for(Edge nei : adj.get(v)){
         if(nei.v != parents[v].v){
            //total+=nei.w;
            //System.out.println(v + " " + nei.v + " " + nei.w + " " + total);
            findtotal(nei.v,t + nei.w);
         }
      }
   }
   
   public static void findleaves(int v){
      seen[v] = true;
      if(adj.get(v).size() == 1){
         leaves[v] = 1;
      } else {
         for(Edge nei : adj.get(v)){
            if(!seen[nei.v]){
               findleaves(nei.v);
               leaves[v] += leaves[nei.v];
            }
         }
         
      }
   }
   
   
   
   
   public static class Edge{
      int v;                              //vertex
      long w;                              //weight
      public Edge(int a, long b){
         v = a;
         w = b;
      }
      public String toString(){
         return v + " " + w;
      }
   }
      
      
}