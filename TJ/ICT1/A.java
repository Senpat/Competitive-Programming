//Lunch Line
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int sum = 0;
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         if(b == 1){
            out.println("YES");
            out.close();
            System.exit(0);
         }
         
         sum+=a;
      
      }
      
      if(sum > 120){
         out.println("NO");
      } else {
         out.println("YES");
      }
      
      
      out.close();
   }
   
}