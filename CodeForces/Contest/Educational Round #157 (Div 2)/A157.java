//make sure to make new file!
import java.io.*;
import java.util.*;

public class A157{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int z = Integer.parseInt(st.nextToken());
      
         if(x > y){
            out.println(x);
         } else if(y <= x+z){
            out.println(y);
         } else {
            out.println(y + y-(x+z));
         }

      }
      
      
      
      
      out.close();
   }
   
      
}