//make sure to make new file!
import java.io.*;
import java.util.*;

public class APR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(a == n && b == n){
            out.println("Yes");
         } else if(a+b <= n-2){
            out.println("Yes");
         } else {
            out.println("No");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}