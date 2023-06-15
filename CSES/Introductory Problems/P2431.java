//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2431{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N10 = 19;
      long[] pow10 = new long[N10];
      pow10[0] = 1L;
      for(int k = 1; k < N10; k++){
         pow10[k] = (10L * pow10[k-1]);
      }
      
      //where numbers of that digit start
      long[] start = new long[N10];
      start[1] = 1L;
      for(int k = 2; k < N10; k++){
         start[k] = start[k-1] + (k-1)*(pow10[k-1] - pow10[k-2]);
      }
      
      int q = Integer.parseInt(f.readLine());
      
      
      for(int t = 0; t < q; t++){
         long x = Long.parseLong(f.readLine());
         
         int digits = 1;
         while(start[digits] <= x){
            digits++;
         }
         digits--;
         
         long number = (x-start[digits])/digits + pow10[digits-1];
         int digitofnumber = (int)((x-start[digits])%digits);
         
         long answer = (number/pow10[digits-1-digitofnumber])%10;
         out.println(answer);
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}