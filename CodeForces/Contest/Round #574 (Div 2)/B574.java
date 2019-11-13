//make sure to make new file!
import java.io.*;
import java.util.*;

public class B574{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
   
      long l = -1;
      long r = n;
      long mid;
      
      
      long answer = -1;
      while(r-l>1){
         mid = (l+r)/2;
         // if(mid*(mid+1)/2 - m == n-mid){
//             answer = n-mid;
//             break;
//          }
         if(mid*(mid+1)/2 - m < n-mid){
            l=mid;
         } else {
            r=mid;
         }
      }
      answer = n-r;
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}