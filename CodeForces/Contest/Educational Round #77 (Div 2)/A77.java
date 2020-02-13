//make sure to make new file!
import java.io.*;
import java.util.*;

public class A77{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(n >= m){
            out.println(m);
         } else {
            int a = m/n;
            int b = m%n;
            
            int answer = a*a*(n-b) + (a+1)*(a+1)*(b);
            out.println(answer);
         }
            

      }
      
      
      
      
      out.close();
   }
   
      
}