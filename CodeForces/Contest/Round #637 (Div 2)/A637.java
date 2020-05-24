//make sure to make new file!
import java.io.*;
import java.util.*;

public class A637{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         int min = n*(a-b);
         int max = n*(a+b);
         
         if(max < c-d || min > c+d){
            out.println("No");
         } else {
            out.println("Yes");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}