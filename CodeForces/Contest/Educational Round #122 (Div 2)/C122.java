//make sure to make new file!
import java.io.*;
import java.util.*;

public class C122{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long hc = Long.parseLong(st.nextToken());
         long dc = Long.parseLong(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         long hm = Long.parseLong(st.nextToken());
         long dm = Long.parseLong(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
      
         long x = Long.parseLong(st.nextToken());
         long w = Long.parseLong(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         
         boolean found = false;
         
         for(long k = 0; k <= x; k++){
            //spend k on hc, rest on dc
            
            long curhc = hc+k*a;
            long curdc = dc+(x-k)*w;
            
            long ch = (curhc + dm-1)/dm;
            long monster = (hm + curdc-1)/curdc;
            
            if(ch >= monster){
               found = true;
               break;
            }
         }
         
         if(found){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}