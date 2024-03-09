//make sure to make new file!
import java.io.*;
import java.util.*;

public class A169{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      int[] p = new int[n+1];
      for(int k = 2; k <= n; k++){
         p[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] d = new int[n+1];
      long[] dsum = new long[n+1];
      d[1] = 0;
      dsum[0] = array[1];
      for(int k = 2; k <= n; k++){
         d[k] = d[p[k]]+1;
         dsum[d[k]] += array[k];
      }
      
      boolean found = false;
      for(int k = n; k >= 0; k--){
         if(dsum[k] < 0){
            out.println("-");
            found = true;
            break;
         } else if(dsum[k] > 0){
            out.println("+");
            found = true;
            break;
         }
      }
      
      if(!found){
         out.println("0");
      }
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}