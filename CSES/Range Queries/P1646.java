//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1646{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      long[] psums = new long[n+1];
      psums[0] = 0L;
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         psums[k+1] = psums[k] + array[k];
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         out.println(psums[r+1]-psums[l]);
         
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}