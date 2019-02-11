//make sure to make new file!
import java.io.*;
import java.util.*;

public class C538{
   
   public static long sum,c,x;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      
      sum = 0;
   //cout << "hi" << endl;
   
      for(long k = 1; k * k <= b; k++){
      
         if(b%k != 0) 
            continue;
      //cout << k << endl;
      
         c = 0;
         x = b/k;
         while(x <= n){
            System.out.println(x);
            c += n/x;
            //cout << c << endl;
            x *= x;
         }
      
         if(k*k == b){
            sum += c/2;
         } else {
            sum += c;
         }
      }
   
      
      System.out.println(sum);
      
      
      out.close();
   }
   
      
}