//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG23{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[] scores;
   
   public static Pair dfs(int v, int p, int x){
      
      ArrayList<Pair> pairs = new ArrayList<Pair>();
      int numchildren = adj.get(v).size();
      if(v != 1) numchildren--;
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         
         Pair pair = dfs(nei,v,x/numchildren);
         
         pairs.add(pair);
      }
      
      Collections.sort(pairs);
      long sum0 = scores[v]*(long)x;
      long sum1 = scores[v]*(long)(x+1);
      if(numchildren > 0){
         int thresh = x%numchildren;
         for(int k = 0; k < pairs.size(); k++){
            if(k < thresh){
               sum0 += pairs.get(k).a1;
            } else {
               sum0 += pairs.get(k).a0;
            }
         
            if(k < thresh+1){
               sum1 += pairs.get(k).a1;
            } else {
               sum1 += pairs.get(k).a0;
            }
         }
      }
      //System.out.println(v + " " + sum0 + " " + sum1);
      return new Pair(sum0,sum1);
   }
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         st = new StringTokenizer(f.readLine());
         for(int k = 2; k <= n; k++){
            int i = Integer.parseInt(st.nextToken());
            adj.get(k).add(i);
            adj.get(i).add(k);
         }
         
         st = new StringTokenizer(f.readLine());
         
         scores = new long[n+1];
         
         for(int k = 1; k <= n; k++){
            scores[k] = Long.parseLong(st.nextToken());
         }
         
         Pair p = dfs(1,-1,m);
         
         out.println(p.a0);
         
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      long a0;
      long a1;
      long d;
      public Pair(long a, long b){
         a0 = a;
         a1 = b;
         d = a1-a0;
      }
      
      //sort by decreasing a1-a0
      public int compareTo(Pair p){
         if(d == p.d){
            if(a0 > p.a0) 
               return -1;
            if(a0 < p.a0) 
               return 1;
            return 0;
         }
         
         if(d > p.d) 
            return -1;
         if(d < p.d) 
            return 1;
         return 0;
      }
   }
   
}