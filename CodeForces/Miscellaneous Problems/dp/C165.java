//Another Problem on Strings
import java.io.*;
import java.util.*;

public class C165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int m = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      int n = s.length();
      
      //edge case
      if(m == 0){
         long answer = 0L;
         long curstreak = 0L;
         for(int k = 0; k < n; k++){
            if(s.charAt(k) == '0') curstreak++;
            else{
               answer += curstreak * (curstreak+1L) / 2L;
               curstreak = 0L;
            }
         }
         answer += curstreak * (curstreak+1L) / 2L;
         out.println(answer);
         out.close();
         return;
      }
      
      int[] array = new int[n+1];
      array[0] = 0;
      
      HashMap<Integer,Long> map = new HashMap<Integer,Long>();
      
      //map.put(0,1);
      
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == '1'){
            array[k+1] = array[k]+1;
         } else {
            array[k+1] = array[k];
         }
         
         if(!map.containsKey(array[k+1])){
            map.put(array[k+1],1L);
         } else {
            map.put(array[k+1],map.get(array[k+1])+1L);
         }
      }
      
      long answer = 0L;
      
      for(int k = 0; k < array.length-1; k++){
         if(map.containsKey(array[k]+m)) answer+=map.get(array[k]+m);
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
}