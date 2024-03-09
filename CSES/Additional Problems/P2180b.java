//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2180b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long [][] array = new long[2][n];
      for(int k = 0; k < 2; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            array[k][j] = Long.parseLong(st.nextToken())-1;
         }
      }
      
      long top = 0L;
      long bot = 0L;
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         top += array[0][k];
         bot += array[1][k];
         
         if((top < 0 && bot > 0) || (top > 0 && bot < 0)){
            long diff = Math.min(Math.abs(top),Math.abs(bot));
            answer += diff;
            
            if(top < 0){
               top += diff;
               bot -= diff;
            } else {
               top -= diff;
               bot += diff;
            }
         }
         
         answer += Math.abs(top) + Math.abs(bot);
      }
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}