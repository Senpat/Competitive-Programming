//make sure to make new file!
import java.io.*;
import java.util.*;

public class B528{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int mod = 0;
      for(int k = m-1; k >= 1; k--){
         if(n % k == 0){
            mod = k;
            break;
         }
      }
      
      int div = n/mod;
      
      int answer = div * m + mod;
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
}