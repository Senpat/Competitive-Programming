//make sure to make new file!
import java.io.*;
import java.util.*;

public class A165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         //see if it has at least two different prime factors
         //then you can make it two numbers multiplied to each other, and then a lot of 1s to make the sum match
         
         int prime = 0;
         
         int i = 2;
         
         while(i*i <= n){
            if(n%i == 0){
               prime++;
               while(n%i == 0) n /= i;
            }
            i++;
         }
         if(n > 1) prime++;
         
         if(prime >= 2){
            out.println("Yes");
         } else {
            out.println("No");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}