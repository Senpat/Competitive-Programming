//make sure to make new file!
import java.io.*;
import java.util.*;

public class ETON{
   
   public static int[] parent;
   public static int[] sizes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n+1];
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            a[k] = Integer.parseInt(st.nextToken());
         }
         
         ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
         
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            
            adj.get(a1).add(a2);
            adj.get(a2).add(a1);
         }
         
         //instantiate dsu arrays
         //ONE INDEXED
         parent = new int[n+1];
      
         for(int k = 1; k <= n; k++){
            parent[k] = k;
         }
      
         sizes = new int[n+1];                     //stores depth of every dsu
         Arrays.fill(sizes,1);
         
         PriorityQueue<State> pq = new PriorityQueue<State>();
         boolean[] seen = new boolean[n+1];
         boolean[] pass = new boolean[n+1];
         for(int k = 1; k <= n; k++){
            if(a[k] == 0){
               pq.add(new State(k,a[k]));
               seen[k] = true;
               pass[k] = true;
            }
         }
         
         boolean fail = false;
         while(!pq.isEmpty()){
            State s = pq.poll();
            
            seen[s.v] = true;
            int maxnei = -1;
            for(int nei : adj.get(s.v)){
               if(pass[nei]){
                  if(maxnei == -1 || sizes[find(maxnei)] < sizes[find(nei)]){
                     maxnei = nei;
                  }
               }
            }
            
            for(int nei : adj.get(s.v)){
               if(pass[nei]) union(nei,s.v);
            }
            
            if(a[s.v] != 0 && sizes[find(maxnei)] < a[s.v]){
               continue;
            }
            
            for(int nei : adj.get(s.v)){
               if(!seen[nei]){
                  pq.add(new State(nei,a[nei]));
                  pass[nei] = true;
               }
            }
         }
         
         if(fail || sizes[find(1)] != n){
            out.println("NO");
         } else {
            out.println("YES");
         }
      
      }
      
      
      
      
      out.close();
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      if(findv == findu) return;
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) 
         return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }

   
   public static class State implements Comparable<State>{
      int v;
      int size;
      public State(int a, int b){
         v = a;
         size = b;
      }
      public int compareTo(State s){
         return size-s.size;
      }
   }
}