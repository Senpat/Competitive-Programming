//make sure to make new file!
import java.io.*;
import java.util.*;

public class DDTb{
   
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[][] d = new long[n+1][n+1];
         
         for(int k = 1; k <= n; k++){
            for(int j = 1; j <= n; j++){
               d[k][j] = 1000000000000L;
            }
            d[k][k] = 0L;
         }
         
         Edge[] edges = new Edge[m];
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            
            edges[k] = new Edge(a,b,w);
            
            d[a][b] = Math.min(d[a][b],1);
            d[b][a] = Math.min(d[b][a],1);
         }
         
         
         for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
               for (int j = 1; j <= n; ++j) {
                  d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]); 
               }
            }
         }
      
         long answer = Long.MAX_VALUE;
         for(int k = 0; k < m; k++){
            //cout << edges[k].a << " " << edges[k].b << " " << edges[k].w << endl;
            long move = (long)Math.min(d[1][edges[k].a]+d[n][edges[k].b],d[1][edges[k].b]+d[n][edges[k].a])+1;
            out.println(edges[k].a + " " + edges[k].b + " " + move);
            answer = Math.min(answer,move*edges[k].w);
         }
      
         out.println(answer);
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Edge{
      int a;
      int b;
      long w;
      public Edge(int c, int d, long e){
         a = c;
         b = d;
         w = e;
      }
   }
   
      
}