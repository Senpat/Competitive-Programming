//make sure to make new file!
import java.io.*;
import java.util.*;

public class A700{

   public static BufferedReader f;
   public static PrintWriter out;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      int l = 1;
      int r = n;
      int ans = -1;
      while(l <= r){
         int mid = l + (r-l)/2;
         int queryi = query(mid);
         if(queryi == 0){
            ans = mid;
            break;
         } else if(queryi == -1){
            r = mid-1;
         } else if(queryi == 1){
            l = mid+1;
         }
      }
      
      out.println("! " + ans);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //returns -1 if decreasing (so go left), 0 if min, 1 if increasing
   public static int query(int i)throws IOException{
      int a = Integer.MAX_VALUE;
      int b = Integer.MAX_VALUE;
      int c = Integer.MAX_VALUE;
      if(i > 1){
         out.println("? " + (i-1));
         out.flush();
         
         a = Integer.parseInt(f.readLine());
      }
      out.println("? " + i);
      out.flush();
      
      b = Integer.parseInt(f.readLine());
      
      if(i < n){
         out.println("? " + (i+1));
         out.flush();
      
         c = Integer.parseInt(f.readLine());
      }
      
      if(b < a && b < c) return 0;
      if(b < a && b > c) return 1;
      if(b > a && b < c) return -1;
      //local max, go in either direction
      return 1;
      
   }
      
}