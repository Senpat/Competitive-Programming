//make sure to make new file!
import java.io.*;
import java.util.*;

public class E519{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] x = new long[n+1];
      long[] y = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
         x[k] = Long.parseLong(st.nextToken());
         y[k] = Long.parseLong(st.nextToken());
      }
      
      ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine()); 
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         //adj.get(b).add(a);
      }
      
      long[] answer = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         for(int j = k+1; j <= n; j++){
            if(!adj.get(k).contains(j)){
               answer[k]+=Math.min(x[k]+y[j],x[j]+y[k]);
               answer[j]+=Math.min(x[k]+y[j],x[j]+y[k]);
            }
         }
      }
      
      for(int k = 1; k <= n; k++){
         out.print(answer[k] + " ");
      }
      
      out.close();
   }
   
}