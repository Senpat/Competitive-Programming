//make sure to make new file!
import java.io.*;
import java.util.*;

public class A774{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long s = Long.parseLong(st.nextToken());
         
         long answer = s/(n*n);
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}