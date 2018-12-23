//make sure to make new file!
import java.io.*;
import java.util.*;

public class fireworks{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= n1; q++){
         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int min = Integer.MAX_VALUE;
         int max = 0;
         int mini = 0;
         int maxi = 0;
         for(int k = 1; k <= n; k++){
            int i = Integer.parseInt(st.nextToken());
            if(i < min){
               min = i;
               mini = k;
            }
            if( i > max){
               max = i;
               maxi = k;
            }
         }
         
         out.println("Scenario #" + q + ":");
         out.println("Highest Firework: " + maxi);
         out.println("Earliest Firework: " + mini);
         out.println();
      }
            
            
             
      
      
      
      out.close();
   }
   
}