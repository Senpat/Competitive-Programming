//make sure to make new file!
import java.io.*;
import java.util.*;

public class E519b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] x = new long[n+1];
      long[] y = new long[n+1];
      
      long[] answer = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
         x[k] = Long.parseLong(st.nextToken());
         y[k] = Long.parseLong(st.nextToken());
         
         for(int j = 1; j < k; j++){
            
            answer[k]+=Math.min(x[k]+y[j],x[j]+y[k]);
            answer[j]+=Math.min(x[k]+y[j],x[j]+y[k]);
            
         }
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine()); 
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         answer[a]-=Math.min(x[a]+y[b],x[b]+y[a]);
         answer[b]-=Math.min(x[a]+y[b],x[b]+y[a]);
         
         
      }
      
      for(int k = 1; k <= n; k++){
         out.print(answer[k] + " ");
      }
      
      out.close();
   }
   
}