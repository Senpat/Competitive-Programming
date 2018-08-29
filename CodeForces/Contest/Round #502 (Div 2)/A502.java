//The Rank
import java.io.*;
import java.util.*;

public class A502{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int i = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
      
      int count = 1;
      for(int k = 1; k < n; k++){
         st = new StringTokenizer(f.readLine());
         if(i < Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())){
            count++;
         }
      }
      
      out.println(count);
      
      out.close();
   }
   
}