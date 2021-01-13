//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG12{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] x = new int[n];
         int[] y = new int[n];
         
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            
            x[k] = Integer.parseInt(st.nextToken());
            y[k] = Integer.parseInt(st.nextToken());
         }
         
         boolean found = false;
         for(int k = 0; k < n; k++){
            boolean outside = false;
            for(int j = 0; j < n; j++){
               if(k==j) continue;
               if(Math.abs(x[k]-x[j]) + Math.abs(y[k]-y[j]) > m){
                  outside = true;
                  break;
               }
            }
            if(!outside){
               found = true;
               break;
            }
         }
         
         if(found) out.println(1);
         else out.println(-1);

      }
      
      
      
      
      out.close();
   }
   
      
}