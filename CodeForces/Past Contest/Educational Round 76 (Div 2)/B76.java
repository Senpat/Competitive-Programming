//make sure to make new file!
import java.io.*;
import java.util.*;

public class B76{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(n < m && n <= 3 && (n!=2 || m != 3)){
            out.println("NO");
         } else {
            out.println("YES");
         } 

      }
      
      
      
      
      out.close();
   }
   
      
}