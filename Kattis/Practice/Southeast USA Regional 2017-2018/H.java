//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{

   public static int n,s,d;

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      s = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      HashSet<Integer> used = new HashSet<Integer>();
      ArrayList<Integer> check = new ArrayList<Integer>();
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int min = Integer.parseInt(st.nextToken());
         int max = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,min,max));
         
         if(!used.contains(min)){
            used.add(min);
            check.add(min);
         }
         
         if(!used.contains(max)){
            used.add(max);
            check.add(max);
         }
      }
      
      Collections.sort(check);
      
      int answer = 0;
      
      //try less than smallest one
      if(check.get(0) > 1){
         if(check(1)) answer += check.get(0)-1;
      }
      
      for(int k = 0; k < check.size(); k++){
         if(check(check.get(k))) answer++;
         if(k+1 < check.size() && check.get(k+1) > check.get(k)+1){
            if(check(check.get(k)+1)) answer += (check.get(k+1)-1) - (check.get(k)+1) + 1;
         }
      }
      
      //try bigger than biggest one
      if(check.get(check.size()-1) < x){
         if(check(check.get(check.size()-1)+1)) answer += x-(check.get(check.size()-1)+1)+1;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int x){
      
      for(Edge e : adj.get(v)){
         if(seen[e.to]) continue;
         if(x < e.min || x > e.max) continue;
         seen[e.to] = true;
         dfs(e.to,x);
      }
   }
   
   public static boolean check(int x){
      seen = new boolean[n+1];
      seen[s] = true;
      dfs(s,x);
      
      return seen[d];
   }
   
   public static class Edge{
      int to;
      int min;
      int max;
      public Edge(int a, int b, int c){
         to = a;
         min = b;
         max = c;
      }
   } 
}