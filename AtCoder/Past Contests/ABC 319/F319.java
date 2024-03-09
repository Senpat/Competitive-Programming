//make sure to make new file!
import java.io.*;
import java.util.*;

public class F319{
   
   public static long MAX = 1000000000L;
   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<Enemy> enemies;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      enemies = new ArrayList<Enemy>(n+1);
      enemies.add(new Enemy(1,-1,-1,-1));       //1-indexed
      enemies.add(new Enemy(1,-1,-1,-1));       //root (no enemy)
      
      ArrayList<Integer> t2 = new ArrayList<Integer>();
      
      int[] parent = new int[n+1];
      
      for(int k = 2; k <= n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int p = Integer.parseInt(st.nextToken());
         int t = Integer.parseInt(st.nextToken());
         long s = Long.parseLong(st.nextToken());
         long g = Long.parseLong(st.nextToken());
         
         parent[k] = p;
         adj.get(k).add(p);
         adj.get(p).add(k);
         
         enemies.add(new Enemy(k,t,s,g));
         
         if(t == 2){
            t2.add(k);
         }
      }
      
      int t2n = t2.size();
      int pn = 1 << t2n;
      
      ArrayList<State> dp = new ArrayList<State>(pn);
      for(int k = 0; k < pn; k++) dp.add(new State(new HashSet<Integer>(),-1));
      
      boolean success = false;
      
      HashSet<Integer> initial_hset = new HashSet<Integer>();
      initial_hset.add(1);
      State initial = new State(initial_hset,1L);
      expand(initial);
      
      dp.set(0,initial);
      
      if(initial.strength > MAX){
         success = true;
      }
      
      for(int mask = 0; mask < pn; mask++){
         if(success) break;
         
         if(dp.get(mask).strength == -1) continue;
         
         for(int k = 0; k < t2n; k++){
            if((mask & (1 << k)) != 0) continue;
            if(!dp.get(mask).nodes.contains(parent[t2.get(k)])) continue;
            State cur = new State(dp.get(mask));
            cur.nodes.add(t2.get(k));
            cur.strength *= enemies.get(t2.get(k)).g;
            if(cur.strength > MAX){
               success = true;
               break;
            }
            
            expand(cur);
            
            if(cur.strength > MAX){
               success = true;
               break;
            }
            
            int newmask = (mask | (1 << k));
            if(cur.strength > dp.get(newmask).strength || (cur.strength == dp.get(newmask).strength && cur.nodes.size() > dp.get(newmask).nodes.size())){
               dp.set(newmask,cur);
            }
         }
      }
      
      if(success || dp.get(pn-1).nodes.size() == n){
         out.println("Yes");
      } else {
         out.println("No");
      }
         
      
      
      
      
      out.close();
   }
   
   public static void expand(State s){
      HashSet<Integer> added = new HashSet<Integer>();
      
      PriorityQueue<Enemy> pq = new PriorityQueue<Enemy>();
      for(int seen : s.nodes){
         for(int nei : adj.get(seen)){
            if(!s.nodes.contains(nei) && !added.contains(nei) && enemies.get(nei).t == 1){
               added.add(nei);
               pq.add(enemies.get(nei));
            }
         }
      }
      
      while(!pq.isEmpty() && pq.peek().s <= s.strength){
         Enemy e = pq.poll();
         s.strength += e.g;
         s.nodes.add(e.v);
         for(int nei : adj.get(e.v)){
            if(!s.nodes.contains(nei) && !added.contains(nei) && enemies.get(nei).t == 1){
               added.add(nei);
               pq.add(enemies.get(nei));
            }
         }
      }
         
      
   }
   
   public static class State{
      HashSet<Integer> nodes;
      long strength;
      
      public State(HashSet<Integer> a, long b){
         nodes = new HashSet<Integer>(a);
         strength = b;
      }
      
      public State(State s){
         nodes = new HashSet<Integer>(s.nodes);
         strength = s.strength;
      }
   }
   
      
   public static class Enemy implements Comparable<Enemy>{
      int v;
      int t;
      long s;
      long g;
      public Enemy(int vp, int a, long b, long c){
         v = vp;
         t = a;
         s = b;
         g = c;
      }
      
      public int compareTo(Enemy e){
         if(s > e.s) return 1;
         if(s < e.s) return -1;
         return 0;
      }
   }
}