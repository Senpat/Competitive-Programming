//make sure to make new file!
import java.io.*;
import java.util.*;

public class B862{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
         
         int minindex = 0;
         for(int k = 1; k < n; k++){
            if(array[k] <= array[minindex]){
               minindex = k;
            }
         }
         
         //move minindex to beginning
         StringJoiner sj = new StringJoiner("");
         sj.add("" + array[minindex]);
         for(int k = 0; k < n; k++){
            if(k != minindex) sj.add("" + array[k]);
         }
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
      
}