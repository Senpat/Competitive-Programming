//make sure to make new file!
import java.io.*;
import java.util.*;

public class A565{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
       
      long[] pow2 = new long[42];
      long[] pow3 = new long[42];
      long[] pow5 = new long[42];
      
      pow2[0] = 1;
      pow3[0] = 1;
      pow5[0] = 1;
      
      for(int k = 1; k < 42; k++){
         pow2[k] = 2*pow2[k-1]; 
         pow3[k] = 3*pow3[k-1];
         pow5[k] = 5*pow5[k-1];
      }
      
      for(int q = 0; q < t; q++){
         long i = Long.parseLong(f.readLine());
         
         int i2 = calcnum(pow2,i);
         int i3 = calcnum(pow3,i);
         int i5 = calcnum(pow5,i);
         
         long answer = i2+2*i3+3*i5;
         if(pow2[i2]*pow3[i3]*pow5[i5] != i){
            out.println(-1);
         } else {
            out.println(answer);
         }
     }

      
      
      
      
      out.close();
   }
   
   public static int calcnum(long[] pow, long i){
      int index = 0;
      while(i >= pow[index] && i % pow[index] == 0){
         index++;
      }
      
      return index-1;
   }
   
   
}