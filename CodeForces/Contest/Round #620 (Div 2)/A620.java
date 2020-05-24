//make sure to make new file!
import java.io.*;
import java.util.*;

public class A620{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         
         if((y-x)%(a+b) == 0){
            out.println((y-x)/(a+b));
         } else {
            out.println(-1);
         }  
         

      }
      
      
      
      
      out.close();
   }
   
      
}