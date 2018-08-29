//Cyclic Components

import java.io.*;
import java.util.*;

public class E479b{

   public static int n,m;
   public static ArrayList<ArrayList<Integer>> adj;
   public static boolean[] used;
   public static boolean curbool;
   

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>();
      used = new boolean[n+1];
      curbool = true;
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st1.nextToken());
         int b = Integer.parseInt(st1.nextToken());
        
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      int count = 0;
      for(int k = 1; k <= n; k++){
         if(!used[k]){
            dfs(k);
            if(curbool) count++;
         }
         curbool = true;
      }
      
      System.out.println(count);
   }
   
   public static void dfs(int cur){
      used[cur] = true;
      if(adj.get(cur).size()!=2) curbool = false;
      for(int i : adj.get(cur)){
         if(!used[i]){
            dfs(i);
         }
      }
   }
}