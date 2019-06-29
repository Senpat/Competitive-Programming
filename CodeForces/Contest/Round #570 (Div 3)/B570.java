//make sure to make new file!
import java.io.*;
import java.util.*;

public class B570{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int min = Integer.MAX_VALUE;
         int max = 0;
         
         st = new StringTokenizer(f.readLine());
         
         for(int k = 0; k < n; k++){
            int i = Integer.parseInt(st.nextToken());
            min = Math.min(min,i);
            max = Math.max(max,i);
         }
         
         int answer = min + m;
         if(max-answer > m){
            out.println(-1);
         } else {
            out.println(answer);
         }
      
      }
      
      
      
      
      out.close();
   }
   
      
}