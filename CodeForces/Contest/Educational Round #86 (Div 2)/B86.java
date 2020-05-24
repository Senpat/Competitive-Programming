//make sure to make new file!
import java.io.*;
import java.util.*;

public class B86{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         String s = f.readLine();
         int n = s.length();
         
         int[] array = new int[n];
         
         StringJoiner sj = new StringJoiner("");
         HashSet<Integer> hset = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
            hset.add(array[k]);
         }
         
         if(hset.size() == 2){
            for(int k = 0; k < n; k++){
               sj.add("10");
            }
         out.println(sj.toString());
         } else {
            out.println(s);
         }
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}