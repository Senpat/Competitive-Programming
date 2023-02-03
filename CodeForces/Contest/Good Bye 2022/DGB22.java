//make sure to make new file!
import java.io.*;
import java.util.*;

public class DGB22{
   
   public static long MOD = 998244353L;
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static HashSet<Integer> edgesseen;
   public static HashSet<Integer> seen;
   public static HashSet<Integer> repeats;
   public static int c;
   public static int e;
   public static boolean containsrepeat;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         long nl = (long)n;
      
         StringTokenizer sta = new StringTokenizer(f.readLine());
         StringTokenizer stb = new StringTokenizer(f.readLine());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(sta.nextToken());
            b[k] = Integer.parseInt(stb.nextToken());
         }
         
         adj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
         repeats = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            adj.get(a[k]).add(new Edge(b[k],k));
            adj.get(b[k]).add(new Edge(a[k],k));
            if(a[k] == b[k]) repeats.add(a[k]);
         }
         
         boolean fail = false;
         long answer = 1L;
         seen = new HashSet<Integer>();
         for(int k = 1; k <= n; k++){
            if(seen.contains(k)) 
               continue;
            edgesseen = new HashSet<Integer>();
            c = 0;
            e = 0;
            containsrepeat = false;
            dfs(k);
            
            if(e != c){
               fail = true;
               break;
            }
            
            if(containsrepeat){
               answer = (answer * nl + MOD)%MOD;
            } else {
               answer = (answer * 2L + MOD)%MOD;
            }
         }
         
         if(fail){
            out.println(0);
         } else {
            out.println(answer);
         }
         
         
         
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      if(!seen.contains(v)) c++;
      if(repeats.contains(v)) containsrepeat = true;
      seen.add(v);
      
      for(Edge nei : adj.get(v)){
         if(edgesseen.contains(nei.i)) continue;
         e++;
         edgesseen.add(nei.i);
         dfs(nei.to);
      }
   }
      
      
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
      }
   }
      
}