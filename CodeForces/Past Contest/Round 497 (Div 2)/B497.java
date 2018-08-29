//Turn the Rectangles
import java.io.*;
import java.util.*;

public class B497{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int cur = Integer.MAX_VALUE;
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(Math.min(a,b)>cur){
            out.println("NO");
            out.close();
            System.exit(0);
         }
         if(Math.max(a,b)<=cur){
            cur = Math.max(a,b);
         } else {
            cur = Math.min(a,b);
         }
      }
      out.println("YES");
      out.close();
   }
}