//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] mins;
   public static int T;
   public static HashSet<Integer> seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      ArrayList<Pair> q0 = new ArrayList<Pair>();
      ArrayList<Pair> q1 = new ArrayList<Pair>();
      
      mins = new int[n+1];
      Arrays.fill(mins,Integer.MAX_VALUE);
      
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
      
         if(a == 0){
            q0.add(new Pair(b,c));
            mins[b] = Math.min(mins[b],c);
         } else {
            
            q1.add(new Pair(b,c));
         }
      
      }
      
      Collections.sort(q0);
      
      for( Pair p : q0){
         seen = new HashSet<Integer>();
         dfs(p.q,p.time);
      }
      
      for( Pair p : q1){
         //out.println(mins[p.q] + " " + p.time);
         if(mins[p.q] <= p.time){
            out.println(1);
         } else {
            out.println(0);
         }
      }
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int t){
      
      seen.add(v);
      for(int nei : adj.get(v)){
         if(seen.contains(nei)) 
            continue;
         if(mins[nei] <= t) 
            continue;
         mins[nei] = t;
         dfs(nei,t+T);
      }
      
   }
   
   
   public static class Pair implements Comparable<Pair>{
      int q;
      int time;
      public Pair(int a, int b){
         q = a;
         time = b;
      }
      public int compareTo(Pair p){
         return time-p.time;
      }
   }
   
      
}