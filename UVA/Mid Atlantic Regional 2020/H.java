//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Point[] array = new Point[n+1];
      array[0] = new Point(0,0);                   //starting point
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(f.readLine());
         array[k] = new Point(i,k);
      }
      
      Arrays.sort(array);
      
      //to get x from w
      int[] indexofw = new int[n+1];
      for(int k = 0; k <= n; k++){
         indexofw[array[k].w] = k;
      }
      
      int LOG = 29;
      ArrayList<ArrayList<HashSet<Integer>>> adj = new ArrayList<ArrayList<HashSet<Integer>>>();            //(n+1)*29 matrix of hashsets. weights of numbers you can reach given width and center (index) of array
      //2^(2nd dimension) is total width of range. For 0, it's +/- 0.5, so you can only see itself
      
      for(int k = 0; k <= n; k++){
         ArrayList<HashSet<Integer>> add = new ArrayList<HashSet<Integer>>(LOG);
         for(int j = 0; j < LOG; j++) add.add(new HashSet<Integer>());
         adj.add(add);
      }
      
      //set 0
      for(int k = 0; k <= n; k++){
         adj.get(k).get(0).add(k);
      }
      
      for(int i = 1; i < LOG; i++){
         int dist = (1 << (i-1));
         //full range is +/- dist inclusive
         
         PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
         //set initial
         int r = 0;
         while(r <= n && array[r].x <= array[0].x+dist){
            pq.add(array[r].w);
            r++;
         }
         
         ArrayList<Integer> added = new ArrayList<Integer>();
         while(!pq.isEmpty() && adj.get(0).get(i).size() < m){
            int w = pq.poll();
            if(array[indexofw[w]].x < array[0].x-dist) 
               continue;
            if(array[indexofw[w]].x != 0){
               adj.get(0).get(i).add(w);
            }
            added.add(w);
         }
         
         for(int num : added) pq.add(num);
         
         for(int k = 1; k < n; k++){
            while(r <= n && array[r].x <= array[k].x+dist){
               pq.add(array[r].w);
               r++;
            }
            
            added = new ArrayList<Integer>();
            while(!pq.isEmpty() && adj.get(k).get(i).size() < m){
               int w = pq.poll();
               if(array[indexofw[w]].x < array[k].x-dist) 
                  continue;
               if(array[indexofw[w]].x != 0){
                  adj.get(k).get(i).add(w);
               }
               added.add(w);
            }
            
            for(int num : added) pq.add(num);
         }
      }
      
      //boolean[][] seen = new boolean[n+1][LOG];
      int[][] mindist = new int[n+1][LOG];
      
      for(int k = 0; k <= n; k++) Arrays.fill(mindist[k],Integer.MAX_VALUE);
      
      Queue<State> q = new LinkedList<State>();
      q.add(new State(0,1,0));
      mindist[0][1] = 0;
      //seen[0][1] = true;
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         if(mindist[s.x][s.log] < s.dist) 
            continue;
         
         for(int nei : adj.get(s.x).get(s.log)){
            if(mindist[nei][s.log] <= s.dist+1) 
               continue;
            mindist[nei][s.log] = s.dist+1;
            q.add(new State(nei,s.log,s.dist+1));
         }
         
         if(s.log > 0){
            if(mindist[s.x][s.log-1] > s.dist+1){
               mindist[s.x][s.log-1] = s.dist+1;
               q.add(new State(s.x,s.log-1,s.dist+1));
            }
         }
         
         if(s.log < LOG-1){
            if(mindist[s.x][s.log+1] > s.dist+1){
               mindist[s.x][s.log+1] = s.dist+1;
               q.add(new State(s.x,s.log+1,s.dist+1));
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         int min = Integer.MAX_VALUE;
         for(int j = 0; j < LOG; j++){
            min = Math.min(min,mindist[k][j]);
         }
         if(min == Integer.MAX_VALUE) sj.add("-1");
         else sj.add("" + min);
      }
      
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int x;                           //weight (index) of center
      int log;
      int dist;
      
      public State(int a, int b, int c){
         x = a;
         log = b;
         dist = c;
      }
      
      public int compareTo(State s){
         return dist-s.dist;
      }
   }
   
   public static class Point implements Comparable<Point>{
      int x;
      int w;
      public Point(int a, int b){
         x = a;
         w = b;
      }
      public int compareTo(Point p){
         return x-p.x;
      }
   }
      
}