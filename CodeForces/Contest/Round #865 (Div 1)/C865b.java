//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class C865b{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<ArrayList<Integer>> radj;
   
   public static int[] depth;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int tt = 1; tt <= t; tt++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         adj = new ArrayList<>(n+1);
         radj = new ArrayList<>(n+1);
         for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Integer>());
            radj.add(new ArrayList<Integer>());
         }
        
         for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
         
            adj.get(a).add(b);
            radj.get(b).add(a);
         }
        
         depth = new int[n+1];
         depth[1] = 1;
         
         //bfs
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(1);
         
         while(!q.isEmpty()){
            int v = q.poll();
            
            for(int nei : radj.get(v)){
               if(depth[nei] != 0) continue;
               depth[nei] = depth[v]+1;
               q.add(nei);
            }
         }
         
         boolean fail = false;
         for(int k = 1; k <= n; k++){
            if(depth[k] == 0){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("INFINITE");
            continue;
         }
         
         
         ArrayList<ArrayList<Integer>> sections = new ArrayList<>(n+1);
         for(int k = 0; k <= n; k++) sections.add(new ArrayList<Integer>());
         for(int k = 1; k <= n; k++){
            sections.get(depth[k]).add(k);
         }
         
         int size = 0;
         StringJoiner sj = new StringJoiner(" ");
         ArrayList<Integer> path = genpath(n);
         for(int p : path){
            if(p < sections.size()){
               for(int i : sections.get(p)){
                  sj.add("" + i);
                  size++;
               }
            }
         }
         
         out.println("FINITE");
         out.println(size);
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
   
   
   public static ArrayList<Integer> genpath(int n){
      ArrayList<Integer> ret = new ArrayList<Integer>();
      
      for(int k = n; k >= 1; k--){
         for(int j = k; j <= n; j++){
            ret.add(j);
         }
      }
      return ret;
   }
   
      
}