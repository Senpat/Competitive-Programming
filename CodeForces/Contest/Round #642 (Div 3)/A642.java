//make sure to make new file!
import java.io.*;
import java.util.*;

public class A642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(n == 1){
            out.println(0);
         } else if(n == 2){
            out.println(m);
         } else {
            out.println(2*m);
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}