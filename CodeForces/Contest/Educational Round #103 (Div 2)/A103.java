//make sure to make new file!
import java.io.*;
import java.util.*;

public class A103{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(m < n){
            int newm = (n+m-1)/m*m;
            m = newm;
         }
         
         if(m % n == 0){
            out.println(m/n);
         } else {
            out.println(m/n+1);
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}