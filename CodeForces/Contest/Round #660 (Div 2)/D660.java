//make sure to make new file!
import java.io.*;
import java.util.*;

public class D660{

   public static ArrayList<ArrayList<Integer>> adj;
   public static Stack<Integer> stk;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] a = new long[n+1];
      int[] b = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         a[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         b[k] = Integer.parseInt(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      for(int k = 1; k <= n; k++){
         if(b[k] == -1) continue;
         adj.get(k).add(b[k]);
      }
      
      seen = new boolean[n+1];
      stk = new Stack<Integer>();
      
      for(int k = 1; k <= n; k++){
         if(seen[k]) continue;
         dfs(k);
      }
      
      ArrayList<Integer> topsort = new ArrayList<Integer>();
      
      while(!stk.isEmpty()){
         topsort.add(stk.pop());
      }
      
      long answer = 0L;
      ArrayList<Integer> order = new ArrayList<Integer>();
   
      boolean[] added = new boolean[n+1];
      for(int k = 0; k < topsort.size(); k++){
         if(a[topsort.get(k)] < 0) continue;
         added[topsort.get(k)] = true;
         answer += a[topsort.get(k)];
         order.add(topsort.get(k));
         if(b[topsort.get(k)] != -1){
            a[b[topsort.get(k)]] += a[topsort.get(k)];
         }
      }
      
      for(int k = topsort.size()-1; k >= 0; k--){
         if(!added[topsort.get(k)]){
            answer += a[topsort.get(k)];
            order.add(topsort.get(k));
         }
      }
      
      out.println(answer);
      StringJoiner sj = new StringJoiner(" ");
      for(int i : order){
         sj.add("" + i);
      }
      out.println(sj.toString());
         
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      seen[v] = true;
      
      for(int nei : adj.get(v)){
         if(seen[nei]) continue;
         dfs(nei);
      }
      
      stk.push(v);
   }
   
      
}