//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         answer += Long.parseLong(st.nextToken());
         answer += m*Long.parseLong(st.nextToken());
      }
      
      out.println(answer); 
      
      
      
      
      out.close();
   }
   
      
}