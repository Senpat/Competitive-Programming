//make sure to make new file!
import java.io.*;
import java.util.*;

public class C800{

   public static ArrayList<ArrayList<Integer>> adj;
   //public static ArrayList<ArrayList<Integer>> radj;

   public static boolean[] seen;
   public static Stack<Integer> stack;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      //radj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
         //radj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());   
      
         adj.get(a).add(b);
      }
      
      seen = new boolean[n+1];
      stack = new Stack<Integer>();
      topsort(1);
      
      ArrayList<Integer> order = new ArrayList<Integer>();
      while(!stack.empty()){
         order.add(stack.pop());
      }
      
      int[] mindist = new int[n+1];
      Arrays.fill(mindist,Integer.MAX_VALUE);
      mindist[n] = 0;
      
      boolean hitn = false;
      boolean[] visited = new boolean[n+1];
      for(int o = order.size()-1; o >= 0; o--){
         int v = order.get(o);
         visited[v] = true;
         //if(mindist[v] == Integer.MAX_VALUE) continue;
         
         if(v == n){
            hitn = true;
            continue;
         }
         if(!hitn) continue;
         
         int needcancel = 0;
         ArrayList<Integer> dists = new ArrayList<Integer>();
         for(int nei : adj.get(v)){
            if(mindist[nei] == Integer.MAX_VALUE){
               needcancel++;
            } else {
               dists.add(mindist[nei]);
            }
            
         }
         
         if(dists.size() == 0) continue;
         
         Collections.sort(dists);
         
         int min = dists.get(dists.size()-1)+1;
         int cancel = 0;
         
         for(int k = dists.size()-2; k >= 0; k--){
            cancel++;
            
            min = Math.min(min,dists.get(k)+1 +cancel);
         }
         
         mindist[v] = min+needcancel;
      }
      
      out.println(mindist[1]);
      
      
      
      
      out.close();
   }
   
   
   
   
   public static void topsort(int v){
      seen[v] = true;
      
      for(int nei : adj.get(v)){
         if(seen[nei]) continue;
         topsort(nei);
      }
      
      stack.push(v);
   }
   
      
}