//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class B869c{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static ArrayList<Integer> path;
   public static boolean[] seen;
   public static boolean found;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         ArrayList<Edge> answer = new ArrayList<Edge>();
         found = false;
         for(int k = 1; k <= n; k++){
            //see if k can be a special graph
            if(adj.get(k).size() < 4) continue;
            
            seen = new boolean[n+1];
            path = new ArrayList<Integer>();
            
            findcycle(k,-1,k);
            
            if(!found) continue;
            
            //found a cycle, and it's in path
            //path contains path of cycle, not including k and beginning and end
            //get two other neighbors
            int nei1 = -1;
            int nei2 = -1;
            for(int nei : adj.get(k)){
               if(nei != path.get(0) && nei != path.get(path.size()-1)){
                  if(nei1 == -1) nei1 = nei;
                  else{
                     nei2 = nei;
                     break;
                  }
               }
            }
            
            //see if nei1 or nei2 makes cycle smaller
            answer.add(new Edge(k,path.get(0)));
            for(int p = 0; p < path.size()-1; p++){
               answer.add(new Edge(path.get(p),path.get(p+1)));
               if(path.get(p+1) == nei1 || path.get(p+1) == nei2){
                  break;
               }
            }
            
            answer.add(new Edge(k,nei1));
            answer.add(new Edge(k,nei2));
            answer.add(new Edge(path.get(path.size()-1),k));
            
            StringJoiner sj = new StringJoiner("\n");
            sj.add("YES");
            sj.add("" + answer.size());
            for(Edge e : answer){
               sj.add("" + e);
            }
            out.println(sj.toString());
            
            break;
         }
         
         if(!found){
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
   public static void findcycle(int v,int p,int start){
      if(found) return;
      seen[v] = true;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         if(nei == start){
            //found cycle
            found = true;
            return;
         }
         
         if(!seen[nei]){
            path.add(nei);
            findcycle(nei,v,start);
            if(found) return;
            path.remove(path.size()-1);
         }
      }
   }
   
   public static class Edge{
      int a;
      int b;
      public Edge(int c, int d){
         a = c;
         b = d;
      }
      
      public String toString(){
         return a + " " + b;
      }
   }
      
}