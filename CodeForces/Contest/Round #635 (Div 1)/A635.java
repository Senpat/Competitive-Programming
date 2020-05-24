//make sure to make new file!
import java.io.*;
import java.util.*;

public class A635{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] depths;
   public static int[] subtree;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      depths = new int[n+1];
      depths[1] = 0;
      dfsdepth(1,-1);
      
      subtree = new int[n+1];
      dfssubtree(1,-1);
      
      PriorityQueue<Pair> pq = new PriorityQueue<Pair>(n-1,Collections.reverseOrder());
      for(int k = 1; k <= n; k++){
         pq.add(new Pair(depths[k],subtree[k]));
      }
      
      long answer = 0L;
      for(int k = 0; k < m; k++){
         Pair p = pq.poll();
         answer += (long)(p.d-p.s);
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static void dfsdepth(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         depths[nei] = depths[v]+1;
         dfsdepth(nei,v);
      }
   }
   
   public static void dfssubtree(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfssubtree(nei,v);
         subtree[v] += subtree[nei] +1;
      }
   }
   
   public static class Pair implements Comparable<Pair>{
      int d;
      int s;
      
      public Pair(int a, int b){
         d = a;
         s = b;
      }
      public int compareTo(Pair p){
         return (d-s) - (p.d-p.s);
      }
   }
   
      
}