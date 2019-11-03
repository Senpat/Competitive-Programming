//make sure to make new file!
import java.io.*;
import java.util.*;
//from C1183
public class binarysearch{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
   
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
      
      
      
      out.close();
   }
   
      
}