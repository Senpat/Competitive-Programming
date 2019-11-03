//make sure to make new file!
import java.io.*;
import java.util.*;

public class A582{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int even = 0;
      int odd = 0;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(i%2 == 0) even++;
         else odd++;
      }
      
      int answer = Math.min(even,odd);
      out.println(answer);
      

      
      
      
      
      out.close();
   }
   
      
}