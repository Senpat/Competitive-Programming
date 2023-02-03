/*
TASK: comehome
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class comehome{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] adj = new int[52][52];
      
      for(int k = 0; k < 52; k++) Arrays.fill(adj[k],Integer.MAX_VALUE);
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = ctoi(st.nextToken().charAt(0));
         int b = ctoi(st.nextToken().charAt(0));
         
         int w = Integer.parseInt(st.nextToken());
         
         adj[a][b] = Math.min(adj[a][b],w);
         adj[b][a] = Math.min(adj[b][a],w);
      }
      
      int[] dist = new int[52];
      Arrays.fill(dist,Integer.MAX_VALUE);
      dist[51] = 0;
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      pq.add(new State(51,0));
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(dist[s.v] != s.w) continue;
         
         for(int nei = 0; nei < 52; nei++){
            if(adj[s.v][nei] == Integer.MAX_VALUE) continue;
            
            int newdist = dist[s.v] + adj[s.v][nei];
            if(dist[nei] > newdist){
               dist[nei] = newdist;
               pq.add(new State(nei,newdist));
            }
         }
               
      }
      
      int minch = 26;
      for(int k = 26; k <= 50; k++){
         if(dist[k] < dist[minch]){
            minch = k;
         }
      }
      
      out.println((char)(minch-26+'A') + " " + dist[minch]);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;
      int w;
      public State(int a, int b){
         v = a;
         w = b;
      }
      
      public int compareTo(State s){
         return w-s.w;
      }
   }
   
   public static int ctoi(char ch){
      if(ch >= 'a') return ch-'a';
      return ch-'A'+26;
   }
   
      
}