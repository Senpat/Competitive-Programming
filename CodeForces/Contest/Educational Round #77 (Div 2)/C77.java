//make sure to make new file!
import java.io.*;
import java.util.*;

public class C77{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int k = Integer.parseInt(st.nextToken());
         
         int r = Math.min(x,y);
         int b = Math.max(x,y);
         
         int d = b/r;
         
         if(b%r == 0) d--;
         else{
            if(r%(b%r) != 0) d++; 
          }
         
         if(d < k){
            out.println("OBEY");
         } else {
            out.println("REBEL");
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}