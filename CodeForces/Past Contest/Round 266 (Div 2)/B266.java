//make sure to make new file!
import java.io.*;
import java.util.*;

public class B266{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken()) * 6L;
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      
      if(a*b >= n){
         out.println(a*b);
         out.println(a + " " + b);
         out.close();
         return;
      }
      
      boolean swap = false;
      if(a < b){
         long temp = a;
         a = b;
         b = temp;
         swap = true;
      }
      //a >= b
      
      long i = n;
      while(true){
         //try i
         boolean found = false;
         for(long k = b; k*k <= i; k++){
            if(i % k == 0L){
               if(i/k >= a){
                  out.println(i);
                  if(swap){
                     out.println(k + " " + i/k);
                  } else {
                     out.println(i/k + " " + k);
                  }
                  found = true;
               }
               break;
            }
         }
         
         if(found){
            break;
         }
         
         i++;
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}