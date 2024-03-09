//make sure to make new file!
import java.io.*;
import java.util.*;

public class C165{

   public static int n;
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
      }
      
      int l = 0;
      int r = 2000000005;
      int ans = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(check(mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      out.println(ans);
      
      
      
      
      out.close();
   }
   
   public static int[] color;
   
   public static boolean check(int x){
      
      color = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         if(color[k] == 0){
            color[k] = 1;
            dfs(k,x);
         }
      }
      
      //check for mismatches
      for(int v = 1; v <= n; v++){
         int minsame = Integer.MAX_VALUE;
         int mindiff1 = Integer.MAX_VALUE;
         int mindiff2 = Integer.MAX_VALUE;
         for(Edge e : adj.get(v)){
            if(color[v] == color[e.to]) minsame = Math.min(minsame,e.w);
            else{
               if(e.w < mindiff1){
                  mindiff2 = mindiff1;
                  mindiff1 = e.w;
               } else if(e.w < mindiff2){
                  mindiff2 = e.w;
               }
            }
         }
         
         if(minsame < x || (mindiff2 != Integer.MAX_VALUE && mindiff1 + mindiff2 < x)){
            return false;
         }
      }
      
      return true;
   }
   
   public static void dfs(int v, int x){
      
      for(Edge e : adj.get(v)){
         if(color[e.to] != 0) continue;      //check for mismatches later
         if(e.w >= x) continue;              //could be either color
         color[e.to] = 3-color[v];
         dfs(e.to,x);
      }
      
   }
   
   public static class Edge{
      int to;
      int w;
      public Edge(int a, int b){
         to = a;
         w = b;
      }
   }
      
}