//make sure to make new file!
import java.io.*;
import java.util.*;

public class BRR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
      
         if(checkn(array)){
            out.println(n);
         } else {
            HashSet<Integer> hset = new HashSet<Integer>();
            
            for(int k = 0; k < n; k++){
               if(array[k] == '-'){
                  hset.add(k);
                  hset.add((k+1+n)%n);
               }
            }
            
            out.println(hset.size());  
            
            
         }
         

      }
      
      
      
      
      out.close();
   }
   
   public static boolean checkn(char[] array){
      boolean clock = true;
      boolean aclock = true;
      
      for(int k = 0; k < array.length; k++){
         if(array[k] == '<') clock = false;
         if(array[k] == '>') aclock = false;
      }
      
      return clock || aclock;
   }
   
      
}