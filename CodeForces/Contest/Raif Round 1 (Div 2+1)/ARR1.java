//make sure to make new file!
import java.io.*;
import java.util.*;

public class ARR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
         
         if(x1==x2){
            out.println(Math.abs(y1-y2));
         } else if(y1==y2){
            out.println(Math.abs(x2-x1));
         } else {
            out.println(Math.abs(y1-y2) + Math.abs(x2-x1) + 2);
         }

      }
      
      
      
      
      out.close();
   }
   
      
}