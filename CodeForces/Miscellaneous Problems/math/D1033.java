//Divisors
import java.io.*;
import java.util.*;

public class D1033{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine().trim());
      
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(f.readLine().trim());
      }
      
      HashMap<Long,Integer> hmap = new HashMap<Long,Integer>();
      
      for(int k = 0; k < n; k++){
         
         long i = array[k];
         while(i%2L == 0){
            if(hmap.containsKey(2L)){
               hmap.put(2L,hmap.get(2L)+1);
            } else {
               hmap.put(2L,1);
            }
            i/=2L;
         }
         
         for(long j = 3; j*j <= i; j+=2){
            
            while(i%j == 0){
               if(hmap.containsKey(j)){
                  hmap.put(j,hmap.get(j)+1);
               } else {
                  hmap.put(j,1);
               }
               i/=j;
            }
         }
         
         if(i > 1){
            if(hmap.containsKey(i)){
               hmap.put(i,hmap.get(i)+1);
            } else {
               hmap.put(i,1);
            }
         }
      }
      
      long answer = 1L;
      
      for(long i : hmap.keySet()){
         answer = (answer*(hmap.get(i)+1)+MOD)%MOD;
      }
      
      out.println(answer);
      out.flush();
      
      
      
      
      
      
      
      out.close();
   }
   
      
}