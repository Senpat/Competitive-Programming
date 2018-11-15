//Edge Deletion
//tutorial
import java.io.*;
import java.util.*;

public class D1076{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Edge2>> adj = new ArrayList<ArrayList<Edge2>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge2>());
      
      for(int k = 1; k <= m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge2(b,w,k);
         adj.get(b).add(new Edge2(a,w,k);
         
         
      }
      
      
      
      
      
      out.close();
   }
   
   
   public static class Edge2{
      int b;
      int w;
      int index;
      public Edge(int x, int c,int v){
         b = x;
         w = c;
         index = v;
      }
   }
   
   public static class Edge{
      int a;
      int b;
      int w;
      public Edge(int z, int x, int c){
         a = z;
         b = x;
         w = c;
      }
   }
   
   
}