//make sure to make new file!
import java.io.*;
import java.util.*;

public class C570
{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long c = Long.parseLong(st.nextToken());
         long n = Long.parseLong(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         
         
         long l = 0;
         long r = n;
         long mid;
      
      
         long answer = -1;
         while(l<=r){
            mid = (l+r)/2;
            if(mid*a+(n-mid)*b < c){
               l=mid+1;
               answer = mid;
            } else {
               r=mid-1;
            }
         }
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}