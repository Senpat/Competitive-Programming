//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong, too slow
public class A36{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long a = find(n);
      long b = n/a;
      
      out.println("0 0 0 " + a + " " + b + " 0");
      

      
      
      
      
      out.close();
   }
   
   public static long find(long n){
      long sqrt = (long)Math.sqrt(n);
      
      while(n%sqrt!=0){
         sqrt--;
      }
      
      return sqrt;
   }
   
      
}