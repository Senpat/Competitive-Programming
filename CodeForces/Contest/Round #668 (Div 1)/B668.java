//make sure to make new file!
import java.io.*;
import java.util.*;

public class B668{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int da = Integer.parseInt(st.nextToken());
         int db = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < n-1; k++){
            st = new StringTokenizer(f.readLine());
         
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            adj.get(c).add(d);
            adj.get(d).add(c);
         }
         
         if(db < 2*da+1){
            //out.println("1");
            out.println("Alice");
            continue;
         }
         
         //see if alice can win on first move - find distance between a and b
         int dist = getdist(a,b);
         
         if(dist <= da+1){
            //out.println("2");
            out.println("Alice");
            continue;
         }
         
         //get diameter of tree
         HashSet<Integer> seen = new HashSet<Integer>();
      
         Queue<State> q2 = new LinkedList<State>();
         q2.add(new State(1,1));
         seen.add(1);
      
         int maxdist = 1;
         int maxnode = 1;
      
         while(!q2.isEmpty()){
            State s = q2.poll();
         
            if(s.t > maxdist){
               maxdist = s.t;
               maxnode = s.v;
            }
         
            for(int nei : adj.get(s.v)){
               if(!seen.contains(nei)){
                  seen.add(nei);
                  q2.add(new State(nei,s.t+1));
               }
            }
         }
         
         seen = new HashSet<Integer>();
      
         Queue<State> q3 = new LinkedList<State>();
         q3.add(new State(maxnode,1));
         seen.add(maxnode);
      
         maxdist = 1;
      
         while(!q3.isEmpty()){
            State s = q3.poll();
         
            maxdist = Math.max(maxdist,s.t);
         
            for(int nei : adj.get(s.v)){
               if(!seen.contains(nei)){
                  seen.add(nei);
                  q3.add(new State(nei,s.t+1));
               }
            }
         }
         
         if(maxdist < 2*da+2){
            //out.println("3");
            out.println("Alice");
         } else {
            out.println("Bob");
         }
      
      
      }
      
      
      
      
      out.close();
   }
   
   //gets distance between a and b (number of nodes on simple path between a and b)
   public static int getdist(int a, int b){
   
      HashSet<Integer> seen = new HashSet<Integer>();
      
      Queue<State> q1 = new LinkedList<State>();
      q1.add(new State(a,1));
      seen.add(a);
      
      while(!q1.isEmpty()){
         State s = q1.poll();
         
         if(s.v == b) 
            return s.t;
         
         for(int nei : adj.get(s.v)){
            if(!seen.contains(nei)){
               seen.add(nei);
               q1.add(new State(nei,s.t+1));
            }
         }
      }
      
      return -1;
   
   }
   
   public static class State{
      int v;
      int t;
      
      public State(int a, int b){
         v = a;
         t = b;
      }
   }
   
      
}