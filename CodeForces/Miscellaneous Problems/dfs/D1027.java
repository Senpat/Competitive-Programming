//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1027{

   public static ArrayList<ArrayList<Integer>> adj,tadj,comp;
   public static Stack<Integer> stk;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] c = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         c[k] = Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      tadj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
         tadj.add(new ArrayList<Integer>());
      }
      
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(st.nextToken());
         
         adj.get(k).add(i);
         tadj.get(i).add(k);
      }
      
      stk = new Stack<Integer>();
      seen = new boolean[n+1];
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            seen[k] = true;
            dfs1(k);
         }
      }
      
      seen = new boolean[n+1];
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            dfs1(k);
         }
      }
      
      seen = new boolean[n+1];
      
      comp = new ArrayList<ArrayList<Integer>>();
      
      while(!stk.empty()){
         int cur = stk.pop();
         if(seen[cur]) continue;
         comp.add(new ArrayList<Integer>());
         
         dfs2(cur);
      }
      
      int answer = 0;
      
      for(ArrayList<Integer> alist : comp){
         if(alist.size() == 1){
            if(adj.get(alist.get(0)).get(0) == alist.get(0)){
               answer+=c[alist.get(0)];
            }
         } else {
            
            int min = Integer.MAX_VALUE;
            for(int v : alist){
               min = Math.min(min,c[v]);
            }
            
            answer+=min;
         }
      }
      
      out.println(answer);
           
      
      out.close();
   }
   
   public static void dfs1(int v){
      if(!seen[v]){
         seen[v] = true;
         for(int nei : adj.get(v)){
            if(!seen[nei]){
               dfs1(nei);
            }
         }
         
         stk.push(v);
      }
   }
   
   public static void dfs2(int v){
      if(!seen[v]){
         seen[v] = true;
         comp.get(comp.size()-1).add(v);
         for(int nei : tadj.get(v)){
            if(!seen[nei]){
               dfs2(nei);
            }
         }
      }
   }
   
}