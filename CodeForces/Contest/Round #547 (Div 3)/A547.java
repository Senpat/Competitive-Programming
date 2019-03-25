//make sure to make new file!
import java.io.*;
import java.util.*;

public class A547{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(m%n!=0){
         out.println("-1");
         out.close();
         System.exit(0);
      }
      
      int need = m/n;
      int count = 0;
      while(need%2==0){
         need/=2;
         count++;
      }
      while(need>1){
         need/=3;
         count++;
         if(need%3 != 0 && need > 1){
            out.println("-1");
            out.close();
            System.exit(0);
         }
      }
      
      out.println(count);

      
      
      
      
      out.close();
   }
   
      
}