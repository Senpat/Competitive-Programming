//Multihedgehog
import java.io.*;
import java.util.*;

public class B1067{

   public static ArrayList<ArrayList<Integer>> adj;
   public static HashSet<Integer> bad;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      bad = new HashSet<Integer>();
      
      int start = 1;
      for(int k = 1; k <= n; k++){
         if(adj.get(k).size() == 1){
            bad.add(k);
            if(k == start){
               start++;
            }
         }
      }
      
      //find diameter
      Queue<State> q = new LinkedList<State>();
      q.add(new State(start,-1,0));
      int maxlevel = 0;
      int farthest = start;
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(cur.level > maxlevel){
            farthest = cur.v;
            maxlevel = cur.level;
         }
         
         for(int nei : adj.get(cur.v)){
            if(nei == cur.parent || bad.contains(nei)) continue;
            q.add(new State(nei,cur.v,cur.level+1));
         }
      }
      
      q = new LinkedList<State>();
      q.add(new State(farthest,-1,0));
      int maxdis = 0;
      center = farthest;
      while(!q.isEmpty()){
         State cur = q.poll();
         
         maxdis = Math.max(maxdis,cur.level);
         center = cur.v;
         for(int nei : adj.get(cur.v)){
            if(nei == cur.parent || bad.contains(nei)) continue;
            q.add(new State(nei,cur.v,cur.level+1));
         }
      }
      
      if(maxdis == m){
         out.println("YES");
      } else {
         out.println("NO");
      }
      
      
      
      
      
      out.close();
   }
   
   public static class State{
      int v;
      int parent;
      int level;
      public State(int a, int b, int c){
         v = a;
         parent = b;
         level = c;
      }
   }
   
}