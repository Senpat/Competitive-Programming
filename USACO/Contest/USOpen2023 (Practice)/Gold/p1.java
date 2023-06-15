//make sure to make new file!
import java.io.*;
import java.util.*;

public class p1{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] color;
   public static int[] keyhas;
   public static int[] keyneed;
   
   public static boolean[] seen;
   
   //next.get(x) stores the nodes that you can reach but currently can't that need color x
   public static ArrayList<Queue<Integer>> next;
   
   public static boolean[] allkeys;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         //blank line
         f.readLine();
         
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         StringTokenizer st3 = new StringTokenizer(f.readLine());
         
         color = new int[n+1];
         keyhas = new int[n+1];
         keyneed = new int[n+1];
         
         for(int k = 1; k <= n; k++){
            color[k] = Integer.parseInt(st1.nextToken());
            keyhas[k] = Integer.parseInt(st2.nextToken());
            keyneed[k] = Integer.parseInt(st3.nextToken());
         }
         
         adj = new ArrayList<>(n+1);
         next = new ArrayList<>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Integer>());
            next.add(new LinkedList<Integer>());
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
      
         allkeys = new boolean[n+1];
         seen = new boolean[n+1];
         seen[1] = true;
         allkeys[keyhas[1]] = true;
         
         dfs(1);
         
         boolean fail = false;
         for(int k = 2; k <= n; k++){
            if(keyhas[k] != keyneed[k] && !seen[k]){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      
      while(!next.get(keyhas[v]).isEmpty()){
         int i = next.get(keyhas[v]).poll();
         if(!seen[i]){
            seen[i] = true;
            allkeys[keyhas[i]] = true;
            dfs(i);
         }
      }
      
      for(int nei : adj.get(v)){
         if(seen[nei]) continue;
         if(allkeys[color[nei]]){
            seen[nei] = true;
            allkeys[keyhas[nei]] = true;
            dfs(nei);
         } else {
            next.get(color[nei]).add(nei);
         }
      }
      
   }
   
      
}