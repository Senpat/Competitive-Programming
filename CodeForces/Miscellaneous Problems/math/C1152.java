//make sure to make new file!
import java.io.*;
import java.util.*;

public class C1152{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int a = Math.min(n,m);
      int b = Math.max(n,m);
      
      int dif = b-a;
      
      if(dif <= 1 || b%dif == 0){
         out.println("0");
         out.close();
         return;
      }
      
      int answer = (b/dif+1)*dif-b;
      out.println(answer);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}