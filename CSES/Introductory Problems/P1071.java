//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1071{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long x = Long.parseLong(st.nextToken());       //row
         long y = Long.parseLong(st.nextToken());       //column
         
         long answer;
         if(x >= y){
            //lower left part 
            long top = x*x;
            long bot = (x-1)*(x-1)+1;
            
            long mid = (bot + top)/2L;
            
            if(x % 2 == 1){
               answer = bot + (y-1);
            } else {
               answer = top - (y-1);
            }
            
            
         } else {
            //upper right part
            long top = y*y;
            long bot = (y-1)*(y-1) + 1;
            
            long mid = (bot + top)/2L;
            
            if(y % 2 == 1){
               answer = top - (x-1);
            } else {
               answer = bot + (x-1);
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}