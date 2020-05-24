//make sure to make new file!
import java.io.*;
import java.util.*;

public class UNITGCDb{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      

      
      
      
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         out.println(Math.max(1,n/2));
         for(int i = 1; i <= n; i++){
            if(i == 1 && n >= 3){
               out.println("3 1 2 3");
               i+=2;
            } else if(i+1 <= n){
               out.println("2 " + i + " " + (i+1));
               i++;
            } else { 
               out.println("1 " + i);
            }
         }
               
         
         
      }
      
      
      
      
      out.close();
   }
   
  
   
}