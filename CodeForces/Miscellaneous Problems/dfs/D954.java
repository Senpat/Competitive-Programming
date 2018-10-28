//Fight Against Traffic
//semi-t
import java.io.*;
import java.util.*;

public class D954{

   public static ArrayList<HashSet<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<HashSet<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      int[] dt = new int[n+1];
      int[] ds = new int[n+1];
      
      Arrays.fill(dt,Integer.MAX_VALUE);
      Arrays.fill(ds,Integer.MAX_VALUE);
      
      // dt[t] = 0;
      // ds[s] = 0;    
      
      boolean[] seen = new boolean[n+1];
      
      
      //fill dt
      Queue<State> q = new LinkedList<State>();
      q.add(new State(t,0));
      //seen[t] = true;
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(dt[cur.v] > cur.level){
            dt[cur.v] = cur.level;
            for(int nei : adj.get(cur.v)){
               q.add(new State(nei,cur.level+1));
            }
         }
      }
      
      
      //fill ds
      q = new LinkedList<State>();
      q.add(new State(s,0));
      //seen[t] = true;
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(ds[cur.v] > cur.level){
            ds[cur.v] = cur.level;
            for(int nei : adj.get(cur.v)){
               q.add(new State(nei,cur.level+1));
            }
         }
      }
      
      int D = ds[t];
      
      int answer = 0;
      
      for(int k = 1; k <= n; k++){
         for(int j = k+1; j <= n; j++){
            if(!adj.get(k).contains(j) && (ds[k] + dt[j] + 1) >= D && (ds[j] + dt[k] + 1) >= D){
               answer++;
            }
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
   public static class State{
      int v;
      int level;
      
      public State(int a, int b){
         v = a;
         level = b;
      }
   }
   
}