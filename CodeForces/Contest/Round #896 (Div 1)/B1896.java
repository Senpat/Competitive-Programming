//make sure to make new file!
import java.io.*;
import java.util.*;

public class B1896{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int P = 30;
      HashMap<Long,Integer> p2 = new HashMap<Long,Integer>();
      for(int i = 0; i <= P; i++){
         p2.put(1L << i,i);
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         long nl = (long)n;
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long sum = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         if(sum % nl != 0){
            out.println("No");
            continue;
         }
         
         long avg = sum / nl;
         
         int[] pin = new int[P+1];
         int[] pout = new int[P+1];
         
         boolean fail = false;
         
         for(int k = 0; k < n; k++){
            if(avg == array[k]){
               continue;
            }
            
            long diff = Math.abs(avg-array[k]);
            
            boolean found = false;
            for(int i = 0; i <= P; i++){
               long p = (1L << i);
               if(p2.containsKey(diff + p)){
                  found = true;
                  
                  if(avg < array[k]){
                     //less -> in bigger, out smaller
                     pin[p2.get(diff+p)]++;
                     pout[i]++;
                  } else {
                     //more -> in smaller, out bigger
                     pin[i]++;
                     pout[p2.get(diff+p)]++;
                  }
                  
                  break;
               }
            }
            
            if(!found){
               fail = true;
               break;
            }
         }
         
         for(int i = 0; i <= P; i++){
            if(pin[i] != pout[i]){
               fail = true;
            }
         }
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}