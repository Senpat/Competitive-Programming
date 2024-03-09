//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int MAXSIZE = 100;
      int THRESH = 12;
      
      int n = Integer.parseInt(f.readLine());
      long nl = (long)n;
      
      long target = ((nl+1)/2L) * ((nl+1)/2L);
      if(n%2 == 0) target += nl/2L;
      
      
      if(n <= THRESH){
         //do bitmask dp
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int pn = (1 << n);
         for(int mask = 0; mask < pn; mask++){
            long ans = 0L;
            for(int k = 0; k < n; k++){
               int sum = 0;
               for(int j = k; j < n; j++){
                  sum += ((mask>>j)&1);
                  if(sum%2 == 1){
                     ans++;
                  }
               }
               
            }
            
            if(ans > target){
               out.println("FOUND LARGE CASE: " + mask);
               break;
            } else if(ans == target){
               answer.add(mask);
               if(answer.size() >= MAXSIZE) break;
            }
         }
         
         out.println(answer.size());
         for(int mask : answer){
            StringJoiner sj = new StringJoiner("");
            for(int k = n-1; k >= 0; k--){
               if(((mask>>k)&1) == 0) sj.add("b");
               else sj.add("r");
            }
            out.println(sj.toString());
         }
         
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}