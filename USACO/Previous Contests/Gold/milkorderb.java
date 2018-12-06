/*
TASK: milkorder
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class milkorderb{

   public static ArrayList<TreeSet<Integer>> adj;
   public static Stack<Integer> stk;
   public static boolean[] seen;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("milkorder.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] indegree = new int[n+1];
      
      int noind = n;                               //vertices with 0 incoming edges
      
      adj = new ArrayList<TreeSet<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new TreeSet<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int num = Integer.parseInt(st.nextToken());
         int[] input = new int[num];
         for(int j = 0; j < num; j++){
            input[j] = Integer.parseInt(st.nextToken());
         }
         
         int changes = 0;
         for(int j = 1; j < num; j++){
            if(indegree[input[j]] == 0) changes++;
         }
         
         if(noind-changes>0){
            noind-=changes;
            for(int j = 1; j < num; j++){
               adj.get(input[j-1]).add(input[j]);
            }
         } else {
            //do topological sort
            stk = new Stack<Integer>();
            seen = new boolean[n+1];
            for(int v = n; v >= 1; v--){
               if(!seen[v]) dfs(v);
            }
            System.out.print(stk.peek());
            out.print(stk.pop());
            while(!stk.empty()){
               System.out.print(" " + stk.peek());
               out.print(" " + stk.pop());
               
            }
            out.println();
            out.close();
            System.exit(0);
         }
      }
        
      stk = new Stack<Integer>();
      seen = new boolean[n+1];
      for(int v = n; v >= 1; v--){
         if(!seen[v]) dfs(v);
      }
      System.out.print(stk.peek());
      out.print(stk.pop());
      while(!stk.empty()){
         System.out.print(" " + stk.peek());
         out.print(" " + stk.pop());
               
      }
      out.println();
      out.close();
   }
   
   public static void dfs(int v){
      seen[v] = true;
      while(!adj.get(v).isEmpty()){
         int cur = adj.get(v).pollLast();
         
         if(!seen[cur]) dfs(cur);
      }
      stk.add(v);
   }
   
      
}