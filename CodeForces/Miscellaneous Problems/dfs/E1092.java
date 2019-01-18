//Minimal Diameter Forest
import java.io.*;
import java.util.*;

public class E1092{

   public static ArrayList<ArrayList<Integer>> adj;
   public static boolean[] seen;
   public static int[] dists;
   public static ArrayList<Mid> centers;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
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
      
      //find distance of farthest vertex (and also calculate diameter)
      int diam = 0;
      dists = new int[n+1];
      for(int k = 1; k <= n; k++){
         dists[k] = dist(k);
         diam = Math.max(diam,dists[k]);
      }
      
      //for(int k = 1; k <= n; k++) out.println(k + ": " + dists[k]);
      
      seen = new boolean[n+1];
      
      centers = new ArrayList<Mid>();
      
      for(int k = 1; k <= n; k++){
         if(seen[k]) continue;
         centers.add(findmid(k));
      }
      
      Collections.sort(centers);
      Collections.reverse(centers);
      
      if(centers.size() == 1){
         out.println(diam);
      } else if(centers.size() == 2){
         out.println(Math.max(diam,centers.get(0).len + centers.get(1).len + 1));
         out.println(centers.get(0).index + " " + centers.get(1).index);
      } else {
         out.println(Math.max(diam,centers.get(1).len + centers.get(2).len + 2));
         StringBuilder sb = new StringBuilder("");
         for(int k = 1; k < centers.size(); k++){
            sb.append(centers.get(k).index + " " + centers.get(0).index + "\n");
         }
         out.println(sb.toString());
      }
         
      
      
      
      
      out.close();
   }
   
   public static Mid findmid(int v){
      seen[v] = true;
      
      Mid min = new Mid(dists[v],v);
      Mid temp;
      
      for(int nei : adj.get(v)){
         if(seen[nei]) continue;
         temp = findmid(nei);
         if(temp.len < min.len){
            min = temp.copy();
         }
      }
      
      return min;
   }
         
      
      
   
   public static int dist(int v){
      
   
      Queue<State> q = new LinkedList<State>();
      
      q.add(new State(0,v,-1));
      
      int maxl = 0;
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         maxl = Math.max(maxl,cur.level);
         
         for(int nei : adj.get(cur.index)){
            if(nei == cur.parent) continue;
            q.add(new State(cur.level+1,nei,cur.index));
         }
      }
      
      return maxl;
   }
      
   
   
   
   public static class State{
      int level;
      int index;
      int parent;
      public State(int a, int b , int c){
         level = a;
         index = b;
         parent = c;
      }
   }
   
   public static class Mid implements Comparable<Mid>{
      int len;
      int index;
      public Mid(int a, int b){
         len = a;
         index = b;
      }
      public int compareTo(Mid mi){
         return len-mi.len;
      }
      public Mid copy(){
         return new Mid(this.len,this.index);
      }
   }
   
}