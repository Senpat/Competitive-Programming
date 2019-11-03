//make sure to make new file!
import java.io.*;
import java.util.*;

public class E565{

   public static ArrayList<ArrayList<Integer>> adj;
   public static HashSet<Integer> answer;
   public static int[] depths;
   public static HashSet<Integer> seen;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
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
         
         seen = new HashSet<Integer>();
         answer = new HashSet<Integer>();
         int start = 1;
         bfs(start);
         
         while(answer.size() > n/2){
            seen = new HashSet<Integer>();
            answer = new HashSet<Integer>();
            bfs(++start);
         }
         
         out.println(answer.size());
         for(int i : answer){
            out.print(i + " ");
         }
         out.println();
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static void bfs(int v){
      Queue<State> q = new LinkedList<State>();
      //seen.add(v);
      q.add(new State(v,0));
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         if(seen.contains(s.v)) continue;
         seen.add(s.v); 
         
         if(s.d % 3 == 0 && checksur(s.v)){
            answer.add(s.v);
         }
         if(s.d % 3 == 2 && checksurseen(s.v) && checksur(s.v)){
            answer.add(s.v);
         }
         
         for(int nei : adj.get(s.v)){
            //if(seen.contains(nei)) continue;
            q.add(new State(nei,s.d+1));
            //seen.add(nei);
         }
      }
   }
   
   public static boolean checksurseen(int v){
      for(int nei : adj.get(v)){
         if(!seen.contains(nei)){
            return false;
         }
      }
      return true;
   }
   
   public static boolean checksur(int v){
      for(int nei : adj.get(v)){
         if(answer.contains(nei)){
            return false;
         }
      }
      return true;
   }
   
   public static class State{
      int v;
      int d;
      
      public State(int a, int b){
         v = a;
         d = b;
      }
      public String toString(){
         return v + " " + d;
      }
      
   }
   
      
}