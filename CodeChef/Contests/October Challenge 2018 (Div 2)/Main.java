//1 CHSERVE
import java.io.*;
import java.util.*;

public class Main{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < n; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int p1 = Integer.parseInt(st.nextToken());
         int p2 = Integer.parseInt(st.nextToken());
         int i = Integer.parseInt(st.nextToken());
         
         if(((p1+p2)/i) % 2 == 1){
            out.println("COOK");
         } else {
            out.println("CHEF");
         }
         
         
      }
      
      out.close();
   }
   
}