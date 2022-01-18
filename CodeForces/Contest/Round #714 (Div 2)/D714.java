//make sure to make new file!
import java.io.*;
import java.util.*;

public class D714{

   public static int[] parent;
   public static int[] sizes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long p = Long.parseLong(st.nextToken());
         /*
         //instantiate dsu arrays
         //ONE INDEXED
         parent = new int[n+1];
         
         for(int k = 1; k <= n; k++){
            parent[k] = k;
         }
         
         sizes = new int[n+1];                     //stores depth of every dsu
         Arrays.fill(sizes,1);
         */  
         long[] array = new long[n+1];
         PriorityQueue<State> pq = new PriorityQueue<State>();
         
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
            pq.add(new State(array[k],k));
         }
         
         HashSet<Integer> seen = new HashSet<Integer>();
         int edgesadded = 0;
         long answer = 0;
         
         while(!pq.isEmpty()){
            State s = pq.poll();
            
            long x = s.x;
            int i = s.i;
            
            if(x >= p) break;
            if(seen.contains(i)) continue;
            
            int l = i-1;
            int curadd = 0;
            while(l >= 1 && array[l]%x == 0){
               
               curadd++;
               if(seen.contains(l)) break;
               seen.add(l);
               l--;
               
            }
            int r = i+1;
            while(r <= n && array[r]%x == 0){
               
               curadd++;
               if(seen.contains(r)) break;
               seen.add(r);
               r++;
               
            }
            
            seen.add(i);
            
            edgesadded += curadd;
            answer += (long)curadd*x;
            if(edgesadded >= n-1) break;                                //shouldn't be > n-1
         }
         
         if(edgesadded < n-1){
            answer += (long)(n-1-edgesadded)*p;
         }
         
         out.println(answer);
         
         
         
         
         
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      long x;
      int i;
      public State(long a, int b){
         x = a;
         i = b;
      }
      public int compareTo(State s){
         if(x > s.x) return 1;
         if(x < s.x) return -1;
         return 0;
      }
   }
   
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }
      
}