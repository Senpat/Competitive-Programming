//make sure to make new file!
import java.io.*;
import java.util.*;

public class A560{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      int count = 0;
      
      for(int k = n-y; k < n; k++){
         if(k==n-x-1 && s.charAt(k) == '0'){
            count++;
         } else if(k!=n-x-1 && s.charAt(k) == '1'){
            count++;
         }
      }
      
      out.println(count);

      
      
      
      
      out.close();
   }
   
      
}