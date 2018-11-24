//make sure to make new file!
import java.io.*;
import java.util.*;

public class B512{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int q = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         
         if(y<=x+d && y >= x-d && y >= -x+d && y <= -x+2*n-d) out.println("YES");
         else out.println("NO");
         
      }
            
      
      out.close();
   }
   
}