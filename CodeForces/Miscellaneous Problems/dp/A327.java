//Flipping Game
import java.io.*;
import java.util.*;

public class A327{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int cur = Integer.parseInt(st.nextToken()) == 0 ? 1 : 0;
      int count1 = cur == 1 ? 0 : 1;
      int max = cur;
      
      for(int k = 1; k < n; k++){
         if(Integer.parseInt(st.nextToken()) == 1){
            cur--;
            cur = Math.max(0,cur);
            count1++;
         } else {
            cur++;
            max = Math.max(max,cur);
         }
         
      }
      
      if(count1 == n){
         out.println(count1-1);
      } else {
         out.println(count1+max);
      }
            
      out.close();
   }
   
}