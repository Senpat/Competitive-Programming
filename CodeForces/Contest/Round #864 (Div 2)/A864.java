//make sure to make new file!
import java.io.*;
import java.util.*;

public class A864{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
      
         if((x1 == 1 || x1 == n) && (y1 == 1 || y1 == m)) out.println(2);
         else if((x2 == 1 || x2 == n) && (y2 == 1 || y2 == m)) out.println(2);
         else if(x1 == 1 || x1 == n || y1 == 1 || y1 == m) out.println(3);
         else if(x2 == 1 || x2 == n || y2 == 1 || y2 == m) out.println(3);
         else out.println(4);

      }
      
      
      
      
      out.close();
   }
   
      
}