//make sure to make new file!
import java.io.*;
import java.util.*;

public class B121{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         String s = f.readLine();
         int n = s.length();
         
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
         }
         
         int inc10 = -1;         //sum is >= 10 and increases (use instantly)
         int dec10 = -1;         //sum is >= 10 but decreases (use last one)
         //same is impossible
         
         //otherwise add first 2
         
         for(int k = 0; k < n-1; k++){
            if(array[k]+array[k+1] >= 10){
               int cur = 10*array[k]+array[k+1];
               if(cur < array[k]+array[k+1]){
                  inc10 = k;
                  break;
               } else {
                  dec10 = k;
               }
            }
         }
         
         int answer = 0;
         if(inc10 != -1){
            answer = inc10;
         }
         if(dec10 != -1){
            answer = dec10;
         }
         
         StringJoiner sj = new StringJoiner("");
         for(int k = 0; k < n; k++){
            if(k == answer){
               sj.add("" + (array[k]+array[k+1]));
               k++;
            } else {
               sj.add("" + array[k]);
            }
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}