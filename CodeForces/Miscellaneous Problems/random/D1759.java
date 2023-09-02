//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1759{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         int a2 = 0;
         int a5 = 0;
         
         long ai = a;
         while(ai % 2 == 0){
            a2++;
            ai /= 2;
         }
         while(ai % 5 == 0){
            a5++;
            ai /= 5;
         }
         
         long answer = 1L;
         
         if(a2 < a5){
            //add 2s
            while(answer*2L <= b && a2 < a5){
               a2++;
               answer *= 2L;
            }
         } else if(a2 > a5){
            //add 5s
            while(answer*5L <= b && a2 > a5){
               a5++;
               answer *= 5L;
            }
         }
         
         //add 10s
         while(answer*10L <= b){
            answer *= 10L;
         }
         
         //maximize result
         answer = b/answer*answer;
         
         out.println(a*answer);
      }
      
      
      
      
      out.close();
   }
   
      
}