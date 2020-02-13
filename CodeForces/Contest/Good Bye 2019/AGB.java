//make sure to make new file!
import java.io.*;
import java.util.*;

public class AGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         int maxa = 0;
         int maxb = 0;
          
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < a; k++){
            int i = Integer.parseInt(st.nextToken());
            maxa = Math.max(maxa,i);
         }
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < b; k++){
            int i = Integer.parseInt(st.nextToken());
            maxb = Math.max(maxb,i);
         }
         
         if(maxa > maxb){
            out.println("YES");
         } else {
            out.println("NO");
         }
         
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}