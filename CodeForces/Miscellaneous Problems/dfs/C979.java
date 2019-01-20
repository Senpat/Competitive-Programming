//Kuro and Walking Route
import java.io.*;
import java.util.*;

public class C979{

   public static int f,b,fnei,bnei;
   public static long n;
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader fi = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(fi.readLine());
      
      n = Long.parseLong(st.nextToken());
      f = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>((int)n+1);
      
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n-1; k++){
         
         st = new StringTokenizer(fi.readLine());
         
         int q = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         adj.get(q).add(w);
         adj.get(w).add(q);
      }
         
         
      Queue<State> q = new LinkedList<State>();
      HashSet<Integer> seen = new HashSet<Integer>();
      
      seen.add(f);
      for(int nei : adj.get(f)){
         q.add(new State(nei,nei));
         seen.add(nei);
      }
      
      fnei = -1;                    //direction of b from f
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(cur.v == b){
            fnei = cur.child;
            break;
         }
         
         for(int nei : adj.get(cur.v)){
            if(seen.contains(nei)) 
               continue;
            seen.add(nei);
            q.add(new State(nei,cur.child));
         }
      }
      
      q = new LinkedList<State>();
      seen = new HashSet<Integer>();
      
      seen.add(b);
      for(int nei : adj.get(b)){
         q.add(new State(nei,nei));
         seen.add(nei);
      }
      
      bnei = -1;                    //direction of f from b
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(cur.v == f){
            bnei = cur.child;
            break;
         }
         
         for(int nei : adj.get(cur.v)){
            if(seen.contains(nei)) 
               continue;
            seen.add(nei);
            q.add(new State(nei,cur.child));
         }
      }
      
      
      long fcount = fsum(f,-1);
      long bcount = bsum(b,-1);
         
      long answer = n*(n-1) - fcount*bcount;
      
      out.println(answer);
         
      out.close();
   }
   
   public static long bsum(int v, int p){
      long count = 1L;
      
      for(int nei : adj.get(v)){
         if(nei == p || nei == bnei) continue;
         count+=bsum(nei,v);
      }
      
      return count;
   }
   
   public static long fsum(int v, int p){
      long count = 1;
      
      for(int nei : adj.get(v)){
         if(nei == p || nei == fnei) continue;
         count+=fsum(nei,v);
      }
      
      return count;
   }
   
   public static class State{
      int v;
      int child;                          //child of f or b that led to v
      public State(int a, int c){
         v = a;
         child = c;
      }
   }
   
}