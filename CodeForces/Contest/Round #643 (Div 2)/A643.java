//make sure to make new file!
import java.io.*;
import java.util.*;

public class A643{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         if(x==1){
            out.println(a);
            continue;
         }
         
         boolean flag = false;
         for(int k = 1; k < x; k++){
            long i = get(a);
            a += i;
            if(i == 0){
               out.println(a);
               flag = true;
               break;
            }
         }
         
         if(!flag)
         out.println(a);
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static long get(long a){
      long min = a%10;
      long max = a%10;
      a/=10;
      while(a > 0){
         min = Math.min(min,a%10);
         max = Math.max(max,a%10);
         a/=10;
      }
      
      return min*max;
   }
      
   
      
}