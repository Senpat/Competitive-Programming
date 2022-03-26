//make sure to make new file!
import java.io.*;
import java.util.*;
//div2 B
public class B767d2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());      
         
         if(l==r){
            if(l == 1){
               out.println("NO");
            } else {
               out.println("YES");
            }
         } else {
            //get number of odds
            if(l % 2 == 0) l++;
            if(r % 2 == 0) r--;
            
            int numodds = r/2 - l/2 + 1;
            
            if(x >= numodds) out.println("YES");
            else out.println("NO");
         }
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}