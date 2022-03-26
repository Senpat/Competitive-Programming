//make sure to make new file!
import java.io.*;
import java.util.*;

public class D777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long x = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         if(x % (d*d) != 0){
            out.println("NO");
            continue;
         } else {
            long div = x/(d*d);
            
            int found = 0;
            
            while(found < 2){
               //find two factors of div that aren't divisible by d
               
               for(long k = 1; k*k <= div; k++){
                  if(div % k == 0){
                     if(k % d == 0 || (div/k)%d == 0) continue;
                     
                     found++;
                     if(found >= 2) break;
                  }
               }
               
               
               
               if(div % d == 0){
                  div/=d;
               } else {
                  break;
               }
            }
            
            if(found >= 2){
               out.println("YES");
            } else {
               out.println("NO");
            }
         }

      }
      
      
      
      
      out.close();
   }
   
      
}