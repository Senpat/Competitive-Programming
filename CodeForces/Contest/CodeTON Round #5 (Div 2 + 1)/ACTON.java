//make sure to make new file!
import java.io.*;
import java.util.*;

public class ACTON{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long sum1 = 0L;
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            sum1 += Long.parseLong(st.nextToken());
         }
         
         long sum2 = 0L;
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            sum2 += Long.parseLong(st.nextToken());
         }
         
         if(sum1 > sum2){
            out.println("Tsondu");
         } else if(sum1 < sum2){
            out.println("Tenzing");
         } else {
            out.println("Draw");
         }
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}