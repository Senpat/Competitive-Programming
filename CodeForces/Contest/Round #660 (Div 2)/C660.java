//make sure to make new file!
import java.io.*;
import java.util.*;

public class C660{
   
   public static int[] p;
   public static int[] h;
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] subsums;
   public static int[] parents;
   public static boolean fail;
   public static int[] sad;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         p = new int[n+1];
         h = new int[n+1];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            p[k] = Integer.parseInt(st.nextToken());
         }
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            h[k] = Integer.parseInt(st.nextToken());
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
         
         subsums = new int[n+1];
         parents = new int[n+1];
         
         dfs1(1,-1);
         
         fail = false;
         
         //count sad
         sad = new int[n+1];
         
         for(int k = 1; k <= n; k++){
            if(Math.abs(h[k]) > subsums[k] || (subsums[k]-h[k]) % 2 == 1){
               fail = true;
               break;
            }
            sad[k] = (subsums[k]-h[k])/2;
            if(sad[k] < 0 || sad[k] > subsums[k]){
               fail = true;
               break;
            }
         }
         
         if(fail || !check()){
            out.println("NO");
         } else {
            out.println("YES");
         }
         
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(){
      for(int k = 1; k < parents.length; k++){
         //get subtree sum
         if(k != 1 && adj.get(k).size() == 1) continue;
         int sum = 0;
         for(int nei : adj.get(k)){
            if(nei == parents[k]) continue;
            sum += sad[nei];
         }
         if(sum < sad[k]-p[k]) return false;
      }
      return true;
   }
   
   public static void dfs1(int v, int par){
      parents[v] = par;
      
      subsums[v] = p[v];
      for(int nei : adj.get(v)){
         if(nei == par) continue;
         dfs1(nei,v);
         subsums[v]+=subsums[nei];
      }
   }
   
      
}