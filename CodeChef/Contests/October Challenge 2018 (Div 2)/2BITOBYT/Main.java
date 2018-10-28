//2BITOBYT
import java.io.*;
import java.util.*;

public class Main{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < n; q++){
         int cur = Integer.parseInt(f.readLine());
         
         long num = (long)Math.pow(2,cur/26);
         
         if(cur % 26 == 0){
            out.println("0 0 " + (num/2));
         } else if(cur % 26 <= 2){
            out.println(num + " 0 0");
         } else if(cur % 26 <= 10){
            out.println("0 " + num + " 0");
         } else{
            out.println("0 0 "+ num);
         }
      }
         
      
      
      
      out.close();
   }
   
}