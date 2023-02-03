//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG23{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         
         boolean has1 = false;
         for(int k = 0; k < n; k++){
            int i = Integer.parseInt(st.nextToken());
            
            if(i == 1) has1 = true;
         }
         
         if(has1){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}