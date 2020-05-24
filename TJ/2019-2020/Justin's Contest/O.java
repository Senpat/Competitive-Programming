//make sure to make new file!
import java.io.*;
import java.util.*;

public class O{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long a = biggestbit(n);
      long b = n-a;
      
      if(a-b <= m){
         out.println(a + " " + b);
      } else {
         out.println(-1);
      }
      
      
   
      
      
      
      
      
      out.close();
   }
   
   static long biggestbit(long n) 
   { 
      if (n == 0) 
         return 0; 
   
      int msb = 0; 
      while (n != 0) 
      { 
         n = n / 2; 
         msb++; 
      } 
      msb--;
      return (1L << msb); 
   } 
   
      
}