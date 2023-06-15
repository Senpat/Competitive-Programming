//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSC{
   
   public static long MAX = 1000000000000000000L-1;
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      
      //binary search for highest number where they are not equal
      long l = 0L;
      long r = MAX;
      long ans = -1;
      
      while(l <= r){
         long mid = l + (r-l)/2;
         
         if(check(mid)){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      String answer = query2(ans);
      out.println("! " + answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(long x) throws IOException{
      boolean q1 = query1(x);
      boolean q2 = query1(x);
      
      if(q1 == q2) return q1;
      
      return query1(x);
   }
   
   public static boolean query1(long x) throws IOException{
      out.println("1 " + x + " " + MAX);
      out.flush();
      
      String s = f.readLine();
      return s.equals("NO");
   }
   
   public static String query2(long x) throws IOException{
      out.println("2 " + x);
      out.flush();
      
      return f.readLine();
   }
      
}