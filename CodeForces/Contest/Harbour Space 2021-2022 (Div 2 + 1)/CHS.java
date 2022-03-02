//make sure to make new file!
import java.io.*;
import java.util.*;

public class CHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] input = f.readLine().toCharArray();
         
         int[] a = new int[10];
         int[] b = new int[10];
         
         for(int k = 0; k < 10; k++){
            if(input[k] == '1'){
               a[k] = 1;
               b[k] = 1;
            } else if(input[k] == '0'){
               a[k] = 0;
               b[k] = 0;
            } else {
               if(k%2 == 0){
                  a[k] = 1;
                  b[k] = 0;
               } else {
                  a[k] = 0;
                  b[k] = 1;
               }
            }
         }
         
         //calculate how many kicks and take minimum
         int akicks = 0;
         int a1 = 0;
         int a2 = 0;
         int a1left = 5;
         int a2left = 5;
         
         int bkicks = 0;
         int b1 = 0;
         int b2 = 0;
         int b1left = 5;
         int b2left = 5;
         
         for(int k = 0; k < 10; k++){
            akicks++;
            if(k%2 == 0){
               a1 += a[k];
               a1left--;
               
            } else {
               a2 += a[k];
               a2left--;
            }
            
            //a1 wins
            if(a1 > a2 + a2left) break;
            //a2 wins
            if(a2 > a1 + a1left) break;
         }
         
         for(int k = 0; k < 10; k++){
            bkicks++;
            if(k%2 == 0){
               b1 += b[k];
               b1left--;
               
            } else {
               b2 += b[k];
               b2left--;
            }
            
            //b1 wins
            if(b1 > b2 + b2left) break;
            //b2 wins
            if(b2 > b1 + b1left) break;
         }
         
         
         out.println(Math.min(akicks,bkicks));
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}