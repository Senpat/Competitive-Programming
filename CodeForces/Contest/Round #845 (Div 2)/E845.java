//make sure to make new file!
import java.io.*;
import java.util.*;

public class E845{
   
   public static int n;
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static HashSet<Integer> seen;
   
   public static Stack<Integer> stack;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         ArrayList<Integer> edges = new ArrayList<Integer>();
         
         adj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Edge>());
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
                
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(new Edge(b,w,1));
            adj.get(b).add(new Edge(a,w,-1));
            
            edges.add(w);
         }
         
         //check if possible (if connected or not)
         seen = new HashSet<Integer>();
         seen.add(1);
         dfsconnected(1,Integer.MAX_VALUE);
         
         if(seen.size() != n){
            out.println(-1);
            continue;
         }
         
         Collections.sort(edges);
         
         int l = 0;
         int r = m-1;
         int ans = edges.get(m-1);
         while(l <= r){
            int mid = l + (r-l)/2;
            
            //thresh is edges.get(mid)
            if(check(edges.get(mid))){
               if(mid == 0) ans = 0;
               else ans = edges.get(mid-1);
               
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         out.println(ans);
         
      }
      
      
      
      
      out.close();
   }
   
   
   
   public static void topsort(int v, int thresh){
      
      for(Edge e : adj.get(v)){
         if(seen.contains(e.to)) continue;
         if(e.dir == -1 && e.w >= thresh) continue;
         seen.add(e.to);
         topsort(e.to,thresh);
      }
      
      stack.push(v);
      
   }
   
   
   public static boolean check(int thresh){
      //do topsort
      stack = new Stack<Integer>();
      seen = new HashSet<Integer>();
      
      for(int k = 1; k <= n; k++){
         if(!seen.contains(k)){
            seen.add(k);
            topsort(k,thresh);
         }
        
      }
      
      //get top of topsort
      int top = stack.pop();
      
      //check if connected
      seen = new HashSet<Integer>();
      seen.add(top);
      dfsconnected(top,thresh);
      return seen.size() == n;
   }
   
   
   public static void dfsconnected(int v,int thresh){
      
      for(Edge e : adj.get(v)){
         if(seen.contains(e.to)) continue;
         if(e.dir == -1 && e.w >= thresh) continue;
         seen.add(e.to);
         dfsconnected(e.to,thresh);
      }
      
      
   }
   
   
   public static class Edge{
      int to;
      int w;
      int dir;
      public Edge(int a, int b, int c){
         to = a;
         w = b;
         dir = c;
      }
        
   }
      
}