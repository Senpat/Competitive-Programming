//Shashlik Cooking
import java.io.*;
import java.util.*;

public class B507{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int flip = 2*m+1;
      
      int minflips = n/flip;
      
      if(n<=m){
         out.println(1);
         out.println(1);
      } else if( n<= flip){
         out.println(1);
         out.println(1+m);
      } else if( n<=flip*2){
         out.println(2);
         out.println(1 + " " + (1+flip));
      } else {
         if(n%flip == 0){
            out.println(n/flip);
            int cur = m+1;
            out.print(cur + " ");
            for(int k = 1; k < n/flip; k++){
               cur += flip;
               out.print(cur + " ");
            }
         } else {
            out.println(n/flip+1);
            int cur = 1;
            out.print(cur + " ");
            for(int k = 1; k <= n/flip; k++){
               cur += flip;
               out.print(cur + " ");
            }
         }
      }
      
      
      out.close();
   }
   
}