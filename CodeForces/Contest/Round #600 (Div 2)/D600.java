//make sure to make new file!
import java.io.*;
import java.util.*;

public class D600{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int min;
   public static int max;
   public static HashSet<Integer> seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      PriorityQueue<Pair> pairs = new PriorityQueue<Pair>();
      seen = new HashSet<Integer>();
      for(int k = 1; k <= n; k++){
         if(seen.contains(k)) continue;
         min = k;
         max = k;
         seen.add(k);
         dfs(k);
         //out.println(min + " " + max);
         pairs.add(new Pair(min,max));
      }
      
      int answer = 0;
      
      Pair first = pairs.poll();
      int curmax = first.b;
      int size = 1;
      while(!pairs.isEmpty()){
         Pair cur = pairs.poll();
         if(cur.a < curmax){
            curmax = Math.max(curmax,cur.b);
            size++;
         } else {
            curmax = cur.b;
            answer += size-1;
            size = 1;
         }
      }
      answer += size-1;
      
      out.println(answer);
         
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      max = Math.max(max,v);
      min = Math.min(min,v);
      
      for(int nei : adj.get(v)){
         if(seen.contains(nei)) continue;
         seen.add(nei);
         dfs(nei);
      }
   }
         
   
   
   
   
   public static class Pair implements Comparable<Pair>{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
      public int compareTo(Pair p){
         return a-p.a;
      }
   }
   
      
}