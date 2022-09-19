//make sure to make new file!
import java.io.*;
import java.util.*;

public class A784{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         
         int n = array.length;
         
         int sum = 0;
         for(int k = 0; k < n; k++){
            sum += array[k]-'a'+1;
         }
         
         if(n == 1){
            out.println("Bob " + sum);
         } else if(n%2 == 0){
            out.println("Alice " + sum);
         } else {
            int bob = Math.min(array[0]-'a'+1,array[n-1]-'a'+1);
            out.println("Alice " + (sum-2*bob));
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}