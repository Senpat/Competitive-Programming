//make sure to make new file!
import java.io.*;
import java.util.*;

public class A79{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         if(a > b+c+1 || b > c+a+1 || c > a+b+1){
            out.println("No");
         } else {
            out.println("Yes");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}