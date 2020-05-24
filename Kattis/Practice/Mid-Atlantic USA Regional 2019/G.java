//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++)adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      Queue<State> q = new LinkedList<State>();
      q.add(new State(1,0));
      boolean[] seen = new boolean[n+1];
      seen[1] = true;
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         if(s.v == n){
            out.println(s.d-1);
            break;
         }
         
         for(int nei : adj.get(s.v)){
            if(!seen[nei]){
               q.add(new State(nei,s.d+1));
               seen[nei] = true;
            }
         }
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State{
      int v;
      int d;
      public State(int a, int b){
         v = a;
         d = b;
      }
   }
   
      
}