//make sure to make new file!
import java.io.*;
import java.util.*;

public class A85{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int prevp = Integer.parseInt(st.nextToken());
         int prevc = Integer.parseInt(st.nextToken());
         
         boolean fail = false;
         
         if(prevp < prevc) fail = true;
         for(int k = 1; k < n; k++){
            st = new StringTokenizer(f.readLine());
      
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            if(p-prevp < c-prevc || c < prevc || p < prevp){
               fail = true;
            }
            prevp = p;
            prevc = c;
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
            
            
      }
      
      
      
      
      out.close();
   }
   
      
}