//make sure to make new file!
import java.io.*;
import java.util.*;

public class A730{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long m = Long.parseLong(st.nextToken());
      
         if(n == m){
            out.println("0 0");
         } else {
            long a1 = Math.abs(n-m);
            long a2 = Math.min(n%a1,a1-(n%a1));
            out.println(a1 + " " + a2);
         }
      }
      
      
      
      
      out.close();
   }
   
      
}