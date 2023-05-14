//make sure to make new file!
import java.io.*;
import java.util.*;

public class D864{

   public static ArrayList<ArrayList<Integer>> adj;
   public static long[] array;
   
   public static long[] subsum;
   public static int[] subsize;
   public static int[] parent;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      array = new long[n+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      subsum = new long[n+1];
      parent = new int[n+1];
      subsize = new int[n+1];
      dfs(1,-1);
      
      ArrayList<TreeSet<Pair>> tsets = new ArrayList<TreeSet<Pair>>(n+1);
      for(int k = 0; k <= n; k++) tsets.add(new TreeSet<Pair>());
      
      for(int k = 1; k <= n; k++){
         for(int nei : adj.get(k)){
            if(nei == parent[k]) continue;
            tsets.get(k).add(new Pair(nei,subsize[nei]));  
         }
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
      
         int i = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            out.println(subsum[x]);
         } else {
            if(tsets.get(x).size() == 0) continue;
            
            Pair heavy = tsets.get(x).last();
            //out.println(heavy.i);
            
            Pair px = new Pair(x,subsize[x]);
            tsets.get(parent[x]).remove(px);
            
            tsets.get(x).remove(heavy);
            
            long prev = subsum[heavy.i];
            subsum[heavy.i] = subsum[x];
            subsum[x] -= prev;
            
            int prev2 = subsize[heavy.i];
            subsize[heavy.i] = subsize[x];
            subsize[x] -= prev2;
            
            
            tsets.get(parent[x]).add(new Pair(heavy.i,subsize[heavy.i]));
            tsets.get(heavy.i).add(new Pair(x,subsize[x]));
            
            //update parents
            int parx = parent[x];
            parent[heavy.i] = parx;
            parent[x] = heavy.i;
            
         }
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      parent[v] = p;
      subsum[v] = array[v];
      subsize[v] = 1;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         subsum[v] += subsum[nei];
         subsize[v] += subsize[nei];
      }
   }
   
   public static class Pair implements Comparable<Pair>{
      int i;
      int w;
      public Pair(int a, int b){
         i = a;
         w = b;
      }
      
      public int compareTo(Pair p){
         if(w < p.w) return -1;
         if(w > p.w) return 1;
         return p.i-i;
      }
   }
      
   
      
}